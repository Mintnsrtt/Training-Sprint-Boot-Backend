package com.iamnsrt.training.backend.mapper;

import com.iamnsrt.training.backend.entity.User;
import com.iamnsrt.training.backend.model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponse toRegisterResponse(User user);
}
