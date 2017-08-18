package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.josip.physical.activity.indeks_tjelesne_mase.BMICalculator;
import com.josip.physical.activity.indeks_tjelesne_mase.BMIReprository;
@Controller
@RequestMapping(value="/")
public class IndexController {

@RequestMapping(method=GET)
public String index(){
	return "index";
}
@RequestMapping(value="home",method=GET)
public String home(){
	return "home";
}
}
