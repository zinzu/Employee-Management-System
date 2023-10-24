package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        jdbcUserDetailsManager.setUsernameBasedPrimaryKey(true);

        return jdbcUserDetailsManager;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure->
                configure
                        .requestMatchers("/employees/showFormForAdd").hasRole("MANAGER")
                        .requestMatchers("/employees/showFormForUpdate/**").hasRole("MANAGER")
                        .requestMatchers("/employees/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

        )
                .formLogin(form->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/employees/list",true)
                                .permitAll()
                )

                .logout(logout->
                        logout.permitAll()
                )

                .exceptionHandling(configurer->
                        configurer
                                .accessDeniedPage("/accessDenied")
                );

        return http.build();
    }


}
