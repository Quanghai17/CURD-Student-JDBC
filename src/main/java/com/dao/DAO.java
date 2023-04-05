package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Student;

public class DAO {
	
	String url = "jdbc:mysql://localhost:3306/hocsinh";
	String user = "root";
	String pass = "";
	
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/hocsinh";
		String user = "root";
		String pass = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	public List<Student> selectAll(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from student";
		
		List<Student>  list = new ArrayList<>();

		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Student s = new Student(rs.getInt("id"), rs.getString("hoten"), rs.getString("lop"));
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	public Student getById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from student where id=?";
		
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setHoten(rs.getString("hoten"));
				s.setLop(rs.getString("lop"));
				return s;
			}			
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void update(Student s) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "update student set hoten=?, lop=? where id=?";
		

		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, s.getHoten());
			ps.setString(2, s.getLop());
			ps.setInt(3, s.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insert(Student s) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "insert into student(hoten,lop) value(?,?)";
		

		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, s.getHoten());
			ps.setString(2, s.getLop());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "delete from student where id=?";
		

		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
