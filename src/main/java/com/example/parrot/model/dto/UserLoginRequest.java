package com.example.parrot.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest {

    private String userAccount;
    private String userPassword;

}
