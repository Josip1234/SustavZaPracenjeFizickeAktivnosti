package com.josip.physical.activity.indeks_tjelesne_mase;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses={BMICalculator.class})

public class BMIConfig {

}
