package com.example.Coupons_Project_Final.services;

import com.example.Coupons_Project_Final.beans.Company;
import com.example.Coupons_Project_Final.beans.Coupon;
import com.example.Coupons_Project_Final.beans.Customer;
import com.example.Coupons_Project_Final.exceptions.CompanyExistsException;
import com.example.Coupons_Project_Final.exceptions.CouponSystemException;
import com.example.Coupons_Project_Final.exceptions.CustomerExistsException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AdminService extends ClientService{

    @Override
    public boolean login(String email, String password) throws SQLException {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    public String addCompany(Company company) throws CompanyExistsException {
        List<Company> companies = companyRepository.findAll(); //get all companies
        for (Company comp : companies){ // for each company
            if (comp.getName().equals(company.getName()) // if the name equals the name of the company we are trying to add
                    || comp.getEmail().equals(company.getEmail())) // or if the email is the same
                throw new CouponSystemException("Company already exists"); //throw an exception
        }
        companyRepository.save(company); //else add the company
        return "New Company Added";
    }
    public String updateCompany(Company company) throws SQLException{
        Company baseComp = companyRepository.findById(company.getId()).orElseThrow(); // fetch company's original data from DB
        String origName = baseComp.getName(); // save the original name
        String newName = company.getName(); // save the new name
        company.setName(origName); // set the new name as the original name (temporarily)
        companyRepository.save(company); // update the company without changing the name
        company.setName(newName);// return the new name to the company seemingly untouched
        return "Company updated!";
    }

    public String deleteCompany(int companyID) throws SQLException {
        couponRepository.deletePurchasedCouponsByCompanyId(companyID);
        companyRepository.deleteById(companyID);
        return "Company deleted";
    }

    public List<Company> getAllCompanies() throws SQLException {
        return companyRepository.findAll();
    }

    public Company getOneCompany(int companyID) throws SQLException {
        return companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemException("Company doesn't exist"));
    }

    public String addCustomer(Customer customer) throws SQLException, CustomerExistsException {
        List<Customer> customers = customerRepository.findAll(); // get all customers

        for (Customer cust : customers){ // for each customer
            if (cust.getEmail().equals(customer.getEmail())) //if the email equals the email of the customer we are trying to add
                throw new CouponSystemException("Email already exists"); // throw an exception
        }
        customerRepository.save(customer); //else add the customer
        return "New Customer added";
    }
    public void updateCustomer(Customer customer) throws SQLException {
        if (customerRepository.existsById(customer.getId())) // if the customer does already exists
            customerRepository.save(customer); // save the new data
        else
            throw new CouponSystemException("Customer not found!"); // else dont
    }
    public String deleteCustomer(int customerID) throws SQLException {
        customerRepository.deleteById(customerID);
        return "Customer deleted";
    }
    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepository.findAll();
    }
    public Customer getOneCustomer(int customerID) throws SQLException {
        return customerRepository.findById(customerID).orElseThrow();
    }
    public Coupon getOneCoupon(int couponID) throws SQLException {
        return couponRepository.findById(couponID).orElseThrow();
    }

}
