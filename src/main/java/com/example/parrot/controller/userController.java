package com.example.parrot.controller;


import com.example.parrot.exception.BusinessException;
import com.example.parrot.model.dto.UserLoginRequest;
import com.example.parrot.model.dto.UserRegisterRequest;
import com.example.parrot.model.entity.User;
import com.example.parrot.model.vo.LoginUserVO;
import com.example.parrot.result.BaseResponse;
import com.example.parrot.result.ErrorCode;
import com.example.parrot.result.ResultUtil;
import com.example.parrot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLogin, HttpServletRequest request) {
        if (request == null || userLogin == null || StringUtils.isAnyBlank(userLogin.getUserAccount(), userLogin.getUserPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLogin.getUserAccount();
        String userPassword = userLogin.getUserPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtil.success(loginUserVO);
    }

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegister) {
        if (userRegister == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegister.getUserAccount();
        String userPassword = userRegister.getUserPassword();
        String checkPassword = userRegister.getCheckPassword();
        Long id = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtil.success(id);
    }

    @GetMapping("/getLoginUser")
    public BaseResponse<User> getLoginUser(HttpServletRequest request) {
        if (request == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        return ResultUtil.success(userService.getLoginUser(request));
    }


    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request){
        if(request == null) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        return ResultUtil.success(userService.userLogout(request));
    }
}
