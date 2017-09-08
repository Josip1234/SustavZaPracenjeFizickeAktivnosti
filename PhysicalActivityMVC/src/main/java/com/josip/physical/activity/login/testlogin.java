package com.josip.physical.activity.login;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class testlogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        ObjectMapperRead read = new ObjectMapperRead();
        try {
			read.readJsonWithObjectMapper();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		Login login = new Login();
		login.setAutoriziran(true);
		JSONWriterObjectMapper jsonWriter= new JSONWriterObjectMapper();
		jsonWriter.writeOdgovorToJson(login);
		*/
	}

}
