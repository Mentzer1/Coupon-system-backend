//package com.example.Coupons_Project_Final.test;
//
//import com.example.Coupons_Project_Final.job.CouponExpirationDailyJob;
//import com.example.Coupons_Project_Final.login.manager.ClientType;
//import com.example.Coupons_Project_Final.login.manager.LoginManager;
//import com.example.Coupons_Project_Final.services.AdminService;
//import com.example.Coupons_Project_Final.services.CompanyService;
//import com.example.Coupons_Project_Final.services.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.sql.SQLException;
//
//@Component
//public class Test {
//
//    @Autowired
//    private ApplicationContext ctx;
//
//    public void testAll() throws SQLException{
//
//        LoginManager loginManager = ctx.getBean(LoginManager.class);
//        CouponExpirationDailyJob couponExpirationDailyJob = ctx.getBean(CouponExpirationDailyJob.class);
//
//        Thread thread = new Thread(couponExpirationDailyJob);
//        thread.start();
//
//        try {
//            AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
//            CustomerService customerService = (CustomerService) loginManager.login("yuval@yuval.com", "123456", ClientType.Customer);
//            CompanyService companyService = (CompanyService) loginManager.login("tacobell@tacos.com", "taco1234", ClientType.Company);
//
//            /*
//            Admin Service
//             */
//
////            adminService.addCompany(new Company("AAA", "AA@aaa.com", "12345", null));//WORKS
//
////            adminService.updateCompany(new Company(1, "BBB", "BBB@BBB.com","54321",null)); //WORKs
//
////            adminService.deleteCompany(4);//WORKS
//
////            System.out.println(adminService.getAllCompanies());//WORKs
//
////            System.out.println(adminService.getOneCompany(3));//WORKS
//
////            adminService.addCustomer(new Customer("Yuval", "Mentzer", "yuval@yuval.com", "123456",null)); //WORKS
//
////            adminService.updateCustomer(new Customer(3,"Youval", "MAntzur", "yuval@yuval.com","1234567", null)); //WORKS
//
////            adminService.deleteCustomer(3); //  WORKS
//
////            System.out.println(adminService.getAllCustomers());//WORKS
//
////            System.out.println(adminService.getOneCustomer(4));//WORKS
//
////            System.out.println(adminService.getOneCoupon(2)); //WORKS
//
//            /*
//            Company Service
//             */
//
////            Date startDate = new Date(124,05,06);
////            Date endDate = new Date(125,05,06);
////            Coupon coupon = new Coupon(3, Category.Cinema,"50% off movie ticket","you get a 50% off movie ticket",startDate, endDate,100, 15.0, "coupon" );
//////
////            companyService.addCoupon(coupon); //WORKS
//
////            companyService.updateCoupon(coupon); //WORKS
//
////            companyService.deleteCoupon(17); //WORKS
//
////            System.out.println(companyService.getCompanyCoupons());//WORKS
//
////            System.out.println(companyService.getCompanyCoupons(Category.Cinema));//WORKS
//
////            System.out.println(companyService.getCompanyCoupons(10));//WORKS
//
////            System.out.println(companyService.getCompanyDetails());//WORKS
//
//            /*
//            Customer Service
//             */
////            Coupon coupon = adminService.getOneCoupon(19);
//
////            customerService.purchaseCoupon(coupon); //WORKS
//
////            System.out.println(customerService.getCustomerCoupons());//WORKS
////            System.out.println(customerService.getCustomerCoupons(Category.Food));//WORKS
////            System.out.println(customerService.getCustomerCoupons(10));//WORKS
////            System.out.println(customerService.getCustomerDetails());//WORKS
//
//
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//}
//
