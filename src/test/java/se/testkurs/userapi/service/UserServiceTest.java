package se.testkurs.userapi.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.testkurs.userapi.exception.InvalidEmailException;
import se.testkurs.userapi.exception.UserAlreadyExistsException;
import se.testkurs.userapi.model.User;
import se.testkurs.userapi.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

    @Test
    void registerUser_InvalidEmail_ShouldThrowException() {
        assertThrows(InvalidEmailException.class,()-> userService.registerUser
                ("anna", "invalid-email", "password123"));
        verifyNoInteractions(userRepository);

    }
    @Test
    void findUserByEmail_ExistingUser_ShouldReturnUser() {
        User existingUser = new User(1L, "anna", "anna@test.com", "password123");

        when(userRepository.findByEmail("anna@test.com"))
                .thenReturn(Optional.of(existingUser));

        User result = userService.findUserByEmail("anna@test.com");

        assertEquals(existingUser, result);
        verify(userRepository).findByEmail("anna@test.com");

    }
    @Test
    void findUserByEmail_NonExistingUser_ShouldReturnNull() {
        when(userRepository.findByEmail("missing@test.com"))
                .thenReturn(Optional.empty());

        User result = userService.findUserByEmail("missing@test.com");

        assertNull(result);
        verify(userRepository).findByEmail("missing@test.com");
    }
}
