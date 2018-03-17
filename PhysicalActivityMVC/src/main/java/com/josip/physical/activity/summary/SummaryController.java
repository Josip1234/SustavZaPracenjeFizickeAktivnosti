package com.josip.physical.activity.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/","/physical","/sum"})
public class SummaryController {
private SummaryRepository summaryRepository;
@Autowired
public SummaryController(SummaryRepository summaryRepository) {
	this.summaryRepository=summaryRepository;
}
@RequestMapping(value= {"/sum"},method=RequestMethod.GET)
public String sum(Model model) {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	   String name=au.getName();
	model.addAttribute("sum",summaryRepository.show(name));
	return "sum";
}
}
