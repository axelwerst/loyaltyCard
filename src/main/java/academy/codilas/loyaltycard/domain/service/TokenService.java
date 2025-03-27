package academy.codilas.loyaltycard.domain.service;


import academy.codilas.loyaltycard.domain.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    String createToken(Admin admin);

    boolean isValidToken(String token);

    String getAdminId(String token);
}
