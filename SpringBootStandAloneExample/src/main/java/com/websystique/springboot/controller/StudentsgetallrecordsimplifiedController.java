package com.websystique.springboot.controller;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.studentdetails.Studentdetail;

@RestController
@RequestMapping("/api")
public class StudentsgetallrecordsimplifiedController {
	@PostMapping(path = "/students/findallrecords")
	public @ResponseBody ArrayList<Studentdetail> getitem() throws JSONException 
	{	
		studentservice studservice = new studentservice();
		ArrayList<Studentdetail> studentdetaillist = studservice.getstudentdetails();    
		return studentdetaillist;
	}
}



