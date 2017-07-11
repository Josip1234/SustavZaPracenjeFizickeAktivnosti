package com.example.physical.activity.regist;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.physical.activity.RegTest;
import com.example.physical.activity.database.BazaConfig;
import com.example.physical.activity.database.PhysicalActivityDatabase;
import com.example.physical.activity.login.test;

@Configuration
@ComponentScan(basePackageClasses={Registration.class,PhysicalActivityDatabase.class})
@Import(BazaConfig.class)
public class RegConfig {

}
