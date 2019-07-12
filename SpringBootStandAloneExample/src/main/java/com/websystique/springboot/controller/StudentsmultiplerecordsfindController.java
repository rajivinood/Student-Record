package com.websystique.springboot.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.studentdetails.Studentdetail;

@RestController
@RequestMapping("/api")
public class StudentsmultiplerecordsfindController {
	@GetMapping(path = "/students/singlerecordfind")
	public @ResponseBody Studentdetail getitem(@RequestBody Studentdetail studentdetail) throws JSONException {
		int studid = studentdetail.getId();
		Connection c = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.10.10.20:5432/test", "postgres", "postgres");
			c.setAutoCommit(false);
			String sql = "SELECT firstname,lastname,address,city FROM STUDENT WHERE id = ?" ;
			System.out.println("--------"+sql);
			String firstname = null;
			String lastname = null;
			String address = null;
			String city = null;
			ResultSet rs;
			PreparedStatement stmt = c.prepareStatement(sql);	
			stmt.setInt(1, studid);
			rs = stmt.executeQuery();
			if (rs.next())
			{ 
				firstname=rs.getString(1);
				lastname=rs.getString(2);
				address=rs.getString(3);
				city=rs.getString(4);

			System.out.println("--------"+firstname);
			}
			studentdetail.setFirstname(firstname);
			studentdetail.setLastname(lastname);
			studentdetail.setAddress(address);
			studentdetail.setCity(city);
			rs.close(); 
			stmt.close();
			c.commit();
			c.close();
		}catch (Exception e)
		{
			e.printStackTrace();
	    }
		return studentdetail;
	}
}



