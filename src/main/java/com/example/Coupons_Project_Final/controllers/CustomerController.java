package com.example.Coupons_Project_Final.controllers;

import com.example.Coupons_Project_Final.beans.Category;
import com.example.Coupons_Project_Final.beans.Coupon;
import com.example.Coupons_Project_Final.beans.Customer;
import com.example.Coupons_Project_Final.exceptions.CouponExistsException;
import com.example.Coupons_Project_Final.exceptions.InvalidCouponException;
import com.example.Coupons_Project_Final.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService = new CustomerService();

    @PostMapping
    public Coupon purchaseCoupon(@RequestBody Coupon coupon) throws InvalidCouponException, SQLException, CouponExistsException {
        return customerService.purchaseCoupon(coupon);
    }

    @GetMapping("coupons")
    public List<Coupon> getCustomerCoupons() throws SQLException {
       return customerService.getCustomerCoupons();
    }

    @GetMapping("coupons/category")
    public List<Coupon> getCustomerCoupons(@RequestParam Category category) throws SQLException {
        return customerService.getCustomerCoupons(category);
    }

    @GetMapping("coupons/max_price")
    public List<Coupon> getCustomerCoupons(@RequestParam double maxPrice) throws SQLException {
        return customerService.getCustomerCoupons(maxPrice);
    }

    @GetMapping("/details")
    public Customer getCustomerDetails() throws SQLException {
        return customerService.getCustomerDetails();
    }

    @GetMapping("all_coupons")
    public List<Coupon> getAllCoupons(){
        return customerService.getAllCoupons();
    }

    @GetMapping("/company_name/{id}")
    public String getCompanyName(@PathVariable int id){
        return customerService.getCompanyName(id);
    }

}
