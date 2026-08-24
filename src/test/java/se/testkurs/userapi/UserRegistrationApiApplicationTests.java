package se.testkurs.userapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Enda testet som redan finns i basprojektet - kontrollerar bara att
 * Spring-kontexten startar. Alla ovriga tester (enhet, komponent,
 * integration) skrivs av eleverna under kursens veckor, enligt
 * Learnpoint-ovningarna.
 */
@SpringBootTest
class UserRegistrationApiApplicationTests {

    @Test
    void contextLoads() {
        // Om denna test gar igenom startar hela applikationen korrekt.
    }
}
