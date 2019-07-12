package com.websystique.springboot.controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.websystique.springboot.studentdetails.Studentdetail;

public class studentservice {

	public ArrayList<Studentdetail> getstudentdetails() 
	{
		ArrayList<Studentdetail> studentdetaillist = new ArrayList<Studentdetail>();
		Studentdetail studentdetail =null;
		Connection c = null;
		try
		{			
			Connectionjdbcclass connectionjdbcclass = new Connectionjdbcclass();
			c=connectionjdbcclass.getconnection();
			c.setAutoCommit(false);
			String sql = StudConstantclass.CONSTANT_SELECT_ALL;
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
