package com.ehr.admin.controller;

import com.ehr.admin.dto.DoctorAvailabilityDto;
import com.ehr.admin.dto.DoctorDto;
import com.ehr.admin.dto.DoctorPersonalDetailsDto;
import com.ehr.admin.service.AdminDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("admin-doctor")
public class AdminDoctorController {

    @Autowired
    private AdminDoctorService adminDoctorService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        return adminDoctorService.deleteDoctor(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorPersonalDetailsDto doctorPersonalDetailsDto) {
        return adminDoctorService.createDoctor(doctorPersonalDetailsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorPersonalDetailsDto doctorPersonalDetailsDto) {
        return adminDoctorService.updateDoctor(doctorPersonalDetailsDto);
    }

    @GetMapping("/allDoctors")
    public ResponseEntity<?> getAllDoctors() {
        return adminDoctorService.getAllDoctors();
    }

    @GetMapping("/userid/{userid}")
    public ResponseEntity<?> getDoctorByUserId(@PathVariable Long userid) {
        return adminDoctorService.getDoctorByUserId(userid);
    }
    @GetMapping("/availableDoctorsByDate")
    public ResponseEntity<?> getAvailableDoctorsByDate(@RequestParam @DateTimeFormat(pattern = "MM-dd-yy") LocalDate dateParam) {
        return adminDoctorService.getAvailableDoctorsByDate(dateParam);
    }
    @PostMapping("/availableDoctors")
    public ResponseEntity<?> getAvailableDoctors(@RequestBody DoctorAvailabilityDto availabilityDto) {
        return adminDoctorService.getAvailableDoctors(availabilityDto);
    }
}
