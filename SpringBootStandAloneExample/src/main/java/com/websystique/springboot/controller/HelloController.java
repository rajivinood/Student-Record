package com.websystique.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.websystique.springboot.studentrequest.Studentrequest;
import com.websystique.springboot.studentresponse.Studentresponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "HelloController", description = "StudentDetailsController")
public class HelloController {
	   @RequestMapping(path = "/students/details22", method = RequestMethod.POST)
	   @ApiOperation(value = "Website Product Push **", notes = "Website Manual Product Push")
	   public @ResponseBody ArrayList<Studentresponse> getitem(@RequestBody Studentrequest []studentrequest) throws JSONException
	   {
		  Studentresponse studentresponse =null;
		  ArrayList<Studentresponse> studentresponselist = new ArrayList();
		  for(int i = 0; i < studentrequest.length; i++) 
		  {		
			  studentresponse = new Studentresponse();
			  int mark1 = studentrequest[i].getM1();
			  int mark2 = studentrequest[i].getM2();
			  int total = mark1 + mark2;
			  studentresponse.setTotal(total);
			  if(studentresponse.getTotal()>50)
			  { 
				  studentresponse.setStatus(true); 
			  }
			  else 
			  {
				  studentresponse.setStatus(false);
			  } 
			  StringBuffer string = new  StringBuffer(studentrequest[i].getFirstname());
			  string.append(studentrequest[i].getLastname());
			  studentresponse.setFullname(string);
			  studentresponselist.add(studentresponse);				
	    }
		  return  studentresponselist;		
	 }
}