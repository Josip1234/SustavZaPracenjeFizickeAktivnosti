package com.example.physical.activity.index;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public aspect BMIAspekt {
@Pointcut("execution(** com.example.physical.activity.index.BMICalculator.izracunajBMI(..))")
public void BMICalculator(){};
@Before("BMICalculator()")
public void poruka(){
	System.out.println("Unos indeksa tjelesne mase!");
}
@AfterReturning("BMICalculator()")
public void poruk(){
	System.out.println("Rezultati su tu.");
}

}