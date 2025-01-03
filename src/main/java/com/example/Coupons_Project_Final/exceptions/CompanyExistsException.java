package com.example.Coupons_Project_Final.exceptions;

public class CompanyExistsException extends Exception{
    public CompanyExistsException(){
        super("Company name or email exists");
    }

}
