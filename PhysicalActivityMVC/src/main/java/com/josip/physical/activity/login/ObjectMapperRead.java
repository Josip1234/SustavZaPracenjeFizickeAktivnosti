package com.josip.physical.activity.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperRead {
private final Logger logger = LoggerFactory.getLogger(this.getClass());

public String readJsonWithObjectMapper() throws IOException{
	String email="";
	ObjectMapper objectMapper=new ObjectMapper();
	Map<?, ?> logMap=objectMapper.readValue(new FileInputStream("json_files/prijava.json"),Map.class);
	for(Map.Entry<?, ?> entry : logMap.entrySet()){
		logger.info(entry.getKey()+"="+entry.getValue()+"\n");
		email=(String) entry.getValue();
		break;
	}
	
	return email;
}
}
