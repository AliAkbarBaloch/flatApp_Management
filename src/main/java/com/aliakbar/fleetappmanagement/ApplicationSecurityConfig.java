package com.aliakbar.fleetappmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> {
			try {
				authz
				.requestMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
				.requestMatchers("/register", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
				.requestMatchers("/users/addNew").permitAll()
				.anyRequest().authenticated()
				.and()
						.exceptionHandling().accessDeniedPage("/accessDenied")
						.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.and()  
				.logout().invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return http.build();
	}
	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
////        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
//    }
	@Bean(name="myPasswordEncoder")
	public PasswordEncoder getPasswordEncoder() {
	        DelegatingPasswordEncoder delPasswordEncoder=  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        BCryptPasswordEncoder bcryptPasswordEncoder =new BCryptPasswordEncoder();
	    delPasswordEncoder.setDefaultPasswordEncoderForMatches(bcryptPasswordEncoder);
	    return delPasswordEncoder;      
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    @Autowired  
    public DaoAuthenticationProvider getDaoAuthenticationProvider(@Qualifier("myPasswordEncoder") PasswordEncoder passwordEncoder, UserDetailsService userDetailsServiceJDBC) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceJDBC);
        return daoAuthenticationProvider;
    }
	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();		
//		provider.setUserDetailsService(userDetailsService);	
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
}
