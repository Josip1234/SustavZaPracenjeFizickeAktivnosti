package com.josip.physical.activity.walking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
