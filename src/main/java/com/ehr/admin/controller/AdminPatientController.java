package com.ehr.admin.controller;

import com.ehr.admin.dto.AppointmentDto;
import com.ehr.admin.dto.PatientPersonalDetailsDto;
import com.ehr.admin.service.AdminPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminpatient")
public class AdminPatientController {

    @Autowired
    private AdminPatientService adminPatientService;

    // Endpoint to register a new patient
    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(@RequestBody PatientPersonalDetailsDto patientPersonalDetailsDto) {
        return adminPatientService.registerPatient(patientPersonalDetailsDto);
    }

    // Endpoint to update an existing patient
    @PutMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestBody PatientPersonalDetailsDto patientPersonalDetailsDto) {
        return adminPatientService.updatePatient(patientPersonalDetailsDto);
    }

    // Endpoint to delete a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        return adminPatientService.deletePatient(id);
    }

    // Endpoint to get details of a single patient
    @GetMapping("/{id}")
    public ResponseEntity<PatientPersonalDetailsDto> getPatient(@PathVariable Long id) {
        return adminPatientService.getPatient(id);
    }

    // Endpoint to get all patients
    @GetMapping("/all")
    public ResponseEntity<List<PatientPersonalDetailsDto>> getAllPatients() {
        return adminPatientService.getAllPatients();
    }

    // Endpoint to book an appointment for a patient
    @PostMapping("/bookAppointment")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
        return adminPatientService.bookAppointment(appointmentDto);
    }
}
