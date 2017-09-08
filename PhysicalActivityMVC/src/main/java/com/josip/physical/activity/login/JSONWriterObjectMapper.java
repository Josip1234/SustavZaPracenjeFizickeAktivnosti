package com.josip.physical.activity.login;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONWriterObjectMapper {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
ObjectMapper objectMapper= new ObjectMapper();

public void writeOdgovorToJson(Login log){
	try {
		String jsonInString= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(log);
		logger.info("Odgovor u jsonu je\n"+jsonInString);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			objectMapper.writeValue(new File("logintrue.json"), log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
