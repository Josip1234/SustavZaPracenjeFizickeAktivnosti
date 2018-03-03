package com.josip.physical.activity.running;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"/","/physical","/runningactivity"})
public class RunningController {
	private RunningRepository runningRepository;
	@Autowired
	public RunningController(RunningRepository runningRepository) {
		this.runningRepository=runningRepository;
	}
	
	 
	   
	@RequestMapping(value = {"/runningactivity"},method=RequestMethod.GET)
	public String runningactivity(Model model) {
        model.addAttribute("run", runningRepository.results());
      //dohva�anje usera iz login forme
        Authentication au=SecurityContextHolder.getContext().getAuthentication();
 	   String name=au.getName();
        System.out.println(name);
		return "runningactivity";
	}
}
