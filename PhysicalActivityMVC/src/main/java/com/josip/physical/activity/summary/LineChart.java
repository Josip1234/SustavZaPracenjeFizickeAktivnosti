package com.josip.physical.activity.summary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component("Linijski grafikon")
public class LineChart {
    @Autowired
    SummaryImplementation sumImpl;
    
	public LineChart() {
		
		
	}
    public File generate(int indeks) throws IOException {
    	//stvori listu
    	List<SummaryActivity> dataset= new ArrayList<SummaryActivity>();
    	//upit za korisnika
    	Authentication au=SecurityContextHolder.getContext().getAuthentication();
		String name=au.getName();
		//dohvati listu  korisnika
    	dataset=sumImpl.show(name);
    	//izracunaj velicinu liste
    	int length=dataset.size();
    	//stvori kategorije dataseta
    	DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    	indeks+=indeks;
    	//crtaj graf
    	int broj=0;
    	for (SummaryActivity summaryActivity : dataset) {
    	
    		String datum1=summaryActivity.getDatum().trim();
    		String datum2=datum1.substring(8,11);
    		line_chart_dataset.addValue( summaryActivity.getUkupnoVrijeme() , "vrijeme" , datum2);
    	
		}
        indeks+=broj;
        broj=0;
    	JFreeChart lineChartObject = ChartFactory.createLineChart(
    	"Mjesecna aktivnost","Dan",
    	"Vrijeme u milisekundama",
    	line_chart_dataset,PlotOrientation.VERTICAL,
    	true,true,false);
    	int width = 1366; /* Width of the image */
    	int height = 768; /* Height of the image */
    	File lineChart = new File( "C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/LineChart.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width, height);
    	
    		return lineChart;
    
}
}
