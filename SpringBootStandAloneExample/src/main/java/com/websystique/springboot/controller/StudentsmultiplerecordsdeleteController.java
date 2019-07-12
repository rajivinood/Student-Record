package com.websystique.springboot.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.studentdetails.Studentdetail;

@RestController
@RequestMapping("/api")
public class StudentsmultiplerecordsdeleteController {
	@PutMapping(path = "/students/multirecordsdelete")
	public @ResponseBody Studentdetail getitem(@RequestBody Studentdetail studentdetail) throws JSONException {
		System.out.println("--------");
		int studid = studentdetail.getId();
		Connection c = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://10.10.10.20:5432/test", "postgres", "postgres");
			c.setAutoCommit(false);
			String sql = "DELETE FROM STUDENT WHERE id = ?" ;
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, studid);
			int i = stmt.executeUpdate();
			System.out.println(i);
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