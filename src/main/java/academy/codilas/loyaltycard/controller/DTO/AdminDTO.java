package academy.codilas.loyaltycard.controller.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminDTO {
    private String id;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public AdminDTO() {

    }

    public AdminDTO(String id, String name, String email,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
