package com.shopdoors.configuration;

import com.shopdoors.service.user.AuthorizeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/private_profile", "/private_profile_home",
                                        "/private_profile_security", "/private_profile_info", "/cart/**",
                                        "/checkout/**", "/favorites/**", "/orders/**", "/order-confirmation/**")
                                .authenticated()
                                .anyRequest().permitAll())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler((request, response, accessDeniedException) ->
                                        response.sendRedirect("/login"))
                                .authenticationEntryPoint((request, response, authException) ->
                                        response.sendRedirect("/login")))
                .csrf(AbstractHttpConfigurer::disable)
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       AuthorizeUserDetailsService userDetailsService)
            throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return builder.build();
    }
}