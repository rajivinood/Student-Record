package com.websystique.springboot.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.studentdetails.Studentdetail;

@RestController
@RequestMapping("/api")
public class StudentsmultiplerecordsController {
	@PostMapping(path = "/students/multirecords")
	public @ResponseBody Studentdetail[] getitem(@RequestBody Studentdetail []studentdetail) throws JSONException {
		System.out.println("--------");
	    for(int i = 0; i < studentdetail.length; i++) 
	    {	  
		String firstname = studentdetail[i].getFirstname();
		String lastname = studentdetail[i].getLastname();
		String address = studentdetail[i].getAddress();
		String city = studentdetail[i].getCity();
		Connection c = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.10.10.20:5432/test", "postgres", "postgres");
			c.setAutoCommit(false);
			String sql = "INSERT INTO STUDENT (firstname,lastname,address,city)" + "VALUES (?,?, ?,?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, firstname);
			stmt.setString(2, lastname);
			stmt.setString(3, address);
			stmt.setString(4, city);
			int j = stmt.executeUpdate();
			System.out.println(j);
			stmt.close();
			c.commit();
			c.close();
		}catch (Exception e)
		{
			e.printStackTrace();
	    }
	    }
		return studentdetail;
	}
}