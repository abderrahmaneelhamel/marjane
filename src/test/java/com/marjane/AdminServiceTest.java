package com.marjane;
import com.marjane.model.Admin;
import com.marjane.repository.AdminRepository;
import com.marjane.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @Test
    public void testGetAllAdmins() {
        // Mock behavior
        when(adminRepository.findAll()).thenReturn(Arrays.asList(new Admin(), new Admin()));

        // Call the method you want to test
        List<Admin> admins = adminService.getAllAdmins();

        // Assertions
        assertEquals(2, admins.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetAdminById() {
        // Mock behavior
        when(adminRepository.findById(52L)).thenReturn(Optional.of(new Admin(52L, "admin", "admin@gmail.com", "password")));

        // Call the method you want to test
        Admin admin = adminService.getAdminById(52L);

        // Assertions
        assertEquals(52L, admin.getId());
        // Add more assertions as needed
    }

    @Test
    public void testUpdateAdmin() {
        // Mock behavior
        when(adminRepository.findById(52L)).thenReturn(Optional.of(new Admin(52L, "admin", "admin@gmail.com", "password")));
        when(adminRepository.save(any(Admin.class))).thenReturn(new Admin(52L, "admin", "updated@example.com", "newPassword"));

        // Call the method you want to test
        Admin updatedAdmin = adminService.updateAdmin(52L, new Admin(52L, "admin", "updated@example.com", "newPassword"));

        // Assertions
        assertEquals(52L, updatedAdmin.getId());
        assertEquals("updated@example.com", updatedAdmin.getEmail());
        assertEquals("newPassword", updatedAdmin.getPassword());
        // Add more assertions as needed
    }



    @Test
    public void testCreateAdmin() {
        // Mock behavior
        when(adminRepository.save(any(Admin.class))).thenAnswer(invocation -> {
            Admin createdAdmin = invocation.getArgument(0);
            createdAdmin.setId(1L); // Set a dummy ID for the created admin
            return createdAdmin;
        });

        // Call the method you want to test
        Admin admin = adminService.createAdmin(new Admin("admin", "admin@example.com", "password"));

        // Assertions
        assertNotNull(admin.getId());
        assertEquals(1L, admin.getId()); // Adjust this assertion based on the expected ID
    }


    @Test
    public void testDeleteAdmin() {
        // Call the method you want to test
        adminService.deleteAdmin(54L);

        // Verify that the deleteById method was called with the correct argument
        verify(adminRepository, times(1)).deleteById(54L);
    }
}
