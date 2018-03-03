package com.josip.physical.activity.biking;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/","/physical","/bikingactivity"})
public class BikingActivityController {
	private BikingRepository bikingRepository;
	@Autowired
	 public BikingActivityController(BikingRepository bikingRepository) {
		this.bikingRepository=bikingRepository;
	}
@RequestMapping(value = {"/","/bikingactivity"},method=RequestMethod.GET, produces="application/json")	
public String bikingactivity(Model model){
	model.addAttribute("bike",bikingRepository.izlistajSve());
	return "bikingactivity";
}

}
