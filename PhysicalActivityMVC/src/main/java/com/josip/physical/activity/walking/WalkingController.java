package com.josip.physical.activity.walking;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value= {"/","/physical","/walking"})
public class WalkingController {
	
private WalkingRepository walkingRepository;
public WalkingController(WalkingRepository walkingRepository) {
	this.walkingRepository=walkingRepository;
}
@RequestMapping(value= {"/walking"},method=RequestMethod.GET)
public String walking(Model model) {
	model.addAttribute("hod",walkingRepository.res());
	return "walking";
}



}
