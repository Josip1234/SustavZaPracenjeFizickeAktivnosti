package com.josip.physical.activity.web;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class BMIController {
	
 @RequestMapping(value="listaunesenihindeksa",method=GET)
 public String listaunesenihindeksa(){
	 return "listaunesenihindeksa";
 }

}
