package com.example.physical.activity.regist;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhysicalActivityApplication {

	public static void main(String[] args) {
		
		Registration rg = new Registration();
		rg.register();
	}
}