package com.websystique.springboot.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.studentdetails.Studentdetail;

@RestController
@RequestMapping("/api")
public class StudentsgetallrecordsController {
	@PostMapping(path = "/students/getallrecords")
	public @ResponseBody ArrayList<Studentdetail> getitem() throws JSONException {	
	System.out.println("--------");
	ArrayList<Studentdetail> studentdetaillist = new ArrayList<Studentdetail>();
	Studentdetail studentdetail =null;
	Connection c = null;
	    try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.10.10.20:5432/test", "postgres", "postgres");
			c.setAutoCommit(false);
			String sql = "SELECT * FROM STUDENT" ;
			System.out.println("--------"+sql);
			ResultSet rs;
			PreparedStatement stmt = c.prepareStatement(sql);	
			rs = stmt.executeQuery();
			while (rs.next())
			{ 
				studentdetail = new Studentdetail();					
				studentdetail.setFirstname(rs.getString(1));
				studentdetail.setLastname(rs.getString(2));
				studentdetail.setAddress(rs.getString(3));
				studentdetail.setCity(rs.getString(4));
				studentdetaillist.add(studentdetail);	
			}	
			rs.close(); 
			stmt.close();
			c.commit();
			c.close();		
		}catch (Exception e)
		{
			e.printStackTrace();
	    }	    
		return studentdetaillist;
	}
}



