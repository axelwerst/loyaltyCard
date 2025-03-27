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
        adminDTO.setPassword(admin.getPassword());

        return adminDTO;
    }

    @Override
    public Admin toDomain(AdminDTO adminDTO) {
        if (adminDTO == null) {
            return null;
        }

        Admin admin = new Admin();

        admin.setId(adminDTO.getId());
        admin.setEmail(adminDTO.getEmail());
        admin.setName(adminDTO.getName());
        admin.setPassword(adminDTO.getPassword());

        return admin;
    }

    @Override
    public Admin toDomain(AdminEntity adminEntity) {
        if (adminEntity == null) {
            return null;
        }

        Admin admin = new Admin();

        admin.setId(adminEntity.getId());
        admin.setEmail(adminEntity.getEmail());
        admin.setName(adminEntity.getName());
        admin.setPassword(adminEntity.getPassword());

        return admin;
    }

    @Override
    public AdminEntity toEntity(Admin admin) {
        if (admin == null) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setId(admin.getId());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setName(admin.getName());
        adminEntity.setPassword(admin.getPassword());

        return adminEntity;
    }
}