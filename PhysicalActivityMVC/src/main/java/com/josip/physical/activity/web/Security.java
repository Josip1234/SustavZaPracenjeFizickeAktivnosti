package com.josip.physical.activity.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
@Configuration
@EnableWebMvcSecurity
public class Security extends AbstractSecurityWebApplicationInitializer {

}
