package com.ehr.admin.mapper;



import com.ehr.admin.dto.AdminDto;
import com.ehr.admin.dto.AdminPersonalDetailsDto;
import com.ehr.admin.dto.UserDto;
import com.ehr.admin.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminDto toDto(Admin admin) {
        if (admin == null) {
            return null;  // Return null if the Admin object is null
        }
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setUserid(admin.getUserid());
        return adminDto;
    }

    @Override
    public Admin toEntity(AdminDto adminDto) {
        if (adminDto == null) {
            return null;  // Return null if the AdminDto object is null
        }

        Admin admin = new Admin();
        admin.setId(adminDto.getId());
        admin.setUserid(adminDto.getUserid());
        return admin;
    }


    public AdminPersonalDetailsDto toAdminPersonalDetails(Admin admin, UserDto userDto) {
            if (admin == null || userDto == null) {
                throw new IllegalArgumentException("Admin and UserDto must not be null");
            }

            AdminPersonalDetailsDto adminPersonalDetailsDto = new AdminPersonalDetailsDto();
            adminPersonalDetailsDto.setId(admin.getId());
            adminPersonalDetailsDto.setUserid(admin.getUserid());
            adminPersonalDetailsDto.setEmail(userDto.getEmail());
            adminPersonalDetailsDto.setFirstName(userDto.getFirstName());
            adminPersonalDetailsDto.setLastName(userDto.getLastName());
            adminPersonalDetailsDto.setIsActive(userDto.getIsActive());
            adminPersonalDetailsDto.setPassword(userDto.getPassword());
            return adminPersonalDetailsDto;
    }


}
