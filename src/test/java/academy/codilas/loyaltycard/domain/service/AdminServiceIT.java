package academy.codilas.loyaltycard.domain.service;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.UUID;

import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.exception.AdminNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")

/*
 * @Transactional Ensures that each test runs in a transaction
 * that is rolled back after the test,
 * keeping the database in a clean state.
 */
@Transactional
class AdminServiceIT {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateAdminnAndEncodePassword() {
        // given: a new Person with a plain password
        Admin admin = new Admin();
        admin.setEmail("test@example.com");
        admin.setPassword("plainPassword");

        // when: creating the person
        Admin savedAdmin = adminService.createAdmin(admin);

        // then: the person is saved and the password is encoded
        assertNotNull(savedAdmin.getId(), "Admin ID should not be null after saving");
        assertNotEquals("plainPassword", savedAdmin.getPassword(), "Password should be encoded");
        assertTrue(
                passwordEncoder.matches("plainPassword", savedAdmin.getPassword()),
                "Encoded password should match the raw password");
    }

    @Test
    void shouldRetrieveAdminById() {
        // given: a saved person
        Admin admin = new Admin();
        admin.setEmail("retrieve@example.com");
        admin.setPassword("password123");
        Admin savedAdmin = adminService.createAdmin(admin);

        // when: retrieving the person by ID
        Admin foundAdmin = adminService.getAdminById(savedAdmin.getId());

        // then: the retrieved person matches the saved person
        assertNotNull(foundAdmin, "Retrieved person should not be null");
        assertEquals(savedAdmin.getId(), foundAdmin.getId(), "IDs should match");
    }

    @Test
    void shouldThrowExceptionWhenAdminNotFound() {
        // given: a random UUID that does not exist
        UUID randomId = UUID.randomUUID();

        // then: getPersonById throws PersonNotFoundException
        assertThrows(
                AdminNotFoundException.class,
                () -> adminService.getAdminById(randomId),
                "Expected PersonNotFoundException for non-existing ID");
    }

    @Test
    void shouldUpdateAdmin() {
        // given: a saved person
        Admin admin = new Admin();
        admin.setEmail("update@example.com");
        admin.setPassword("oldPassword");
        Admin savedAdmin = adminService.createAdmin(admin);

        // Prepare updated data
        Admin updateInfo = new Admin();
        updateInfo.setEmail("updated@example.com");

        // when: updating the person
        Admin updatedAdmin = adminService.updateAdmin(savedAdmin.getId(), updateInfo);

        // then: the person has updated fields
        assertEquals("updated@example.com", updatedAdmin.getEmail(), "Email should be updated");
    }

    @Test
    void shouldDeleteAdmin() {
        // given: a saved person
        Admin admin = new Admin();
        admin.setEmail("delete@example.com");
        admin.setPassword("passwordToDelete");
        Admin savedAdmin = adminService.createAdmin(admin);

        // when: deleting the person
        adminService.deleteAdmin(savedAdmin.getId());

        // then: retrieving the person should throw PersonNotFoundException
        assertThrows(
                AdminNotFoundException.class,
                () -> adminService.getAdminById(savedAdmin.getId()),
                "Expected exception when retrieving deleted person");
    }

    @Test
    void shouldRetrieveAllAdmins() {
        // given: create two persons
        Admin admin1 = new Admin();
        admin1.setAdminName(" Name 1");
        admin1.setEmail("person1@example.com");
        admin1.setPassword("pass1");

        Admin admin2 = new Admin();
        admin2.setAdminName(" Name 2");
        admin2.setEmail("person2@example.com");
        admin2.setPassword("pass2");

        adminService.createAdmin(admin1);
        adminService.createAdmin(admin2);

        // when: retrieving all persons
        List<Admin> persons = adminService.getAdmins();

        // then: the list should contain at least 2 persons
        assertTrue(persons.size() >= 2, "There should be at least 2 persons in the database");
    }
}
