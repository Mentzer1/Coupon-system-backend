package com.example.Coupons_Project_Final.controllers;

import com.example.Coupons_Project_Final.beans.Category;
import com.example.Coupons_Project_Final.beans.Company;
import com.example.Coupons_Project_Final.beans.Coupon;
import com.example.Coupons_Project_Final.exceptions.CouponExistsException;
import com.example.Coupons_Project_Final.exceptions.InvalidCouponException;
import com.example.Coupons_Project_Final.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService = new CompanyService();

    @PostMapping("/coupon")
    public void addCoupon(@RequestBody Coupon coupon) throws InvalidCouponException, SQLException, CouponExistsException {
        companyService.addCoupon(coupon);
    }

    @PutMapping("/coupon")
    public void updateCoupon(@RequestBody Coupon coupon) throws InvalidCouponException, SQLException {
        companyService.updateCoupon(coupon);
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable int id) throws InvalidCouponException, SQLException {
        companyService.deleteCoupon(id);
    }

    @GetMapping("/coupons")
    public List<Coupon> getCompanyCoupons() throws SQLException {
        return companyService.getCompanyCoupons();
    }

    @GetMapping("/coupons/category")
    public List<Coupon> getCompanyCoupons(@RequestParam Category category) throws SQLException {
        return companyService.getCompanyCoupons(category);
    }

    @GetMapping("/coupons/max_price")
    public List<Coupon> getCompanyCoupons(@RequestParam double maxPrice) throws SQLException {
        return companyService.getCompanyCoupons(maxPrice);
    }

    @GetMapping("/details")
    public Company getCompanyDetails() throws SQLException {
        return companyService.getCompanyDetails();
    }
}
