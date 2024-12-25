package com.ehr.admin.service;


import com.ehr.admin.dao.AdminRepository;
import com.ehr.admin.dto.AdminPersonalDetailsDto;
import com.ehr.admin.dto.UserDto;
import com.ehr.admin.feign.UserServiceInterface;
import com.ehr.admin.mapper.AdminMapper;
import com.ehr.admin.model.Admin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserServiceInterface userServiceInterface;

    // Create a new Admin
    @Transactional
    public ResponseEntity<Object> createAdmin(AdminPersonalDetailsDto adminPersonalDetailsDto) {
        Map<String, String> response = new HashMap<>();
        Admin admin=new Admin();
      admin.setId(adminPersonalDetailsDto.getId());
      admin.setUserid(adminPersonalDetailsDto.getUserid());

        UserDto userDto = new UserDto();
        userDto.setEmail(adminPersonalDetailsDto.getEmail());
        userDto.setFirstName(adminPersonalDetailsDto.getFirstName());
        userDto.setLastName(adminPersonalDetailsDto.getLastName());
        userDto.setIsActive(adminPersonalDetailsDto.getIsActive());
        userDto.setPassword(adminPersonalDetailsDto.getPassword());
        userDto.setRole("admin");

        ResponseEntity<UserDto> user=userServiceInterface.createUser(userDto);
     System.out.println(adminPersonalDetailsDto.toString());

        if(user.getStatusCode()!=HttpStatus.CREATED){
            response.put("message", Objects.requireNonNull(user.getBody()).toString());
            response.put("status", "error");
            return ResponseEntity.status(user.getStatusCode()).body(response);
        }
        Admin savedAdmin;
        UserDto userDto1=user.getBody();
        try {
            admin.setUserid(userDto1.getId());
            savedAdmin = adminRepository.save(admin);
        }catch (Exception e){
          response.put("message", "Unable to add try again");
          response.put("status", "error");
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return new ResponseEntity<>(adminMapper.toAdminPersonalDetails(savedAdmin,userDto), HttpStatus.CREATED);
    }

    // Get all Admins
    public ResponseEntity<?> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();

        if (admins.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No admins found");
        }

        List<AdminPersonalDetailsDto> adminDetailsList = new ArrayList<>();

        for (Admin admin : admins) {
            AdminPersonalDetailsDto adminPersonalDetailsDto = new AdminPersonalDetailsDto();
            adminPersonalDetailsDto.setUserid(admin.getUserid());
            adminPersonalDetailsDto.setId(admin.getId());

            // Fetch user details for each admin
            ResponseEntity<UserDto> userResponse = userServiceInterface.getUserById(admin.getUserid());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                UserDto userDto = userResponse.getBody();
                if (userDto != null) {
                    adminPersonalDetailsDto.setEmail(userDto.getEmail());
                    adminPersonalDetailsDto.setFirstName(userDto.getFirstName());
                    adminPersonalDetailsDto.setLastName(userDto.getLastName());
                    adminPersonalDetailsDto.setIsActive(userDto.getIsActive());
                }
            } else {
                adminPersonalDetailsDto.setEmail("N/A");
                adminPersonalDetailsDto.setFirstName("N/A");
                adminPersonalDetailsDto.setLastName("N/A");
            }

            adminDetailsList.add(adminPersonalDetailsDto);
        }

        return ResponseEntity.ok(adminDetailsList);
    }



    public ResponseEntity<?> getAdminById(Long id) {
        Optional<Admin> adminOptional=adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            AdminPersonalDetailsDto adminPersonalDetailsDto=new AdminPersonalDetailsDto();
            adminPersonalDetailsDto.setUserid(adminOptional.get().getUserid());
            adminPersonalDetailsDto.setId(adminOptional.get().getId());

            ResponseEntity<UserDto> userResponse= userServiceInterface.getUserById(adminPersonalDetailsDto.getUserid());
            if(userResponse.getStatusCode()==HttpStatus.OK){
                UserDto userDto= userResponse.getBody();
                if(userDto!=null) {
                    adminPersonalDetailsDto.setEmail(userDto.getEmail());
                    adminPersonalDetailsDto.setFirstName(userDto.getFirstName());
                    adminPersonalDetailsDto.setLastName(userDto.getLastName());
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin User personal details not found");
            }
            return ResponseEntity.ok(adminPersonalDetailsDto); // Return the DTO in response
        }

        // If admin is not found, return a 404 Not Found response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
    }

    // Update an existing Admin
    @Transactional
    public ResponseEntity<Object> updateAdmin(AdminPersonalDetailsDto adminPersonalDetailsDto) {
        Optional<Admin> existingAdminOptional = adminRepository.findById(adminPersonalDetailsDto.getId());
        Map<String, String> response = new HashMap<>();

        if (existingAdminOptional.isPresent()) {
                UserDto userDto=new UserDto();
                userDto.setId(adminPersonalDetailsDto.getUserid());
                userDto.setEmail(adminPersonalDetailsDto.getEmail());
                userDto.setFirstName(adminPersonalDetailsDto.getFirstName());
                userDto.setLastName(adminPersonalDetailsDto.getLastName());

                ResponseEntity<Object> user=userServiceInterface.updateUser(userDto);

                if(user.getStatusCode()!=HttpStatus.OK){
                    response.put("message", Objects.requireNonNull(user.getBody()).toString());
                    response.put("status", "error");
                    return ResponseEntity.status(user.getStatusCode()).body(response);
                }
            response.put("message","Admin Details Updated" );
            response.put("status", "success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.put("message","Admin Not Found" );
        response.put("status", "error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<Object> deleteAdmin(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        Map<String, String> response = new HashMap<>();

        if (admin.isPresent()) {

            ResponseEntity<?> user =userServiceInterface.deleteUser(admin.get().getUserid());

            response.put("message", Objects.requireNonNull(user.getBody()).toString());
            response.put("status", "error");
            return ResponseEntity.status(user.getStatusCode()).body(response);
        }
        response.put("message","Admin Not Found, consider deleted successfully" );
        response.put("status", "success");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<?> getAdminByUserid(Long userid) {
        Optional<Admin> adminOptional=adminRepository.findByUserid(userid);
        if (adminOptional.isPresent()) {
            AdminPersonalDetailsDto adminPersonalDetailsDto=new AdminPersonalDetailsDto();
            adminPersonalDetailsDto.setUserid(adminOptional.get().getUserid());
            adminPersonalDetailsDto.setId(adminOptional.get().getId());

            ResponseEntity<UserDto> userResponse= userServiceInterface.getUserById(adminPersonalDetailsDto.getUserid());
            if(userResponse.getStatusCode()==HttpStatus.OK){
                UserDto userDto= userResponse.getBody();
                if(userDto!=null) {
                    adminPersonalDetailsDto.setEmail(userDto.getEmail());
                    adminPersonalDetailsDto.setFirstName(userDto.getFirstName());
                    adminPersonalDetailsDto.setLastName(userDto.getLastName());
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin User personal details not found");
            }
            return ResponseEntity.ok(adminPersonalDetailsDto); // Return the DTO in response
        }

        // If admin is not found, return a 404 Not Found response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
    }

}
