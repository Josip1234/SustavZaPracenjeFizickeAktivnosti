package com.example.physical.activity.index;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.physical.activity.RegTest;
import com.example.physical.activity.login.test;

@Configuration
@ComponentScan(basePackageClasses={RegTest.class,test.class,BMITest.class})
public class BMIConfig {

}
