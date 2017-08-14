package com.josip.physical.activity.baza;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.josip.physical.activity.regist.Registration;

@Configuration
@ComponentScan(basePackageClasses={PhysicalActivityDatabase.class})
@EnableAspectJAutoProxy
@Import(Registration.class)
public class BazaConfig {

}
