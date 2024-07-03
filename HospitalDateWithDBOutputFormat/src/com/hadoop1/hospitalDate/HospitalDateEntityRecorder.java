package com.hadoop1.hospitalDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class HospitalDateEntityRecorder implements DBWritable{
	private String hospital_week;
    private String start_time;
    private String end_time;
    
    public HospitalDateEntityRecorder() {}
    
    

	public HospitalDateEntityRecorder(String hospital_week, String start_time, String end_time) {
		super();
		this.hospital_week = hospital_week;
		this.start_time = start_time;
		this.end_time = end_time;
	}



	public void readFields(ResultSet rs) throws SQLException {
		hospital_week = rs.getString(1);
		start_time = rs.getString(2);
		end_time = rs.getString(3);
		
	}

	public void write(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, this.hospital_week);
		pstmt.setString(2, this.start_time);
		pstmt.setString(3, end_time);
		
	}



	public String toString() {
		return hospital_week + ","
				+ start_time + ","
				+ end_time;
	}
	

}
