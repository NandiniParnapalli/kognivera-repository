package com.kog.cm.securityconfig;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class FacultyConfig {

	@Value("${spring.security.user.name}")
	private String userName;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	@Value("${spring.security.user.roles}")
	private String roles;

	
	
      @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http .csrf(csrf->csrf.disable())
	    		.authorizeHttpRequests(requests -> requests
	            .requestMatchers("/authfac/home").permitAll()
	            .requestMatchers("/authfac/user/**").hasAnyRole("ADMIN","USER")
	            .requestMatchers("/authfac/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated())
	            .httpBasic(withDefaults())
	           
	            .logout(logout ->logout.logoutUrl("/authfac/user/logout").logoutSuccessUrl("/").permitAll());
       
	    return http.build();
	            	
	            		
	            
	            //.logout(logout->logout.logoutUrl("/authfac/user/logout").logoutSuccessUrl("/")).build();
	            
	           
	}

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails user=User.builder()
				.username(userName)
				.password(passwordEncoder().encode(password))
				.roles(roles)
				.build();
		return new InMemoryUserDetailsManager(user);
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
