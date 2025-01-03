package com.example.Coupons_Project_Final.services;

import com.example.Coupons_Project_Final.repositories.CompanyRepository;
import com.example.Coupons_Project_Final.repositories.CouponRepository;
import com.example.Coupons_Project_Final.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password) throws SQLException;

}
