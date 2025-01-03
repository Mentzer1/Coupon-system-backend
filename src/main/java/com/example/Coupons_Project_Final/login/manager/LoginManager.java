package com.example.Coupons_Project_Final.login.manager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Coupons_Project_Final.exceptions.CouponSystemException;
import com.example.Coupons_Project_Final.security.TokenManager;
import com.example.Coupons_Project_Final.services.AdminService;
import com.example.Coupons_Project_Final.services.ClientService;
import com.example.Coupons_Project_Final.services.CompanyService;
import com.example.Coupons_Project_Final.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;


@Service
public class LoginManager {

//    @Autowired
//    public List<String> activeTokens;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ApplicationContext ctx;

    public String login(String email, String password, ClientType clientType) throws SQLException {

        switch (clientType) {
            case Administrator -> {
                AdminService adminService = ctx.getBean(AdminService.class);
                if (adminService.login(email, password)) {
                    String token = tokenManager.createToken(adminService);
                    tokenManager.addActiveToken(token);
                    return token;
                }
                throw new CouponSystemException("Email or password do not match");
            }
            case Company -> {
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (companyService.login(email, password)) {
                    String token = tokenManager.createToken(companyService);
                    tokenManager.addActiveToken(token);
                    return token;
                }
                throw new CouponSystemException("Email or password do not match");
            }
            case Customer -> {
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(email, password)) {
                    String token = tokenManager.createToken(customerService);
                    tokenManager.addActiveToken(token);
                    return token;
                }
                throw new CouponSystemException("Email or password do not match");
            }
        }
        return null;
    }
}
