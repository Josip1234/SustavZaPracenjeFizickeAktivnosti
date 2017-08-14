package com.josip.physical.activity.login;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.josip.physical.activity.regist.Registration;

@Configuration
@ComponentScan(basePackageClasses={Login.class,Registration.class})

public class LogConfig {

}
