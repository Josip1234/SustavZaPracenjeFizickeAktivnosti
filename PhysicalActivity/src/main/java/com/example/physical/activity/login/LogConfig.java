package com.example.physical.activity.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.physical.activity.regist.Registration;

@Configuration
@ComponentScan(basePackageClasses={Login.class,Registration.class})
public class LogConfig {

}
