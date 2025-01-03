package com.example.Coupons_Project_Final.controllers;

import com.example.Coupons_Project_Final.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class AllExceptionsHandler {

    @ExceptionHandler(CouponSystemException.class)
    public ResponseEntity<String> handleCouponSystemException(CouponSystemException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    @ExceptionHandler(CompanyExistsException.class)
    public ResponseEntity<String> handleCompanyException(CompanyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CouponExistsException.class)
    public ResponseEntity<String> handleCouponException(CouponExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomerExistsException.class)
    public ResponseEntity<String> handleCustomerException(CustomerExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCouponException.class)
    public ResponseEntity<String> handleInvalidCouponException(InvalidCouponException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong, please try again later");
    }

}
