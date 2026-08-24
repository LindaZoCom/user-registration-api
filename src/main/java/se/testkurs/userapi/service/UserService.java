package se.testkurs.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import se.testkurs.userapi.exception.InvalidEmailException;
import se.testkurs.userapi.exception.UserAlreadyExistsException;
import se.testkurs.userapi.exception.UserNotFoundException;
import se.testkurs.userapi.model.User;
import se.testkurs.userapi.repository.UserRepository;

/**
 * Affarslogik for anvandarregistrering.
 *
 * OBS till elever/lararen: nagra Learnpoint-ovningar antar att
 * findUserByEmail returnerar "null" nar ingen anvandare hittas (aldre stil)
 * medan andra ovningar antar Optional&lt;User&gt;. UserRepository foljer
 * den moderna Spring Data-konventionen (Optional), och findUserByEmail
 * har medvetet kvar den aldre null-stilen for att matcha
 * Spring-Boot-Test-med-ovningar-exemplen rakt av.
 */
@Service
public class UserService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String password) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Ogiltigt email-format: " + email);
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("En anvandare med email " + email + " finns redan");
        }

        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    /** Aldre null-stil - anvands av flera Mockito-ovningar direkt. */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Ingen anvandare med id " + id));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Ingen anvandare med id " + id);
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findOptionalByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
