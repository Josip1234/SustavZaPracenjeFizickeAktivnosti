package com.josip.physical.acitivty.REST;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.biking.BikingActivity;
import com.josip.physical.activity.biking.BikingImplementation;
import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.regist.RegistrationImpl;
import com.josip.physical.activity.running.RunningActivity;
import com.josip.physical.activity.running.RunningImplementation;
import com.josip.physical.activity.summary.SummaryActivity;
import com.josip.physical.activity.summary.SummaryBiking;
import com.josip.physical.activity.summary.SummaryImplementation;
import com.josip.physical.activity.summary.SummaryRunning;
import com.josip.physical.activity.summary.WalkingStatistika;
import com.josip.physical.activity.walking.WalkingActivity;
import com.josip.physical.activity.walking.WalkingImplementation;
import com.josip.physical.activity.walking.WalkingRepository;



@RestController
@RequestMapping({"/","/physical/","/sumary","/bike","/run","/walk"})
public class JsonGeneratorController {
     
        @Autowired
        SummaryImplementation sumimp;
		@Autowired
		Registration rg;
		@Autowired
		RegistrationImpl impl;
		@Autowired
		SpringDataSource ds;
		
		@Bean
		public MessageSource messageSource(){
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages");
			return messageSource;
		}

	    
		@RequestMapping(value="/1e2b3tzrUZcvn")
		
		public List<Registration> korisnik(){
		    List<Registration> rg=new ArrayList<Registration>();
			ds=new SpringDataSource();
			Registration temp=new Registration();
			
			if(null != ds.getObj()) {
				String select="SELECT OIB,ime,prezime,spol,datumr,email,sifra FROM `registration`";
				List<Registration> reg=ds.getObj().query(select,new RowMapper() {
					public Registration mapRow(ResultSet result, int rowNum) throws SQLException{
					    Registration rg=new Registration();
						rg.setOIB(result.getString("OIB"));
						rg.setIme(result.getString("ime"));
						rg.setPrezime(result.getString("prezime"));
						rg.setSpol(result.getString("spol"));
						rg.setDatumr(result.getString("datumr"));
						rg.setEmail(result.getString("email"));
						rg.setSifra(result.getString("sifra"));
						
						return rg;
						
					}});
					
				for (Registration registration : reg) {
					System.out.println(registration.toString());
					rg.add(registration);
					temp=registration;
					
				}
				}
			
			return rg;
			
		}
		
		@RequestMapping(value= {"/1e2b3tzrUZcvn"},method=RequestMethod.GET, produces="application/json")
		public String RemoteService(Model model){
			
		   model.addAttribute("lista",korisnik());
	 		return "1e2b3tzrUZcvn";
		}
		/*@RequestMapping(value= "/1e2b3tzrUZcvn",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<WalkingActivity> spremi(Model model,@RequestBody WalkingActivity wal) {
			wk.spremiPodatke(wal);
			ResponseEntity<WalkingActivity> response = new ResponseEntity<WalkingActivity>(wal,HttpStatus.CREATED);
			model.addAttribute(wk);
			return response;
		}
		@RequestMapping(value= "/pGRHmge52511wwf",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<BikingActivity> spremi(Model model,@RequestBody BikingActivity bik) {
			bk.insert(bik);
			ResponseEntity<BikingActivity> response = new ResponseEntity<BikingActivity>(bik,HttpStatus.CREATED);
			model.addAttribute(bik);
			return response;
		}
		@RequestMapping(value= "/15zuIOPPgrfef5",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<RunningActivity> spremi(Model model,@RequestBody RunningActivity run) {
			rn.spremiPodatke(run);
			ResponseEntity<RunningActivity> response = new ResponseEntity<RunningActivity>(run,HttpStatus.CREATED);
			model.addAttribute(run);
			return response;
		}*/
		@RequestMapping(value= "/sumary",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<SummaryActivity> spremi(Model model,@RequestBody SummaryActivity sumact) {
			sumimp.dodajUkupno(sumact);
			ResponseEntity<SummaryActivity> response = new ResponseEntity<SummaryActivity>(sumact,HttpStatus.CREATED);
			model.addAttribute(sumact);
			return response;
		}
		@RequestMapping(value= "/bike",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<SummaryBiking> spremi(Model model,@RequestBody SummaryBiking sumbik) {
			sumimp.dodaj(sumbik);
			ResponseEntity<SummaryBiking> response = new ResponseEntity<SummaryBiking>(sumbik,HttpStatus.CREATED);
			model.addAttribute(sumbik);
			return response;
		}
		@RequestMapping(value= "/run",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<SummaryRunning> spremi(Model model,@RequestBody SummaryRunning run) {
			sumimp.dodaj(run);
			ResponseEntity<SummaryRunning> response = new ResponseEntity<SummaryRunning>(run,HttpStatus.CREATED);
			model.addAttribute(run);
			return response;
		}
		@RequestMapping(value= "/walk",method=RequestMethod.POST,consumes="application/json",produces="application/json")
		public @ResponseBody ResponseEntity<WalkingStatistika> spremi(Model model,@RequestBody WalkingStatistika walk) {
			sumimp.dodaj(walk);
			ResponseEntity<WalkingStatistika> response = new ResponseEntity<WalkingStatistika>(walk,HttpStatus.CREATED);
			model.addAttribute(walk);
			return response;
		}
		}

