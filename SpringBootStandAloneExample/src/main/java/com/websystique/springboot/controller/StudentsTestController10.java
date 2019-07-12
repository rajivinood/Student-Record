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
public class StudentsTestController10 {
	@PostMapping(path = "/students/records")
	public @ResponseBody Studentdetail getitem(@RequestBody Studentdetail studentdetail) throws JSONException {
		System.out.println("--------");

	    System.out.println("ok");
		int id = studentdetail.getId();
		String firstname = studentdetail.getFirstname();
		String lastname = studentdetail.getLastname();
		String address = studentdetail.getAddress();
		String city = studentdetail.getCity();
		Connection c = null;
		try
		{
			System.out.println("ok------------------------------------------------check");
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.10.10.20:5432/test", "postgres", "postgres");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			String sql = "INSERT INTO STUDENT (id,firstname,lastname,address,city)" + "VALUES (?, ?,?, ?,?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, firstname);
			stmt.setString(3, lastname);
			stmt.setString(4, address);
			stmt.setString(5, city);
			System.out.println("query" + sql);
			int i = stmt.executeUpdate();
			System.out.println(i);
			System.out.println("query222222222");
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

