package com.lti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServe() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int eid=Integer.parseInt(request.getParameter("EmpId"));
		String ename=request.getParameter("EmpName");
		
		PrintWriter out=response.getWriter();
		out.println(eid+ "   "+ename);
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties prop=new Properties();
		prop.load(is);
		
		String driver=(String)prop.get("db.driver");
		String url=(String)prop.get("db.url");
		String user=(String)prop.get("db.user");
		String pass=(String)prop.get("db.pass");
		try{
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,user,pass);
			out.print("Conection obtained....");
			PreparedStatement p=con.prepareStatement("insert into Emp12 values(?,?)");
			p.setInt(1,eid);
			p.setString(2,ename);
			
			
			int k=p.executeUpdate();
			if(k>0)
				out.print("Entry accepted!!");
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		out.close();
	    
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
