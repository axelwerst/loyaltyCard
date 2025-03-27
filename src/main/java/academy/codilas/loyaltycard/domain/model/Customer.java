package academy.codilas.loyaltycard.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String phone;
    private String name;
    private String email;

    public Customer() {
    }

    public Customer(UUID id, String phone, String name, String email) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void update(Customer customer) {
        this.phone = customer.getPhone();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }

    @Override
    public String toString() {
        return "Customer{"
                + "id='" + id + '\''
                + ", phone='" + phone + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id)
                && Objects.equals(phone, customer.name)
                && Objects.equals(name, customer.name)
                && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, name, email);
    }
}