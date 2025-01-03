package com.example.Coupons_Project_Final.repositories;

import com.example.Coupons_Project_Final.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmailAndPassword(String email, String password);
}
