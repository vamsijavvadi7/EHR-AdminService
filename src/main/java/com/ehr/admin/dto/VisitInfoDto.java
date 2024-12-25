package com.ehr.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VisitInfoDto {
    private Long id;
    private Long appointmentId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String notes;
}
