import org.example.DTO.RegistrationFormDTO;
import org.example.models.Role;
import org.example.models.User;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.security.SecurityConstants;
import org.example.services.impl.AuthorizationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AuthorizationServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthorizationServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterNewUser() {
        String userName = "testUser";
        String email = "test@example.com";
        String password = "password";

        User user = new User()
                .setUserName(userName)
                .setEmail(email)
                .setPassword(password)
                .setId(1);

        when(userRepository.save(any(User.class))).thenReturn(user);

        Role role = new Role()
                .setRoleName(SecurityConstants.USER)
                .setId(1)
                .setUsers(List.of(user));
        when(roleRepository.findByRoleName(anyString())).thenReturn(Optional.of(role));

        RegistrationFormDTO registrationFormDTO = new RegistrationFormDTO()
                .setUsername(userName)
                .setEmail(email)
                .setPassword(password);
        authService.registerUser(registrationFormDTO);

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(password);
    }
}
