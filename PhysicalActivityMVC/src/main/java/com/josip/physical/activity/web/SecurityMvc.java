package com.josip.physical.activity.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityMvc extends WebSecurityConfigurerAdapter{
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://127.0.0.1/physicalactivity");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
		/*
	auth
	.inMemoryAuthentication()
	.withUser("user").password("user").roles("USER").and()
	.withUser("admin").password("admin").roles("USER", "ADMIN");
	}
	*/
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select email,sifra,true "+ "from registration where email=?").authoritiesByUsernameQuery("select email,'ROLE_USER' from registration where email=?");
}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().and()
		.authorizeRequests()
		 .antMatchers("/bikingactivity").hasRole("USER")
		 .antMatchers("/mojprofil").hasRole("USER")
		 .anyRequest().permitAll();
		 
		
	}
}

