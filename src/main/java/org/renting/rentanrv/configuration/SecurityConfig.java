package org.renting.rentanrv.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth
//			.inMemoryAuthentication()
//			 .withUser("user")
//			 .password(encoder.encode("pass"))
//			 .roles("USER")
//		 .and()
//		 	.withUser("admin")
//		 	.password(encoder.encode("pass"))
//		 	.roles("ADMIN");
//		 
//	}
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//	        .antMatchers("/css/**", "/images/**", "/js/**", "/error/**", "/", "/vehicles", "/vehicles/{\\d+}").permitAll()
//	        .antMatchers("/registration", "/reg").permitAll() //?? change
//	        .antMatchers("/admin/**").hasRole("ADMIN")
//	        .anyRequest().authenticated()
//	        .and()
//	        .headers().frameOptions().disable()
//            .and()
//            .csrf().ignoringAntMatchers("/h2-console/**")//!! change
// 			.and().formLogin().loginPage("/login").permitAll()	        
//	        .and().logout().logoutSuccessUrl("/").permitAll();
//    }
}