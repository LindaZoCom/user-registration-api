package se.testkurs.userapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Anvandarentitet.
 *
 * Falten (username, email, password) speglar det som anvands i de flesta
 * Learnpoint-ovningarna. Nagra ovningar anvander "name" istallet for
 * "username" eller hoppar over password - se lararmanuset for en oversatt
 * jamforelsestabell mellan ovningarnas exempel och denna klass.
 */
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username far inte vara tomt")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Email far inte vara tomt")
    @Email(message = "Email maste ha giltigt format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password far inte vara tomt")
    @Size(min = 8, message = "Password maste vara minst 8 tecken")
    @Column(nullable = false)
    private String password;

    public User() {
        // Kravs av JPA/Hibernate
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "'}";
    }
}
