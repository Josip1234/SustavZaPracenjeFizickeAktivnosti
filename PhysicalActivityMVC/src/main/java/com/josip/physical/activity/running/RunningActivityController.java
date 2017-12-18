package com.josip.physical.activity.running;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"/","/physical","/runningactivity"})
public class RunningActivityController {
	private RunningRepository runningRepository;
	@Autowired
	 public RunningActivityController(RunningRepository runningRepository) {
		this.runningRepository=runningRepository;
	}
	
@RequestMapping(value = {"/","/runningactivity"},method=RequestMethod.GET)	
public String runningactivity(Model model){
	model.addAttribute("run",runningRepository.display());
	return "runningactivity";
}
}
