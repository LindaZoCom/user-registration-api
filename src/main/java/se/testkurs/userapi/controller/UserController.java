package se.testkurs.userapi.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.testkurs.userapi.exception.UserNotFoundException;
import se.testkurs.userapi.model.User;
import se.testkurs.userapi.service.UserService;

/**
 * REST-lager for anvandarregistrering.
 * Endpoints:
 *   POST   /api/users            - skapa ny anvandare (201)
 *   GET    /api/users            - lista alla anvandare
 *   GET    /api/users?email=...  - hitta en anvandare via email
 *   GET    /api/users/{id}       - hamta en anvandare via id
 *   DELETE /api/users/{id}       - ta bort en anvandare
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String email) {
        if (email != null) {
            User user = userService.findUserByEmail(email);
            if (user == null) {
                throw new UserNotFoundException("Ingen anvandare med email " + email);
            }
            return ResponseEntity.ok(user);
        }
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
