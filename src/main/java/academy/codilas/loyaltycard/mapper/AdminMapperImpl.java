package academy.codilas.loyaltycard.mapper;

import academy.codilas.loyaltycard.controller.DTO.AdminDTO;
import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.repository.entity.AdminEntity;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminDTO toDTO(Admin admin) {
        if (admin == null) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setName(admin.getName());
        adminDTO.setEmail(admin.getEmail());

        return adminDTO;
    }

    @Override
    public Admin toDomain(AdminEntity adminEntity) {

        if (adminEntity == null) {
            return null;
        }

        Admin admin = new Admin(String.valueOf(id), "John Doe", "john.doe@example.com", "1234567890");
        admin.setAdminName(adminEntity.getAdminName());
        admin.setEmail(adminEntity.getEmail());

        return admin;
    }
}