package com.ehr.admin.service;

import com.ehr.admin.dto.DoctorAvailabilityDto;
import com.ehr.admin.dto.DoctorDto;
import com.ehr.admin.dto.DoctorPersonalDetailsDto;
import com.ehr.admin.feign.DoctorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminDoctorService {

    @Autowired
    private DoctorServiceClient doctorServiceClient;

    public ResponseEntity<?> deleteDoctor(Long id) {
        return doctorServiceClient.deleteDoctor(id);
    }

    public ResponseEntity<?> createDoctor(DoctorPersonalDetailsDto doctorPersonalDetailsDto) {
        return doctorServiceClient.createDoctor(doctorPersonalDetailsDto);
    }

    public ResponseEntity<?> updateDoctor(DoctorPersonalDetailsDto doctorPersonalDetailsDto) {
        return doctorServiceClient.updateDoctor(doctorPersonalDetailsDto);
    }

    public ResponseEntity<?> getAllDoctors() {
        return doctorServiceClient.getAllDoctors();
    }

    public ResponseEntity<?> getAvailableDoctors(DoctorAvailabilityDto availabilityDto) {
        return doctorServiceClient.getAvailableDoctors(availabilityDto);
    }

    public ResponseEntity<?> getDoctorByUserId(Long userid) {
        return doctorServiceClient.getDoctorByUserid(userid);
    }

    public ResponseEntity<?> getAvailableDoctorsByDate(LocalDate dateParam) {
        return doctorServiceClient.getAvailableDoctorsByDate(dateParam);
    }
}
