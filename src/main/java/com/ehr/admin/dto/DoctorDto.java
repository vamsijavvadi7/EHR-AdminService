package com.ehr.admin.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DoctorDto {
    private Long id;
    private Long userid;
    private String specialization;
    private String phone;
    private DoctorAvailabilityDto availability;
}
