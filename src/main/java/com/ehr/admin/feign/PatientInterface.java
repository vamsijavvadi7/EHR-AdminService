package com.ehr.admin.feign;

import com.ehr.admin.dto.PatientPersonalDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("patient")
public interface PatientInterface {

    @PostMapping("/patients/create")
    ResponseEntity<Object> createPatient(@RequestBody PatientPersonalDetailsDto patientPersonalDetailsDto);

    @PutMapping("/patients/update")
    ResponseEntity<Object> updatePatient(@RequestBody PatientPersonalDetailsDto patientPersonalDetailsDto);

    @DeleteMapping("/patients/{id}")
    ResponseEntity<Object> deletePatient(@PathVariable Long id);

    @GetMapping("/patients/{id}")
    ResponseEntity<PatientPersonalDetailsDto> getPatient(@PathVariable Long id);

    @GetMapping("/patients/getAll")
    ResponseEntity<List<PatientPersonalDetailsDto>> getAllPatients();
}
