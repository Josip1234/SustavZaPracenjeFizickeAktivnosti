package com.josip.physical.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.josip.physical.activity.login.ObjectMapperRead;

@Controller
@RequestMapping("/")
public class MobilnaVerifikacijaController {
	@RequestMapping(value="mobilnaverifikacijavalidacija",method=GET)
	public String mobilnaverifikacijavalidacija(){
		
		
		return "mobilnaverifikacijavalidacija";
	}
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public @ResponseBody String getMethod(@RequestHeader(value="json")String headerStr) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException{
		System.out.println("POST");
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		ObjectMapper objectMapper=new ObjectMapper();
		Map<?, ?> logMap=objectMapper.readValue(new FileInputStream(headerStr),Map.class);
		for(Map.Entry<?, ?> entry : logMap.entrySet()){
			logger.info(entry.getKey()+"="+entry.getValue()+"\n");
			
		}
			
		return "mobilnaverifikacijavalidacija";
		
	}
	
}
