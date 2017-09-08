package com.josip.physical.activity.login;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@Configuration
@ComponentScan(basePackageClasses={Login.class})
@EnableAspectJAutoProxy
public class LogConfig {

}
