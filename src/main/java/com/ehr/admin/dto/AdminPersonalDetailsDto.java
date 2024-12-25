package com.ehr.admin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminPersonalDetailsDto {
    private Long id;
    private Long userid;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String password;
}
