package com.ehr.admin.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {
    private Long id;

    private Long userid;

    @NotBlank
    private String phone;

    private AddressDto address;


    @Data
    @NoArgsConstructor
    public class AddressDto {
        private String street;
        private String city;
        private String state;
        private String postalCode;
    }

}

