package se.testkurs.userapi.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.testkurs.userapi.exception.UserAlreadyExistsException;
import se.testkurs.userapi.model.User;
import se.testkurs.userapi.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_ValidData_ShouldSaveUser() {
        //Arrange
        User savedUser = new User(1L, "anna", "anna@test.com", "password123");

        when(userRepository.findByEmail("anna@test.com"))
        .thenReturn(Optional.empty());

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        //Act
        User result = userService.registerUser("anna", "anna@test.com", "password123");

        //Assert
        assertEquals("anna", result.getUsername());
        assertEquals("anna@test.com", result.getEmail());
        assertEquals(1L, result.getId());

        verify(userRepository).findByEmail("anna@test.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerUser_DuplicateEmail_ShouldThrowException() {
        //Arrange
        User excistingUser = new User(1L, "excisting", "anna@test.com", "password123");

        when(userRepository.findByEmail("anna@test.com"))
                .thenReturn(Optional.of(excistingUser));

        //Act and Assert
        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser("anna", "anna@test.com", "password123"));

        verify(userRepository).findByEmail("anna@test.com");
        verify(userRepository, never()).save(any(User.class));

    }
}
