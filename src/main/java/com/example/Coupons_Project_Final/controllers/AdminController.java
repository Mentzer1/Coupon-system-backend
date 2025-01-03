package com.example.Coupons_Project_Final.controllers;

import com.example.Coupons_Project_Final.beans.Company;
import com.example.Coupons_Project_Final.beans.Customer;
import com.example.Coupons_Project_Final.exceptions.CompanyExistsException;
import com.example.Coupons_Project_Final.exceptions.CustomerExistsException;
import com.example.Coupons_Project_Final.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService = new AdminService();

    @PostMapping("/company")
    public void addCompany(@RequestBody Company company) throws CompanyExistsException {
        adminService.addCompany(company);
    }

    @PutMapping("/company")
    public void updateCompany(@RequestBody Company company) throws SQLException {
        adminService.updateCompany(company);
    }

    @DeleteMapping("/company/{id}")
    public void deleteCompany(@PathVariable int id) throws SQLException {
        adminService.deleteCompany(id);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() throws SQLException {
        return adminService.getAllCompanies();
    }

    @GetMapping("/company/{id}")
    public Company getOneCompany(@PathVariable int id) throws SQLException {
        System.out.println("I am controller");
        return adminService.getOneCompany(id);
    }

    @PostMapping("/customer")
    public String addCustomer(@RequestBody Customer customer) throws CustomerExistsException, SQLException {
        adminService.addCustomer(customer);
        return "Customer added";
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody Customer customer) throws SQLException {
        adminService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable int id) throws SQLException {
        adminService.deleteCustomer(id);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer() throws SQLException {
        return adminService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getOneCustomer(@PathVariable int id) throws SQLException {
        return adminService.getOneCustomer(id);
    }


}
