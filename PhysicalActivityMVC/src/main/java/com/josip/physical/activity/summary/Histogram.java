package com.josip.physical.activity.summary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("Histogram")
public class Histogram {
	@Autowired
	SummaryImplementation sumImpl;
    @Autowired
    SummaryActivity sumAct;
	public Histogram() {
	
	}
	//kategorizacija za legendu
	final String ukupanBrojKoraka="Broj koraka";
	final String prijedjeniKilometri="Kilometraža";
	final String prosjecnaBrzina="Prosječna brzina";
	
	//donji dio histograma
	final String dan="Dan";
	
	public File generirajHistogram() throws IOException {
		List<SummaryActivity> act=new ArrayList<SummaryActivity>();
		Authentication au=SecurityContextHolder.getContext().getAuthentication();
		String name=au.getName();
		act=sumImpl.show(name);
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SummaryActivity summaryActivity : act) {
			String datum1=summaryActivity.getDatum().trim();
    		String datum2=datum1.substring(8,11);
    		double sekunde=summaryActivity.getUkupnoVrijeme()/1000;
    		double minute=sekunde/60;
			dataset.addValue(minute,ukupanBrojKoraka,datum2);
			dataset.addValue(minute, prijedjeniKilometri, datum2);
			dataset.addValue(minute, prosjecnaBrzina, datum2);
			
		}
		JFreeChart barChart= ChartFactory.createBarChart("Ukupna statistika korisnika tijekom vremena trajanja aktivnosti",
				"Kategorija", "Minute trajanja", dataset,PlotOrientation.VERTICAL,true,true,true);
		
		int width=1366;
		int height=768;
		File histogram=new File("C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/Histogram.jpeg");
		ChartUtilities.saveChartAsJPEG(histogram, barChart, width, height);
		return histogram;
	}
	
	public File generirajHistogram(String datum1,String datum2) throws IOException {
		List<SummaryActivity> act=new ArrayList<SummaryActivity>();
		Authentication au=SecurityContextHolder.getContext().getAuthentication();
		String name=au.getName();
		act=sumImpl.filtriraj(name, datum1, datum2);
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SummaryActivity summaryActivity : act) {
			String datum12=summaryActivity.getDatum().trim();
    		String datum23=datum1.substring(8,11);
    		double sekunde=summaryActivity.getUkupnoVrijeme()/1000;
    		double minute=sekunde/60;
			dataset.addValue(minute,ukupanBrojKoraka,datum23);
			dataset.addValue(minute, prijedjeniKilometri, datum23);
			dataset.addValue(minute, prosjecnaBrzina, datum23);
			
		}
		JFreeChart barChart= ChartFactory.createBarChart("Ukupna statistika korisnika tijekom vremena trajanja aktivnosti",
				"Kategorija", "Minute trajanja", dataset,PlotOrientation.VERTICAL,true,true,true);
		
		int width=1366;
		int height=768;
		File histogram=new File("C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/Histogram.jpeg");
		ChartUtilities.saveChartAsJPEG(histogram, barChart, width, height);
		return histogram;
	}


}
