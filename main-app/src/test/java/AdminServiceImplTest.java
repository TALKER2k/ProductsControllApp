import org.example.DTO.BlockedDTO;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.example.services.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBlockUser() {
        User user = new User().setId(1).setUserName("User").setEmail("user@mail.com");
        BlockedDTO blockedDTO = new BlockedDTO(user.getUserName(), user.getEmail());

        when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        adminService.dismissalUser(blockedDTO);

        verify(userRepository, times(1)).findByEmail("user@mail.com");
    }
}