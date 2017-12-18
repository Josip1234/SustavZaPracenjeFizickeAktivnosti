package com.josip.physical.activity.walking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.josip.physical.activity.biking.BikingRepository;

@Controller
@RequestMapping
public class WalkingController {
	private WalkingRepository walkingRepository;
	@Autowired
	 public WalkingController(WalkingRepository walkingRepository) {
		this.walkingRepository=walkingRepository;
	}
	/*
@RequestMapping(value = {"/","/walkingactivity"},method=RequestMethod.GET)	
public String walkingactivity(Model model){
	model.addAttribute("walk",walkingRepository.disp());
	return "walkingactivity";
}
*/
}
