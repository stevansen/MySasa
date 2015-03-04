package it.unibz.mysasa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgres {

	private Connection connection = null;
	private String usr = "";
	private String pwd = "";

	public Connection getCon() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/mysasa", usr, pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	protected void dispose() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
