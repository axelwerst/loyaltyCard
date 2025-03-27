package academy.codilas.loyaltycard.controller;

import academy.codilas.loyaltycard.controller.DTO.AdminDTO;
import academy.codilas.loyaltycard.controller.DTO.ErrorDTO;
import academy.codilas.loyaltycard.exception.AdminNotFoundException;
import academy.codilas.loyaltycard.domain.service.AdminService;
import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.mapper.AdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminController(AdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }

    //
    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAdmins() {
        List<Admin> adminList = adminService.getAdmins();
        List<AdminDTO> adminDTOList = adminList
                .stream()
                .map(adminMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminDTOList);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable UUID adminId) throws AdminNotFoundException {

        Admin admin = adminService.getAdminById(adminId);

        AdminDTO adminDTO = adminMapper.toDTO(admin);

        return ResponseEntity.status(HttpStatus.OK).body(adminDTO);
    }

//    @DeleteMapping("/{adminId}")
//    public ResponseEntity<Void> deleteAdmin@PathVariable String adminId) throws AdminNotFoundExeption {
//       adminService.deleteAdmin(adminId);
//
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

//    @PostMapping
//    public ResponseEntity<AdminDTO> NewAdmin(@RequestBody AdminDTO adminDTO) {
//       adminService.newAdmin(adminDTO.getEmail(), adminDTO.getAdminName());
//        return ResponseEntity.
//                status(HttpStatus.CREATED)
//                .body(adminDTO);
//    }
//
//    @PutMapping("/{adminId}")
//    public ResponseEntity<AdminDTO> updateAdmin(
//            @PathVariable String adminId,
//            @RequestBody NewAdminDTO updatedAdminDTO) throws AdminNotFoundException {
//       Admin updatedAdmin = adminService.updateAdmin(
//                updateAddminDTO.getAdminName(),
//                updatedAdminDTO.getEmail(),
//                adminId
//        );
//        AdminDTO updatedAdminDTOResponse = adminMapper.toDTO(updatedAdmin);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(updatedAdminDTOResponse);
//    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleAdminNotFoundException(AdminNotFoundException e) {

        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

