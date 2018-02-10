package com.josip.physical.activity.baza;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

@Component("Spring jdbc baze")
public class SpringDataSource {
	   static JdbcTemplate obj;
	    static SimpleDriverDataSource ds;
	    static String DB_USERNAME="root";
	    static String DB_PASSWORD ="";
	    static String DB_URL = "jdbc:mysql://localhost:3306/physical";
	   
	    public SpringDataSource() {
	    	
	    }
	    
		public static JdbcTemplate getObj() {
			return obj;
		}


		public static void setObj(JdbcTemplate obj) {
			SpringDataSource.obj = obj;
		}


		public static SimpleDriverDataSource getDs() {
			return ds;
		}


		public static void setDs(SimpleDriverDataSource ds) {
			SpringDataSource.ds = ds;
		}


		public static String getDB_USERNAME() {
			return DB_USERNAME;
		}


		public static void setDB_USERNAME(String dB_USERNAME) {
			DB_USERNAME = dB_USERNAME;
		}


		public static String getDB_PASSWORD() {
			return DB_PASSWORD;
		}


		public static void setDB_PASSWORD(String dB_PASSWORD) {
			DB_PASSWORD = dB_PASSWORD;
		}


		public static String getDB_URL() {
			return DB_URL;
		}


		public static void setDB_URL(String dB_URL) {
			DB_URL = dB_URL;
		}


		public static DataSource getConn() {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl("jdbc:mysql://127.0.0.1/physical");
			ds.setUsername("root");
			ds.setPassword("");
			return ds;
		}
}
