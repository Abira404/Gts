package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.UserPojo;

public class DbQueries {

	public static String registerUser(UserPojo userpojo) {
		String fname = userpojo.getFname();
		String lname = userpojo.getLname();
		String email = userpojo.getEmail();
		String phone = userpojo.getPhone();
		String pass = userpojo.getPass();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			String select = "select * from register where email='" + email + "'";
			con = DbConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs;

			rs = (ResultSet) stmt.executeQuery(select);

			if (rs.next()) {
				return "Email Already Exists";
			} else {

				String query = "insert into register(fname,lname,email,phone,pass) values (?,?,?,?,?)";
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, fname);
				preparedStatement.setString(2, lname);
				preparedStatement.setString(3, email);
				preparedStatement.setString(4, phone);
				preparedStatement.setString(5, pass);

				int i = preparedStatement.executeUpdate();

				if (i != 0)
					return "SUCCESS";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Oops... Something went wrong there..!"; 
	}

}
