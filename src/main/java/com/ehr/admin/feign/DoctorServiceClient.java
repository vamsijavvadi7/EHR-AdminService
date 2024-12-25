package com.ehr.admin.feign;

import com.ehr.admin.dto.DoctorAvailabilityDto;
import com.ehr.admin.dto.DoctorDto;
import com.ehr.admin.dto.DoctorPersonalDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient("doctorservice")
public interface DoctorServiceClient {

    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id);

    @PostMapping("/doctor/create")
    public ResponseEntity<?> createDoctor(@RequestBody DoctorDto doctorDto);

    @PutMapping("/doctor/update")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorPersonalDetailsDto doctorPersonalDetailsDto);

    @GetMapping("/doctor/allDoctors")
    public ResponseEntity<?> getAllDoctors();

    @PostMapping("/doctor/availableDoctors")
    public ResponseEntity<?> getAvailableDoctors(@RequestBody DoctorAvailabilityDto availabilityDto);

    @GetMapping("/doctor/availableDoctorsByDate")
    public ResponseEntity<?> getAvailableDoctorsByDate(@RequestParam @DateTimeFormat(pattern = "MM-dd-yy")  LocalDate dateParam);

    @GetMapping("/doctor/userid/{userid}")
    public ResponseEntity<?> getDoctorByUserid(@PathVariable Long userid);
}
