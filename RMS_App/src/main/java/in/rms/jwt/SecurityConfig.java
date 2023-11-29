package in.rms.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService customerUserDetailsService;

	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserDetailsService)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	

	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .build();
	}
	

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception { 
		return http.csrf().disable()
	.authorizeHttpRequests()
	.antMatchers ("/user/signup","/user/login","/user/forgotPassword"). permitAll()
	
	.anyRequest()
	.authenticated()
	.and()
	.sessionManagement()
	.sessionCreationPolicy (SessionCreationPolicy. STATELESS)
	.and()
	
	.addFilterBefore (jwtFilter, UsernamePasswordAuthenticationFilter.class)
	.build();
	}

}
