package com.example.physical.activity.login;

import com.example.physical.activity.regist.Registration;

public class test {

	public static void main(String[] args) {
		Registration r = new Registration();
        Login a = new Login(r);
        a.login();
	}

}
