package com.example.physical.activity.database;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.example.physical.activity.regist.RegConfig;
import com.example.physical.activity.regist.Registration;

@Configuration
@ComponentScan(basePackageClasses={PhysicalActivityDatabase.class})
@EnableAspectJAutoProxy
@Import(Registration.class)
public class BazaConfig {
  
}