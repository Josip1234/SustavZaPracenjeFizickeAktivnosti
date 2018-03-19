package com.josip.physical.activity.summary;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
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
	model.addAttribute("ukupnoBicikliranja",summaryRepository.izlistaj(name));
	model.addAttribute("ukupnoTrcanje",summaryRepository.lista(name));
	model.addAttribute("ukupnoHodanja",summaryRepository.izlistajHod(name));
	return "sum";
}
@RequestMapping(value= {"/sum"},method=RequestMethod.POST)
public String kreirajGraf(Model model) throws IOException {
    
    
			LineChart lineChart = new LineChart();
			
			
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
			   String name=au.getName();
	model.addAttribute("sum",summaryRepository.show(name));
	model.addAttribute("ukupnoBicikliranja",summaryRepository.izlistaj(name));
	model.addAttribute("ukupnoTrcanje",summaryRepository.lista(name));
	model.addAttribute("ukupnoHodanja",summaryRepository.izlistajHod(name));
	model.addAttribute(lineChart.generate());
	return "sum";
}
}
