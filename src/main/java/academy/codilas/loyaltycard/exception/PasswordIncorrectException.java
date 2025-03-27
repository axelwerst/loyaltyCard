package academy.codilas.loyaltycard.exception;


public class PasswordIncorrectException extends RuntimeException {

  public PasswordIncorrectException(String adminEmail) {
    super("Password was incorrect for Admin " + adminEmail);
  }
}
