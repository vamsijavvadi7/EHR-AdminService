package com.ehr.admin.feign;

import com.ehr.admin.dto.AppointmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("appointmentservice")
public interface AppointmentServiceInterface {
    @PostMapping("/appointments/create")
    public ResponseEntity<?> scheduleAppointment(@RequestBody AppointmentDto appointmentDto);
}