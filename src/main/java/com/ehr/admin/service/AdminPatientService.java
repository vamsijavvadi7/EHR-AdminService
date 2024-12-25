package com.ehr.admin.service;

import com.ehr.admin.dto.AppointmentDto;
import com.ehr.admin.dto.PatientPersonalDetailsDto;
import com.ehr.admin.feign.AppointmentServiceInterface;
import com.ehr.admin.feign.PatientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPatientService {

    @Autowired
    private PatientInterface patientInterface;

    @Autowired
    private AppointmentServiceInterface appointmentServiceInterface;

    // Register a new patient
    public ResponseEntity<?> registerPatient(PatientPersonalDetailsDto patientPersonalDetailsDto) {
        return patientInterface.createPatient(patientPersonalDetailsDto);
    }

    // Update an existing patient
    public ResponseEntity<?> updatePatient(PatientPersonalDetailsDto patientPersonalDetailsDto) {
        return patientInterface.updatePatient(patientPersonalDetailsDto);
    }

    // Delete a patient
    public ResponseEntity<?> deletePatient(Long id) {
        return patientInterface.deletePatient(id);
    }

    // Retrieve a single patient by ID
    public ResponseEntity<PatientPersonalDetailsDto> getPatient(Long id) {
        return patientInterface.getPatient(id);
    }

    // Retrieve all patients
    public ResponseEntity<List<PatientPersonalDetailsDto>> getAllPatients() {
        return patientInterface.getAllPatients();
    }

    // Schedule an appointment for the patient
    public ResponseEntity<?> bookAppointment(AppointmentDto appointmentDto) {
        return appointmentServiceInterface.scheduleAppointment(appointmentDto);
    }
}
