package academy.codilas.loyaltycard.controller;

import academy.codilas.loyaltycard.controller.DTO.AdminDTO;
import academy.codilas.loyaltycard.controller.DTO.AuthTokenDTO;
import academy.codilas.loyaltycard.controller.DTO.ErrorDTO;
import academy.codilas.loyaltycard.controller.DTO.LoginDTO;
import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.domain.service.AdminService;
import academy.codilas.loyaltycard.domain.service.LoginService;
import academy.codilas.loyaltycard.exception.AdminNotFoundException;
import academy.codilas.loyaltycard.exception.PasswordIncorrectException;
import academy.codilas.loyaltycard.mapper.AdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AdminService adminService;
    private final AdminMapper adminMapper;
    private final LoginService loginService;

    @Autowired
    public AuthController(
            AdminService adminService, AdminMapper adminMapper, LoginService loginService) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
        this.loginService = loginService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody AdminDTO adminDTO) {

       Admin admin = adminMapper.toDomain(adminDTO);

        try {
            adminService.createAdmin(admin);
        } catch (Exception e) {
            log.error(
                    "Error creating admin with email {} and first name {}.",
                    adminDTO.getEmail(),
                   adminDTO.getName());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenDTO> login(@RequestBody LoginDTO loginDTO) {

        String token = loginService.login(loginDTO.getEmail(), loginDTO.getPassword());

        return ResponseEntity.ok(new AuthTokenDTO(token));
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleAdminNotFoundException(AdminNotFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO("Wrong credentials");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorDTO> handlePasswordIncorrectException(PasswordIncorrectException e) {
        ErrorDTO errorDTO = new ErrorDTO("Wrong credentials");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }
}
