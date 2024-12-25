package com.ehr.admin.feign;

import com.ehr.admin.config.GlobalFeignConfig;
import com.ehr.admin.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users", configuration = GlobalFeignConfig.class)
public interface UserServiceInterface {
    @GetMapping("/users/getuserbyid/{userid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userid);

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id);

    @PutMapping("/users/updateuser")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto);

    @PostMapping("/users/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);
}
