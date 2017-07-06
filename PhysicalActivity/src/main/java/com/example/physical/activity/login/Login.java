package com.example.physical.activity.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.physical.activity.regist.Registration;


@Component("Login user")
public class Login implements log {
    private Registration rg;
    
    @Autowired
    public Login(Registration rg){
    	this.rg=rg;
    }
    public void login(){
    	rg.register();
    }

}
