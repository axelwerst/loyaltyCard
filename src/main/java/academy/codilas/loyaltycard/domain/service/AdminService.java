package academy.codilas.loyaltycard.domain.service;



import academy.codilas.loyaltycard.domain.model.Admin;

import java.util.*;

public interface AdminService {

    Admin createAdmin(Admin admin);

    List<Admin> getAdmins();

   Admin getAdminById(UUID adminId);

    Admin updateAdmin(UUID adminId, Admin adminToUpdateFrom);

    void deleteAdmin(UUID adminId);
}
