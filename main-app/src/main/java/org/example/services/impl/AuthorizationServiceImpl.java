package org.example.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.DTO.LoginDTO;
import org.example.DTO.RegistrationFormDTO;
import org.example.models.Role;
import org.example.models.User;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.security.JWTGenerator;
import org.example.security.SecurityConstants;
import org.example.services.AuthorizationService;
import org.example.utils.exceptions.UserCreatedException;
import org.example.utils.responses.UserErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    private final ModelMapper modelMapper;

    public AuthorizationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void registerAdmin(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: User is not found by id."));

        List<Role> roles = user.getUserRoles();
        roles.add(roleRepository.findByRoleName(SecurityConstants.ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));

        user.setUserRoles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User registerUser(RegistrationFormDTO registrationFormDto) {
        User registeredUser = convertToUser(registrationFormDto);
        userRepository.save(registeredUser);

        return registeredUser;
    }

    @Override
    public String loginUser(LoginDTO loginDto) {
        log.info("Authentication. User {}", loginDto.username());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.username(),
                        loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtGenerator.generateToken(authentication);
    }

    @Override
    public void checkErrors(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder msgErrors = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                msgErrors.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage());
            }

            throw new UserCreatedException(msgErrors.toString());
        }
    }

    @Override
    public boolean existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private User convertToUser(RegistrationFormDTO registrationFormDTO) {
        Role roles = roleRepository.findByRoleName(SecurityConstants.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        return modelMapper.map(registrationFormDTO, User.class)
                .setPassword(passwordEncoder.encode((registrationFormDTO.getPassword())))
                .setUserRoles(Collections.singletonList(roles));
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> exceptionHandler(UserCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
