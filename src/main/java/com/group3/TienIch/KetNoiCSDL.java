package com.group3.TienIch;

import java.sql.*;

public class KetNoiCSDL {
	private static String DBURL = "jdbc:postgresql://ec2-3-211-221-185.compute-1.amazonaws.com:5432/d86iro6m5d8jol";
	private static String USERNAME = "fesotjquwzbpbt";
	private static String PASSWORD = "c110b6b325409039072f9fc54879307063849523824f814313a7ffe7f60da7f6";

	public static Connection ketNoiPostgreSQL() {
		Connection conn = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
