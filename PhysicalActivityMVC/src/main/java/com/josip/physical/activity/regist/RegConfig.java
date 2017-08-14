package com.josip.physical.activity.regist;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.josip.physical.activity.baza.BazaConfig;
import com.josip.physical.activity.baza.PhysicalActivityDatabase;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses={Registration.class,PhysicalActivityDatabase.class})
@Import(BazaConfig.class)

public class RegConfig {

}
