package academy.codilas.loyaltycard.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customerid")
    private String id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String email;
    public CustomerEntity(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public CustomerEntity() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
