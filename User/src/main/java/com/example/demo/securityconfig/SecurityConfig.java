package com.example.demo.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder)
{
	UserDetails admin=User.withUsername("nandu")
			.password(encoder.encode("123"))
			.roles("ADMIN","USER")
			.build();
	UserDetails user=User.withUsername("siri")
			.password(encoder.encode("123"))
			.roles("USER")
			.build();
	return new InMemoryUserDetailsManager(admin,user);
}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
{
	return http.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(request->request.requestMatchers("/auth/welcome").permitAll())
			.authorizeHttpRequests(request->request.requestMatchers("/auth/user/**").authenticated())
			.authorizeHttpRequests(request->request.requestMatchers("/auth/admin/**").authenticated()).formLogin(withDefaults()).build();
}
@Bean
public PasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder();
}
@Bean
public SecurityContextLogoutHandler securityContextLogoutHandler()
{
	return new SecurityContextLogoutHandler();
}
}
