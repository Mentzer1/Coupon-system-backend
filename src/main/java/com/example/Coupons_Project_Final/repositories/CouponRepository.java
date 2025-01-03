package com.example.Coupons_Project_Final.repositories;

import com.example.Coupons_Project_Final.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByCompanyID(int companyID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `couponsdbspring`.`customer_coupon` WHERE customer_id = ?;", nativeQuery = true)
    void deleteAllPurchasesCouponsByCustomerId(long customerId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `couponsdbspring`.`customer_coupon` WHERE coupon_id = ?;", nativeQuery = true)
    void deleteAllPurchasesCouponsByCouponId(long couponId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `couponsdbspring`.`customer_coupon` WHERE coupon_id IN (select id from `coupons` where companyid = ?)", nativeQuery = true)
    void deletePurchasedCouponsByCompanyId(long companyId);
}
