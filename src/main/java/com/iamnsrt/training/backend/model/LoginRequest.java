package com.iamnsrt.training.backend.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}
