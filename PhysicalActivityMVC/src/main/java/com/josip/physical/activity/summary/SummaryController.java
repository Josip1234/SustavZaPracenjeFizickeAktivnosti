package com.josip.physical.activity.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.josip.physical.activity.biking.BikingRepository;

@Controller
@RequestMapping
public class SummaryController {
	private SummaryRepository summaryRepository;
	@Autowired
	 public SummaryController(SummaryRepository summaryRepository) {
		this.summaryRepository=summaryRepository;
	}
/*@RequestMapping(value = {"/","/summaryactivity"},method=RequestMethod.GET)	
public String summaryactivity(Model model){
	model.addAttribute("sum",summaryRepository.show());
	return "summaryactivity";
}*/
}
