package com.example.parrot.model.vo;

import com.example.parrot.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVO {


    private String userAccount;
    private String userName;
    private String userRole;


}
