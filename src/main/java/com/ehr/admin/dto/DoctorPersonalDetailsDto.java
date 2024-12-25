package com.ehr.admin.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DoctorPersonalDetailsDto {
    private Long id;
    private Long userid;
    private String email;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phone;
    private Boolean isActive;
    private DoctorAvailabilityDto availability;
}
