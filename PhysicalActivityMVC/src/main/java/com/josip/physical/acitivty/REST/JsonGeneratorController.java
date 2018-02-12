package com.josip.physical.acitivty.REST;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.josip.physical.activity.baza.SpringDataSource;
import com.josip.physical.activity.regist.Registration;
import com.josip.physical.activity.regist.RegistrationImpl;


@Controller
@RequestMapping({"/","/physical/","/1e2b3tzrUZcvn"})
public class JsonGeneratorController {

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
		@ResponseBody
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
	}

