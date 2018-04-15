package com.learning.LearningRESTNavinTelusko;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
	Connection con=null;
	
	public AlienRepository() {
		String url="jdbc:mysql://localhost:3306/learning";
		String username="root";
		String password="learning@1991";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
	public List<Alien> getAliens(){
		List<Alien> aliens=new ArrayList<>();
		String sql="select * from alien";
		try {
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				Alien a=new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return aliens; 
	}
	public Alien getAlien(int id) {
		Alien alien=new Alien();
		String sql="select * from alien where id="+id;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				alien.setId(rs.getInt(1));
				alien.setName(rs.getString(2));
				alien.setPoints(rs.getInt(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return alien;
	}
	public void create(Alien a1) {
		// TODO Auto-generated method stub
		String sql="insert into alien values(?,?,?)";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
	}
	public void update(Alien a1) {
		// TODO Auto-generated method stub
		String sql="update alien set name=?, points=? where id=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
	public void kill(int id) {
		// TODO Auto-generated method stub
		String sql="delete from alien where id=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
	}

}
