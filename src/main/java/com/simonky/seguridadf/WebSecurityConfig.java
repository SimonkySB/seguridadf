package com.simonky.seguridadf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> 
                headers.contentSecurityPolicy(csp -> csp.policyDirectives(
                    "default-src 'self'; "+ 
                    "style-src 'self' https://cdn.jsdelivr.net; " +
                    "script-src 'self' https://cdn.jsdelivr.net; " + 
                    "img-src 'self' data: https://cdn.jsdelivr.net https://placehold.co; " + 
                    "frame-ancestors 'none'; " +
                    "connect-src 'self'; " +
                    "font-src 'self' https://fonts.gstatic.com; " +
                    "object-src 'none';"
                ))
            )
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home").permitAll()
                .requestMatchers("/", "/search").permitAll()
                .requestMatchers("/**.css").permitAll()
                .requestMatchers("/images/**.svg").permitAll()
                .requestMatchers("/._darcs/**", "/.bzr/**", "/.hg/**", "/BitKeeper/**").denyAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout((logout) -> 
                logout
                    .logoutSuccessUrl("/home")
                    .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService UserDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("password"))
            .roles("USER","ADMIN")
            .build();

        UserDetails manager = User.builder()
            .username("manager")
            .password(passwordEncoder().encode("password"))
            .roles("USER","MANAGER")
            .build();

        return new InMemoryUserDetailsManager(user, admin, manager);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
