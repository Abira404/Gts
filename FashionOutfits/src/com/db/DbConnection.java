package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DbConnection implements ServletContextListener {

	static Connection connection = null;

	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			System.out
					.println("-------contentbased connection closed successfully------");

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/fashion", "root", "root");
			context.setAttribute("connection", connection);
			System.out
					.println("-------ContentBased connection opened successfully------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
}