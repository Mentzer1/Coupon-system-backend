package com.example.Coupons_Project_Final.repositories;

import com.example.Coupons_Project_Final.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByEmailAndPassword(String email, String password);
}
