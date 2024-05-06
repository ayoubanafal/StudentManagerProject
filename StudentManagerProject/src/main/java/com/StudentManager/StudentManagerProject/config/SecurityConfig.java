package com.StudentManager.StudentManagerProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/students", "/deletedStudents","/studentsAbsence","teachers").authenticated()
                        .requestMatchers("/login", "/", "/webjars/**","/**").permitAll())
                //.formLogin((form -> form.loginPage("/loginpage").permitAll()))
                .formLogin(Customizer.withDefaults())
                .formLogin(formlogin->formlogin
                                .permitAll()
                        .defaultSuccessUrl("/students"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails ayoubs = User.withUsername("ayoubs")
                .password("1")
                .roles("super")
                .build();
        UserDetails user = User.withUsername("user")
                .password("1")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(ayoubs, user);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
