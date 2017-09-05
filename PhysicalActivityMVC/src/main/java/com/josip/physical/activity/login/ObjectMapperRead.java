package com.josip.physical.activity.login;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperRead {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
public Login readJsonWithObjectMapper() throws IOException{
	ObjectMapper objectMapper=new ObjectMapper();
	Login log=objectMapper.readValue(new File("prijava.json"), Login.class);
	logger.info(log.toString());
	return log;
}
}
