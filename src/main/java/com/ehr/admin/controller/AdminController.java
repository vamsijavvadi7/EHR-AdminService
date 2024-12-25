package com.ehr.admin.controller;

import com.ehr.admin.dto.AdminDto;
import com.ehr.admin.dto.AdminPersonalDetailsDto;
import com.ehr.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create a new Admin
    @PostMapping("/createAdmin")
    public ResponseEntity<Object> createAdmin(@RequestBody AdminPersonalDetailsDto adminPersonalDetailsDto) {
        return adminService.createAdmin(adminPersonalDetailsDto);
    }

    // Get all Admins
    @GetMapping("/getAllAdmins")
    public ResponseEntity<?> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<?> geAdminByUserid(@PathVariable Long userid) {
        return adminService.getAdminByUserid(userid);
    }

    // Get Admin by IDs
    @GetMapping("/getAdminById/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    // Update Admin information
    @PutMapping("/update")
    public ResponseEntity<Object> updateAdmin(@RequestBody AdminPersonalDetailsDto adminPersonalDetailsDto) {
        return adminService.updateAdmin(adminPersonalDetailsDto);
    }

    // Delete Admin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable Long id) {
        return adminService.deleteAdmin(id);
    }
}
