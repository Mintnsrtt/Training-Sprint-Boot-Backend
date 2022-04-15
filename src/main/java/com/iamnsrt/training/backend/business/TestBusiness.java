package com.iamnsrt.training.backend.business;

import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.exception.FileException;
import com.iamnsrt.training.backend.exception.UserException;
import com.iamnsrt.training.backend.model.RegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TestBusiness {
    public String register(RegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }

        //validate email
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }

        //validate password
        if (Objects.isNull(request.getPassword())) {
            throw UserException.passwordNull();
        }

        //validate name
        if (Objects.isNull(request.getName())) {
            throw UserException.nameNull();
        }

        return "";
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

        //TODO: uplode file File Storage (AWS S3, etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "";
    }
}
