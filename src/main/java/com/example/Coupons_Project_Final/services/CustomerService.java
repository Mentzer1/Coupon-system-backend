package com.example.Coupons_Project_Final.services;

import com.example.Coupons_Project_Final.beans.Category;
import com.example.Coupons_Project_Final.beans.Coupon;
import com.example.Coupons_Project_Final.beans.Customer;
import com.example.Coupons_Project_Final.exceptions.CouponExistsException;
import com.example.Coupons_Project_Final.exceptions.CouponSystemException;
import com.example.Coupons_Project_Final.exceptions.InvalidCouponException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService extends ClientService {
    
    private int customerID;

    @Override
    public boolean login(String email, String password) throws SQLException {
        if(customerRepository.existsByEmailAndPassword(email, password)){
            List<Customer> customers = customerRepository.findAll();
            for (Customer c: customers){
                if (c.getEmail().equals(email)){
                    customerID = c.getId();
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Coupon purchaseCoupon(Coupon coupon) throws SQLException, CouponExistsException, InvalidCouponException {
        List<Coupon> coupons = getCustomerCoupons(); // get all the coupons of the customer
        Date now = Date.valueOf(LocalDate.now());
        for (Coupon coup: coupons){ //for each coupon of the customer
            if (coup.getId() == coupon.getId()){ // if its id matches that of the coupon we are trying to add
                throw new CouponSystemException("You cant purchase more than 1 of the same coupon"); // throw an exception
            }
        }
        if (coupon.getAmount() == 0){ // if its out of stock
            throw new CouponSystemException("Coupon out of stock"); // throw an exception
        }
        if (coupon.getEndDate().before(now)){// if its end date passed
            throw new CouponSystemException("Coupon out of date"); // throw an exception
        }

        //else

        coupons.add(coupon); // add it to its list of coupons
        Customer customer = getCustomerDetails(); //get the customer
        customer.setCoupons(coupons); // and set the updated list of coupons
        customerRepository.save(customer); // and save it to the database
        coupon.setAmount(coupon.getAmount()-1); // update the stock of the coupon
        return couponRepository.save(coupon); // and save it to the database

    }

    public List<Coupon> getCustomerCoupons() throws SQLException {
        Customer customer = customerRepository.findById(customerID).orElseThrow();
        return customer.getCoupons();
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException {
        List<Coupon> coupons = getCustomerCoupons(); // get all the coupons of the customer

        ArrayList<Coupon> categoryCoupons = new ArrayList<>(); //create a new temporary list
        for (Coupon coupon: coupons){ // for each of the customer coupons
            if (coupon.getCategory().equals(category)){ // if its category matches the category we want
                categoryCoupons.add(coupon); // add it to the temporary list
            }
        }
        return categoryCoupons; // and return it
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException {
        List<Coupon> coupons = getCustomerCoupons();// get all the coupons of the customer

        ArrayList<Coupon> pricedCoupons = new ArrayList<>(); //create a new temporary list
        for (Coupon coupon: coupons){ // for each of the customer coupons
            if (coupon.getPrice() <= maxPrice){ // if its under the price we want
                pricedCoupons.add(coupon);// add it to the temporary list
            }
        }
        return pricedCoupons; // and return it
    }


    public Customer getCustomerDetails() throws SQLException {
        return customerRepository.findById(customerID).orElseThrow();
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }

    public String getCompanyName(int id){
        return companyRepository.findById(id).orElseThrow(() -> new CouponSystemException("Company doesn't exist")).getName();
    }



}
