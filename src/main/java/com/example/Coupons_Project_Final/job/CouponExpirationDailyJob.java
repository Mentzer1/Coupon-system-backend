package com.example.Coupons_Project_Final.job;

import com.example.Coupons_Project_Final.beans.Coupon;
import com.example.Coupons_Project_Final.beans.Customer;
import com.example.Coupons_Project_Final.repositories.CouponRepository;
import com.example.Coupons_Project_Final.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CouponExpirationDailyJob implements Runnable {


    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private boolean quit;

    @Override
    public void run() {

        try {
            quit = false;
            while (!quit) {
                List<Coupon> coupons = couponRepository.findAll();
                Date now = new Date(System.currentTimeMillis());
                List<Customer> customers = customerRepository.findAll();
                for (Coupon c : coupons) {
                    if (c.getEndDate().before(now)) {
                        for (Customer cust: customers){
                            List<Coupon> custCoupons = cust.getCoupons();
                            for (Coupon coup: custCoupons){
                                if (coup.getId()==c.getId()){
                                    custCoupons.remove(coup);
                                    customerRepository.save(cust);
                                }
                            }

                        }
                        couponRepository.delete(c);
                    }
                }
                try{
                    Thread.sleep(24*60*60*1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        quit = true;
    }

}

