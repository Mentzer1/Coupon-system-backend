package com.example.Coupons_Project_Final.services;

import com.example.Coupons_Project_Final.beans.Category;
import com.example.Coupons_Project_Final.beans.Company;
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
public class CompanyService extends ClientService {

    private int companyID;


    @Override
    public boolean login(String email, String password) throws SQLException {
        if(companyRepository.existsByEmailAndPassword(email, password)){
            List<Company> companies = companyRepository.findAll();
            for (Company c: companies){
                if (c.getEmail().equals(email)){
                    companyID = c.getId();
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void addCoupon(Coupon coupon) throws SQLException, CouponExistsException, InvalidCouponException {
        if (coupon.getCompanyID() == companyID) { //Check to see if the coupon's company id matches ours, if not then stop it now
            List<Coupon> coupons = getCompanyCoupons(); //get list of all coupons
            if (coupons != null) {//if company has coupons...
                for (Coupon c : coupons) {//...check all of them...
                    if (c.getTitle().equals(coupon.getTitle()))//if already exists
                        throw new CouponSystemException("Coupon already exists for this company");//throw the appropriate exception
                }
            }
            if (coupon.getEndDate().before(coupon.getStartDate())){
                throw new CouponSystemException("End date cannot be before start date!");
            }
            if (coupon.getStartDate().before(Date.valueOf(LocalDate.now()))){
                throw new CouponSystemException("Start date cannot be before now!");
            }
            couponRepository.save(coupon);//if all went well add the coupon
        } else {
            throw new CouponSystemException("Coupon doesn't belong to this company");
        }
    }
    public void updateCoupon(Coupon coupon) throws CouponSystemException, SQLException {
        if (coupon.getCompanyID() == companyID) { //Check to see if the coupon's company id matches ours, if not then stop it now
            List<Coupon> coupons = getCompanyCoupons(); //get list of all coupons
            if (coupons != null) {//if company has coupons...
                for (Coupon c : coupons) {//...check all of them...
                    if (c.getId()==(coupon.getId())) {//if already exists
                        couponRepository.save(coupon);//if all went well add the coupon
                    }
                }
            }
        } else {
            throw new CouponSystemException("Coupon doesn't belong to this company");

        }
    }
    public void deleteCoupon(int couponID) throws SQLException, CouponSystemException {
        Coupon coupon = couponRepository.findById(couponID).orElseThrow(); // get the coupon through the id
//        List<Customer> customers = customerRepository.findAll(); // get all customers

        if (coupon.getCompanyID() == companyID){ // if the coupon belongs to this company
//            for (Customer cust: customers){ // then for each customer
//                List<Coupon> coupons = cust.getCoupons(); // get his list of coupons
//                for (Coupon coup: coupons){ // for each of those coupons
//                    if (coup.getId()==couponID){ // check if its the coupon we're trying to delete
//                        coupons.remove(coup); // if so then remove it from the customer coupons
//                        cust.setCoupons(coupons); //set the updated coupons list
//                        customerRepository.save(cust); // and save to the database
//                    }
//                }
//            }
            couponRepository.deleteAllPurchasesCouponsByCouponId(couponID);
            couponRepository.delete(coupon); // after all that safely delete the coupon
        } else {
            throw new CouponSystemException("Coupon doesn't belong to this company");
        }
    }

    public List<Coupon> getCompanyCoupons() throws SQLException {
        return couponRepository.findByCompanyID(companyID); //get list of all coupons
    }

    public List<Coupon> getCompanyCoupons(Category category) throws SQLException {
        List<Coupon> allCoupons = getCompanyCoupons(); // get list of all coupons
        ArrayList<Coupon> companyCoupons = new ArrayList<>(); //create a new temporary list
        for (Coupon c: allCoupons){ //for each of the company coupons
            if (c.getCategory().equals(category)){ // if its category matches the category we want
                companyCoupons.add(c); // add it to the temporary list
            }
        }
        return companyCoupons; // and return it


    }
    public List<Coupon> getCompanyCoupons(double maxPrice) throws SQLException {
        List<Coupon> allCoupons = getCompanyCoupons(); // get list of all coupons
        ArrayList<Coupon> companyCoupons = new ArrayList<>();//create a new temporary list
        for (Coupon c: allCoupons){ // for each of the company coupons
            if (c.getPrice()<=maxPrice){ //if its under the price we want
                companyCoupons.add(c); // add it to the temporary list
            }
        }
        return companyCoupons; // and return it

    }
    public Company getCompanyDetails() throws SQLException {
        return companyRepository.findById(companyID).orElseThrow();
    }


}
