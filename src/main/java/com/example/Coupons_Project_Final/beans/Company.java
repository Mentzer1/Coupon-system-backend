package com.example.Coupons_Project_Final.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
public class Company {
    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name, email, password;
    @OneToMany(mappedBy = "companyID", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Coupon> coupons;

    public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

}
