package academy.codilas.loyaltycard.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Admin {

    private UUID id;
    private String name;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void update(Admin admin) {
//        this.adminName = admin.getAdminName();
//        this.email = admin.getEmail();
    }

    @Override
    public String toString() {
        return "Admin{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id)
                && Objects.equals(name, admin.name)
                && Objects.equals(email, admin.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}