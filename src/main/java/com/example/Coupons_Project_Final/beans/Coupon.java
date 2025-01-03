package com.example.Coupons_Project_Final.beans;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class Coupon {
    @Setter(AccessLevel.PRIVATE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int companyID;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    private String title, description;
    private Date startDate, endDate;
    private int amount;
    private double price;
    private String image;

    public Coupon(int companyID, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.companyID = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }
}
