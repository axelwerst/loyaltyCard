package academy.codilas.loyaltycard.mapper;

import academy.codilas.loyaltycard.controller.DTO.AdminDTO;
import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.repository.entity.AdminEntity;


public interface AdminMapper {

    AdminDTO toDTO(Admin admin);

    Admin toDomain(AdminDTO adminDTO);

    Admin toDomain(AdminEntity adminEntity);

    AdminEntity toEntity(Admin admin);
}
