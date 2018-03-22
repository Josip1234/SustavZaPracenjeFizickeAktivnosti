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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value= {"/","/physical","/sum"})
public class SummaryController {
private SummaryRepository summaryRepository;

@Autowired
LineChart lineChart;
@Autowired
Histogram histogram;
@Autowired
public SummaryController(SummaryRepository summaryRepository) {
	this.summaryRepository=summaryRepository;
	
}
@RequestMapping(value= {"/sum"},method=RequestMethod.GET)
public String sum(Model model) throws IOException {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	   String name=au.getName();
	model.addAttribute("sum",summaryRepository.show(name));
	model.addAttribute("ukupnoBicikliranja",summaryRepository.izlistaj(name));
	model.addAttribute("ukupnoTrcanje",summaryRepository.lista(name));
	model.addAttribute("ukupnoHodanja",summaryRepository.izlistajHod(name));
	lineChart.generate();
	histogram.generirajHistogram();
   
	
	return "sum";
}
@RequestMapping(value= {"/sum"},method=RequestMethod.POST)
public String filter(@RequestParam("datum1")String Datum1,@RequestParam("datum2")String Datum2,Model model) throws IOException {
	Authentication au=SecurityContextHolder.getContext().getAuthentication();
	   String name=au.getName();
	model.addAttribute("sum",summaryRepository.filtriraj(name, Datum1, Datum2));
	lineChart.generirajFiltriranp(Datum1, Datum2);
    
    histogram.generirajHistogram(Datum1,Datum2);
	
	return "sum";
}

}
