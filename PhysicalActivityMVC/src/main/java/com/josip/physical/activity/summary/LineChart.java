package com.josip.physical.activity.summary;

import static org.mockito.Mockito.RETURNS_DEFAULTS;

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
    public void generate() throws IOException {
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
    	DefaultCategoryDataset lnd=new DefaultCategoryDataset();
    
    	//crtaj graf
    	int broj=0;
    	for (SummaryActivity summaryActivity : dataset) {
    	
    		String datum1=summaryActivity.getDatum().trim();
    		String datum2=datum1.substring(8,11);
    		double sekunde=summaryActivity.getUkupnoVrijeme()/1000;
    		double minute=sekunde/60;
    		
    		line_chart_dataset.addValue( minute , "vrijeme" , datum2);
    		lnd.addValue(summaryActivity.getPrijedjeniKilometri(), "Broj prijedjenih kilometara", datum2);
    	
		}
       
        broj=0;
    	JFreeChart lineChartObject = ChartFactory.createLineChart(
    	"Ukupna aktivnost","Dan",
    	"Vrijeme u minutama",
    	line_chart_dataset,PlotOrientation.VERTICAL,
    	true,true,true);
    	int width = 1366; /* Width of the image */
    	int height = 768; /* Height of the image */
    	File lineChart = new File( "C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/LineChart.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width, height);
    	lineChart=new File("C:/xampp/tomcat/wtpwebapps/PhysicalActivityMVC/resources/LineChart.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width, height);
    	JFreeChart lnch=ChartFactory.createLineChart("Udaljenost u kilometrima","Dan","Ukupna prijeđena udaljensot",lnd,PlotOrientation.VERTICAL,true,true,true);
    	width = 1366; /* Width of the image */
    	height = 768; /* Height of the image */
    	lineChart = new File( "C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/LineChart2.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lnch, width, height);
    	lineChart=new File("C:/xampp/tomcat/wtpwebapps/PhysicalActivityMVC/resources/LineChart2.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width, height);
    
}
    public void generirajFiltriranp(String datum1,String datum2) throws IOException {
    	//stvori listu
    	List<SummaryActivity> dataset= new ArrayList<SummaryActivity>();
    	//upit za korisnika
    	Authentication au=SecurityContextHolder.getContext().getAuthentication();
		String name=au.getName();
		//dohvati listu  korisnika
    	dataset=sumImpl.filtriraj(name, datum1, datum2);
    	//izracunaj velicinu liste
    	int length=dataset.size();
    	//stvori kategorije dataseta
    	DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    	DefaultCategoryDataset lnd=new DefaultCategoryDataset();
    
    	//crtaj graf
    	int broj=0;
    	for (SummaryActivity summaryActivity : dataset) {
    
    		String datum12=summaryActivity.getDatum().trim();
    		
    		double sekunde=summaryActivity.getUkupnoVrijeme()/1000;
    		double minute=sekunde/60;
    		
    		line_chart_dataset.addValue( minute , "vrijeme" , datum12);
    		lnd.addValue(summaryActivity.getPrijedjeniKilometri(), "Broj prijedjenih kilometara", datum12);
    	
		}
       
        broj=0;
    	JFreeChart lineChartObject = ChartFactory.createLineChart(
    	"Ukupna aktivnost","Dan",
    	"Vrijeme u minutama",
    	line_chart_dataset,PlotOrientation.VERTICAL,
    	true,true,true);
    	int width = 1366; /* Width of the image */
    	int height = 768; /* Height of the image */
    	File lineChart = new File( "C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/LineChart.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width, height);
    	
    	JFreeChart lnch=ChartFactory.createLineChart("Udaljenost u kilometrima","Dan","Ukupna prijeđena udaljensot",lnd,PlotOrientation.VERTICAL,true,true,true);
    	width = 1366; /* Width of the image */
    	height = 768; /* Height of the image */
    	lineChart = new File( "C:/xampp/htdocs/SustavZaPracenjeFizickeAktivnosti/PhysicalActivityMVC/src/main/webapp/resources/LineChart2.jpeg" );
    	ChartUtilities.saveChartAsJPEG(lineChart ,lnch, width, height);
    		
    
}    
}
