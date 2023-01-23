package com.banzaiflix_backend.banzaiflix_backend.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.banzaiflix_backend.banzaiflix_backend.Interfaces.JWTInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            SecretKey key = Keys.hmacShaKeyFor(JWTInterface.JWT_KEY.getBytes());
            String jwt  = Jwts.builder().setIssuer("banzaiflix").setSubject("JWT TOKEN")
            .claim("username", authentication.getName())
            .claim("authorities", populateAuthorities(authentication.getAuthorities()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date().getTime() + 30000000)))
            .signWith(key).compact();
            response.setHeader(JWTInterface.JWT_HEADER, jwt);

        }
        filterChain.doFilter(request, response);
        
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
            
        }
        return String.join(",", authoritiesSet);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // TODO Auto-generated method stub
        return !request.getServletPath().equals("/");
    }
    
}
