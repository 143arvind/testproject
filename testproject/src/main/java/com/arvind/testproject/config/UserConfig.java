package com.arvind.testproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserConfig {
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	    @Bean
	    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(User.withUsername("admin")
	                .password(passwordEncoder.encode("admin"))
	                .roles("ADMIN").build());
	        manager.createUser(User.withUsername("user")
	                .password(passwordEncoder.encode("user"))
	                .roles("USER").build());
	        return manager;
	    }
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                    .requestMatchers("/api/users","/favicon.ico").hasRole("ADMIN")
	                    .requestMatchers("/api/users/**").hasAnyRole("ADMIN", "USER")
	                    .anyRequest().authenticated()
	            )
	                 .httpBasic(Customizer.withDefaults())
	                .exceptionHandling(exceptionHandling ->
	                    exceptionHandling
	                        .accessDeniedPage("/403")
	                );
	            return http.build();
	    }
}
