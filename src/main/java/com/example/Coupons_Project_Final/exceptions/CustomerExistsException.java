package com.example.Coupons_Project_Final.exceptions;

public class CustomerExistsException extends Exception{
    public CustomerExistsException(){
        super("Customer already in the system");
    }
}
