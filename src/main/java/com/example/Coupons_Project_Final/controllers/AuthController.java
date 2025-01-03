package com.example.Coupons_Project_Final.controllers;

import com.example.Coupons_Project_Final.security.TokenManager;
import com.example.Coupons_Project_Final.login.manager.ClientType;
import com.example.Coupons_Project_Final.login.manager.LoginManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password,@RequestParam ClientType clientType) throws SQLException, LoginException {
        return loginManager.login(email,password,clientType);
    }

    @DeleteMapping("/logout")
    public void logout(@RequestParam String token){
        tokenManager.removeActiveToken(token);
    }

//    HttpServletRequest request,
//    int companyId = (Integer) request.getAttribute("id");
}
