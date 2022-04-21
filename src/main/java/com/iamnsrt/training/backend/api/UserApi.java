package com.iamnsrt.training.backend.api;

import com.iamnsrt.training.backend.business.UserBusiness;
import com.iamnsrt.training.backend.exception.BaseException;
import com.iamnsrt.training.backend.model.LoginRequest;
import com.iamnsrt.training.backend.model.RegisterRequest;
import com.iamnsrt.training.backend.model.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws BaseException {
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) throws BaseException {
        RegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh-token")
    public  ResponseEntity<String> refreshToken() throws BaseException{
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
