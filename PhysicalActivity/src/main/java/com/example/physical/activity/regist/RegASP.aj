package com.example.physical.activity.regist;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public aspect RegASP {
@Pointcut("execution(** com.example.physical.activity.regist.registeryourself.register(..))")
public void registeryourself(){};
@Before("registeryourself()")
public void postupak(){
	System.out.println("U postupku registracije");
}
}