package com.josip.physical.activity.indeks_tjelesne_mase;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public aspect BMIAspekt {
	@Pointcut("execution(** com.josip.physical.activity.index_tjelesne_mase.BMICalculator.izracunajBMI(..))")
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
