package org.example.physical.activity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.physical.activity.regist.Registration;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/PhysicalActivity")
public class DatabaseController {
private DatabaseReprository databaseReprository;
public Registration rg;
@Autowired
public DatabaseController(DatabaseReprository databaseReprository){
	this.databaseReprository=databaseReprository;
}
@RequestMapping(method=RequestMethod.GET)
public String ispis(Model model){
	model.addAttribute(databaseReprository.ispisiImePrezimeMail("Josip","Bošnjak","jbosnjak3@gmail.com"));
	return "ispis";
}
}
