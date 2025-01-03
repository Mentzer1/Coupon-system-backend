package com.example.Coupons_Project_Final.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = false)
public class Customer {
    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName, lastName, email, password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_coupon",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
    private List<Coupon> coupons;

    public Customer(String firstName, String lastName, String email, String password, List<Coupon> coupons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }
}
