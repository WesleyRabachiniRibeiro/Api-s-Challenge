package com.savelife.project.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/webjars/**",
            "/h2/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeHttpRequests()

                .antMatchers(HttpMethod.POST, "/v1/user/").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/user/").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/user/**").hasAnyRole("HOSPITAL", "ADMIN", "USER", "AMBULANCE")
                .antMatchers(HttpMethod.PUT, "/v1/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/user/**").hasAnyRole("USER", "ADMIN")

                .antMatchers(HttpMethod.POST, "/v1/ambulance/").hasAnyRole("AMBULANCE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/ambulance/").hasAnyRole("HOSPITAL", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/ambulance/**").hasAnyRole("HOSPITAL", "ADMIN", "AMBULANCE")
                .antMatchers(HttpMethod.PUT, "/v1/ambulance/**").hasAnyRole("ADMIN", "AMBULANCE")
                .antMatchers(HttpMethod.DELETE, "/v1/ambulance/**").hasAnyRole("ADMIN", "AMBULANCE")

                .antMatchers(HttpMethod.POST, "/v1/hospital/").hasAnyRole("HOSPITAL", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/hospital/").hasAnyRole("HOSPITAL", "ADMIN", "USER", "AMBULANCE")
                .antMatchers(HttpMethod.GET, "/v1/hospital/**").hasAnyRole("HOSPITAL", "ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/v1/hospital/**").hasAnyRole("HOSPITAL", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/hospital/**").hasAnyRole("HOSPITAL", "ADMIN")

                .antMatchers(HttpMethod.POST, "/v1/request/").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/v1/request/").hasAnyRole("USER", "ADMIN", "HOSPITAL", "AMBULANCE")
                .antMatchers(HttpMethod.GET, "/v1/request/**").hasAnyRole("HOSPITAL", "ADMIN", "USER", "AMBULANCE")
                .antMatchers(HttpMethod.PUT, "/v1/request/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/request/**").hasAnyRole("USER", "ADMIN")

                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
