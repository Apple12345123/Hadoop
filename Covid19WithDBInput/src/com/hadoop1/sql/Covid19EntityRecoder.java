package com.hadoop1.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class Covid19EntityRecoder implements DBWritable {

	private String iso_code;
	private String date;
	private int new_cases;
	private int new_cases_14_days;
	private double new_cases_14_days_100k;
	private int total_cases;
	private double total_cases_100k;
	private int new_deaths;
	private int new_deaths_14_days;
	private double new_deaths_14_days_100k;
	private int total_deaths;
	private double total_deaths_100k;

	public Covid19EntityRecoder() {
	}

	// alt + s , a
	public Covid19EntityRecoder(String iso_code, String date, int new_cases, int new_cases_14_days,
			double new_cases_14_days_100k, int total_cases, double total_cases_100k, int new_deaths,
			int new_deaths_14_days, double new_deaths_14_days_100k, int total_deaths, double total_deaths_100k) {
		super();
		this.iso_code = iso_code;
		this.date = date;
		this.new_cases = new_cases;
		this.new_cases_14_days = new_cases_14_days;
		this.new_cases_14_days_100k = new_cases_14_days_100k;
		this.total_cases = total_cases;
		this.total_cases_100k = total_cases_100k;
		this.new_deaths = new_deaths;
		this.new_deaths_14_days = new_deaths_14_days;
		this.new_deaths_14_days_100k = new_deaths_14_days_100k;
		this.total_deaths = total_deaths;
		this.total_deaths_100k = total_deaths_100k;
	}

	@Override
	public void readFields(ResultSet rs) throws SQLException {
		// ResultSet == one row result
		iso_code = rs.getString(1);
		date = rs.getString(2);
		new_cases = rs.getInt(3);
		new_cases_14_days = rs.getInt(4);
		new_cases_14_days_100k = rs.getDouble(5);
		total_cases = rs.getInt(6);
		total_cases_100k = rs.getDouble(7);
		new_deaths = rs.getInt(8);
		new_deaths_14_days = rs.getInt(9);
		new_deaths_14_days_100k = rs.getDouble(10);
		total_deaths = rs.getInt(11);
		total_deaths_100k = rs.getDouble(12);

	}

	@Override
	public void write(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, this.iso_code);
		pstmt.setString(2, this.date);
		pstmt.setInt(3, new_cases);
		pstmt.setInt(4, new_cases_14_days);
		pstmt.setDouble(5, new_cases_14_days_100k);
		pstmt.setInt(6, total_cases);
		pstmt.setDouble(7, total_cases_100k);
		pstmt.setInt(8, new_deaths);
		pstmt.setInt(9, new_deaths_14_days);
		pstmt.setDouble(10, new_deaths_14_days_100k);
		pstmt.setInt(11, total_deaths);
		pstmt.setDouble(12, total_deaths_100k);

	}

	// alt + s, s, s
	@Override
	public String toString() {
		return iso_code + "," + date + "," + new_cases + "," + new_cases_14_days + "," + new_cases_14_days_100k + ","
				+ total_cases + "," + total_cases_100k + "," + new_deaths + "," + new_deaths_14_days + ","
				+ new_deaths_14_days_100k + "," + total_deaths + "," + total_deaths_100k;
	}

}
