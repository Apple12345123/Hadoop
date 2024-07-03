package com.hadoop1.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class Covid19MaxDateEntityRecorder implements DBWritable{

	private String iso_code;
	private String date;
	private int new_cases;
	
	public Covid19MaxDateEntityRecorder() {}
	
	// alt + s , a
	public Covid19MaxDateEntityRecorder(String iso_code, String date, int new_cases) {
		super();
		this.iso_code = iso_code;
		this.date = date;
		this.new_cases = new_cases;
	}

	@Override
	public void readFields(ResultSet rs) throws SQLException {
		this.iso_code = rs.getString(1);
		this.date = rs.getString(2);
		this.new_cases = rs.getInt(3);
	}

	@Override
	public void write(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, iso_code);
		pstmt.setString(2, date);
		pstmt.setInt(3, new_cases);
		
	}

	// getter & setter
	// alt + s , r
	public String getIso_code() {
		return iso_code;
	}

	public void setIso_code(String iso_code) {
		this.iso_code = iso_code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNew_cases() {
		return new_cases;
	}

	public void setNew_cases(int new_cases) {
		this.new_cases = new_cases;
	}
	
	@Override
	public String toString() {
		return iso_code + "," + date + "," + new_cases;
	}
	
}
