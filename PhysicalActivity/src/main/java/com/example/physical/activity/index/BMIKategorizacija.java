package com.example.physical.activity.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BMIKategorizacija implements ITM {
private BMICalculator b;

@Autowired
public BMIKategorizacija(BMICalculator b){
	this.b=b;
};

public float izracunajBMI(){
	return b.izracunajBMI();
}






}
