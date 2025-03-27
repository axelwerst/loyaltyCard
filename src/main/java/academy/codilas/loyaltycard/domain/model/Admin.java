package academy.codilas.loyaltycard.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Admin {

    private UUID id;
    private String adminName;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(UUID id,  String adminName, String email, String password) {
        this.id = id;
        this.adminName = adminName;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void getAdminName(String adminName) {
        this.adminName = adminName;
    }


    public void setAdminName(String adminName) {
        this.adminName = adminName;
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
        this.adminName = admin.getAdminName();
        this.email = admin.getEmail();
    }

    private String getName() {
        return adminName;
    }

    @Override
    public String toString() {
        return "Admin{"
                + "id='" + id + '\''
                + ", Name='" + adminName + '\''
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
                && Objects.equals(adminName, admin.adminName)
                && Objects.equals(email, admin.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}