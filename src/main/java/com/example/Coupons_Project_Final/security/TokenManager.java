package com.example.Coupons_Project_Final.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Coupons_Project_Final.services.AdminService;
import com.example.Coupons_Project_Final.services.ClientService;
import com.example.Coupons_Project_Final.services.CompanyService;
import com.example.Coupons_Project_Final.services.CustomerService;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TokenManager {
    private List<String> activeTokens = new ArrayList<>();

    public List<String> getActiveTokens() {
        return activeTokens;
    }

    public void addActiveToken(String token){
        activeTokens.add(token);
    }

    public void removeActiveToken(String token){
        String tokenToDelete = token.replace("Bearer ", "");
        activeTokens.remove(tokenToDelete);
    }
    public void setActiveTokens(List<String> activeTokens) {
        this.activeTokens = activeTokens;
    }
    public String createToken(ClientService clientService) throws SQLException {
        Date expires = new Date();
        expires.setTime(expires.getTime()+1000*60*30);
        if (clientService instanceof AdminService){
            return JWT.create()
                    .withIssuer("")
                    .withIssuedAt(new Date())
                    .withClaim("id", "-1")
                    .withClaim("name", "Admin")
                    .withClaim("role", "Administrator")
                    .withExpiresAt(expires)
                    .sign(Algorithm.none());
        }
        if (clientService instanceof CompanyService){
            return JWT.create()
                    .withIssuer("")
                    .withIssuedAt(new Date())
                    .withClaim("id", ((CompanyService) clientService).getCompanyDetails().getId())
                    .withClaim("name", ((CompanyService) clientService).getCompanyDetails().getName())
                    .withClaim("role", "Company")
                    .withExpiresAt(expires)
                    .sign(Algorithm.none());
        }
        if (clientService instanceof CustomerService){
            return JWT.create()
                    .withIssuer("")
                    .withIssuedAt(new Date())
                    .withClaim("id", ((CustomerService) clientService).getCustomerDetails().getId())
                    .withClaim("name", ((CustomerService) clientService).getCustomerDetails().getFirstName())
                    .withClaim("role", "Customer")
                    .withExpiresAt(expires)
                    .sign(Algorithm.none());
        }
//        TODO ask nir if I should throw exception
        return null;
    }

}
