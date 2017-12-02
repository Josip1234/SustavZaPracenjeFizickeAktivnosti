package com.josip.physical.activity.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josip.physical.activity.baza.PhysicalActivityDatabase;
import com.josip.physical.activity.regist.Registration;
@Controller
@RequestMapping(value={"/index","/BMICalc"})
public class IndexController {

@RequestMapping(value="/index",method=GET)
public String index(){

	return "index";
}
@RequestMapping(value="/BMICalc",method=GET)
public String showBmiCalculator(){

	return "BMICalc";
}


}
