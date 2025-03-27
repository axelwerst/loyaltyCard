package academy.codilas.loyaltycard.controller.DTO;

public class LoginDTO {

    private final String email;
    private final String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
