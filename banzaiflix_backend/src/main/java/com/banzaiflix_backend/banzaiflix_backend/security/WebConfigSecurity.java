package com.banzaiflix_backend.banzaiflix_backend.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.banzaiflix_backend.banzaiflix_backend.filters.JWTGeneratorFilter;
import com.banzaiflix_backend.banzaiflix_backend.filters.JWTValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class WebConfigSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      return  http

      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .csrf().disable()
            .cors().configurationSource(new CorsConfigurationSource() {

                @Override
                @Nullable
                public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowedHeaders(Arrays.asList("Authorization"));
                    config.setAllowedOrigins(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;
                }
                

            }).and()
            .addFilterAfter(new JWTGeneratorFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new JWTValidatorFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/cadastrarUsuario").hasAnyRole("ROLE_USER")
            .requestMatchers(HttpMethod.GET,"/admin").hasAnyRole("ROLE_ADMIN")
           
           

            .anyRequest().authenticated()
            
            
           .and()
           .httpBasic()
           
            
            .and()
            .build();    
    }
    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return (web) -> web.ignoring().requestMatchers(HttpMethod.GET,"/");
    // }
   
}
