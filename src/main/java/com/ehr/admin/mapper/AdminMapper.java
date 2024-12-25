package com.ehr.admin.mapper;

import com.ehr.admin.dto.AdminDto;
import com.ehr.admin.dto.AdminPersonalDetailsDto;
import com.ehr.admin.dto.UserDto;
import com.ehr.admin.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


public interface AdminMapper {


    AdminDto toDto(Admin admin);

    AdminPersonalDetailsDto toAdminPersonalDetails(Admin admin, UserDto userDto);


    Admin toEntity(AdminDto adminDto);
}

