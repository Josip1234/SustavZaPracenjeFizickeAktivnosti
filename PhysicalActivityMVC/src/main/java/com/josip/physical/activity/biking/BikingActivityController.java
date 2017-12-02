package com.josip.physical.activity.biking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/bikingactivity")
public class BikingActivityController {
	private BikingRepository bikingRepository;
	@Autowired
	 public BikingActivityController(BikingRepository bikingRepository) {
		this.bikingRepository=bikingRepository;
	}
@RequestMapping(value="/bikingactivity",method=RequestMethod.GET)	
public String bikingactivity(Model model){
	model.addAttribute("bike",bikingRepository.listStuff());
	return "bikingactivity";
}
}
