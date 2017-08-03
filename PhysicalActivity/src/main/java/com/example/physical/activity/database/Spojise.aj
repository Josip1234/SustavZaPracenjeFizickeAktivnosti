package com.example.physical.activity.database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public aspect Spojise {

@Pointcut("execution(** com.example.physical.activity.database.PhysicalActivityDatabase.spoji(..))")
public void PhysicalActivityDatabase(){}
@Before("PhysicalActivityDatabase()")
	public void poruka(){
	
		System.out.println("Potrebno je spojiti se na bazu");
}
@AfterReturning("PhysicalActivityDatabase()")
public void por(){
	System.out.println("Spojeno na bazu");
}
@AfterThrowing("PhysicalActivityDatabase()")
public void p(){
	System.out.println("Nešto je pošlo po zlu.");
}
	
		
	
		
	


}
