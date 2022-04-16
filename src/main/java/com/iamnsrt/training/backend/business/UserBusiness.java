package com.iamnsrt.training.backend.business;

import com.iamnsrt.training.backend.entity.User;
import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.exception.FileException;
import com.iamnsrt.training.backend.exception.UserException;
import com.iamnsrt.training.backend.mapper.UserMapper;
import com.iamnsrt.training.backend.model.LoginRequest;
import com.iamnsrt.training.backend.model.RegisterRequest;
import com.iamnsrt.training.backend.model.RegisterResponse;
import com.iamnsrt.training.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public String login(LoginRequest request) throws BaseException{
        //validate request

        //verify database
        Optional<User> otp = userService.findByEmail(request.getEmail());

        if (otp.isEmpty()) {
            throw UserException.loginFailEmailNotFound();
        }

        User user = otp.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.loginFailPasswordIncorrect();
        }

        //TODO: generate JWT
        String token = "JWT TO DO";

        return token;
    }

    public RegisterResponse register(RegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        return userMapper.toRegisterResponse(user);
    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException{
        //validate file
        if (file == null) {
            //throw error
            FileException.fileNull();
        }

        if (file.getSize() > 1048576 * 2 ) {
            //throw error
            FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            //throw error
            FileException.unsupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpag", "image/png");
        if (!supportedTypes.contains(contentType)) {
            //throw error
            FileException.unsupported();
        }

        //TODO: upload file File Storage (AWS S3, etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "";
    }
}
