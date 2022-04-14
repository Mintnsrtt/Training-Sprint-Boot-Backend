package com.iamnsrt.training.backend.business;

import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.exception.UserException;
import com.iamnsrt.training.backend.model.RegisterRequest;
import org.springframework.stereotype.Service;

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
}
