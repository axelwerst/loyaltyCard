import java.util.UUID;

public class Admin {
    private UUID id;
    private String username;
    private String password; // Захешированный пароль

    public Admin(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}