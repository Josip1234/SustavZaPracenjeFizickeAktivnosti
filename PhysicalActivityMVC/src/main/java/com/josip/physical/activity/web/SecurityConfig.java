package com.josip.physical.activity.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
import com.josip.physical.activity.login.Login;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
Login login=new Login();
PhysicalActivityDatabase db = new PhysicalActivityDatabase();

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.inMemoryAuthentication().withUser(login.getUsername()).password(login.getSifra()).roles("USER","ADMIN").and()
	.withUser("jbosnjak3@gmail.com").password("root").roles("USER");
}
}
