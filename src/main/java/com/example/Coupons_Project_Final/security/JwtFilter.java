package com.example.Coupons_Project_Final.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(2) // Run AFTER CorsFilter
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Missing or invalid Authorization header!");
                return;
            }
//            System.out.println("activeTokens:\n" + tokenManager.getActiveTokens());
            String token = authHeader.replace("Bearer ", "");
            if (!tokenManager.getActiveTokens().contains(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized, please log in!");
                return;
            }

            DecodedJWT decoded = JWT.decode(token);
            String role = decoded.getClaim("role").asString();

            if (isAuthorized(request.getServletPath(), role)) {
                // Authorization successful, proceed to next filter or controller
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Forbidden: Insufficient permissions!");
            }
        } catch (Exception e) {
            // Handle invalid token or decoding issues
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token!");
        }
    }

    private boolean isAuthorized(String path, String role) {
        if (path.startsWith("/api/admin") && "Administrator".equals(role)) {
            return true;
        } else if (path.startsWith("/api/company") && "Company".equals(role)) {
            return true;
        } else if (path.startsWith("/api/customer") && "Customer".equals(role)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Exclude login and public paths from the filter
        String path = request.getServletPath();
        return path.startsWith("/users") || path.startsWith("/public");
    }

}
