package academy.codilas.loyaltycard.mapper;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Collections;
import java.util.UUID;

import academy.codilas.loyaltycard.controller.DTO.AdminDTO;
import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.repository.entity.AdminEntity;
import org.junit.jupiter.api.Test;

class AdminMapperTest {

    private final AdminMapper adminMapper = new AdminMapperImpl();

    @Test
    void shouldMapDomainToDto() {
        // given
        Admin admin = new Admin(
                UUID.fromString("488352d9-ae2f-4599-85a0-ae938dc3db77"),
                "Nikita",
                "email",
                "1234Qwerty!"
        );

        // when
        AdminDTO adminDTO = adminMapper.toDTO(admin);

        // then
        assertEquals(admin.getId(), adminDTO.getId());
        assertEquals(admin.getName(), adminDTO.getName());
        assertEquals(admin.getEmail(), adminDTO.getEmail());
        assertEquals(admin.getPassword(), adminDTO.getPassword());
    }

    @Test
    void shouldMapDtoToDomain() {
        // given
        AdminDTO adminDTO = new AdminDTO(
                null,
                "Nikita",
                "test@gmail.com",
                "123456789"
        );

        // when
        Admin admin = adminMapper.toDomain(adminDTO);

        // then
        assertNull(adminDTO.getId());
        assertEquals("Nikita", admin.getName());
        assertEquals("test@gmail.com", admin.getEmail());
        assertEquals("123456789", admin.getPassword());
    }

    @Test
    void shouldMapEntityToDomain() {
        // given
        AdminEntity adminEntity = new AdminEntity(
                UUID.fromString("488352d9-ae2f-4599-85a0-ae938dc3db77"),
                "Nikita",
                "email",
                "1234Qwerty!"

                );

        // when
        Admin admin = adminMapper.toDomain(adminEntity);

        // then
        assertEquals(adminEntity.getId(), admin.getId());
        assertEquals(adminEntity.getName(), admin.getName());
        assertEquals(adminEntity.getEmail(), admin.getEmail());
        assertEquals(adminEntity.getPassword(), admin.getPassword());
    }

    @Test
    void shouldMapDomainToEntity() {
        // given
        Admin admin = new Admin(
                UUID.fromString("488352d9-ae2f-4599-85a0-ae938dc3db77"),
                "Nikita",
                "email",
                "1234Qwerty!"
        );

        // when
        AdminEntity adminEntity = adminMapper.toEntity(admin);

        // then
        assertEquals(adminEntity.getId(), admin.getId());
        assertEquals(adminEntity.getName(), admin.getName());
        assertEquals(adminEntity.getEmail(), admin.getEmail());
        assertEquals(adminEntity.getPassword(), admin.getPassword());

    }
}