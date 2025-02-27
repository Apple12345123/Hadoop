package com.hadoop1.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class HospitalEntityRecorder implements DBWritable{
	private String hospital_id;
	private String hospital_name;
	private String hospital_loc_code;
    private String hospital_location;
    private String hospital_website;
    private String hospital_call;
    
    public HospitalEntityRecorder() {}
    
	public HospitalEntityRecorder(String hospital_id, String hospital_name, String hospital_loc_code, String hospital_location, String hospital_website, String hospital_call) {
		super();
		this.hospital_id = hospital_id;
		this.hospital_name = hospital_name;
		this.hospital_loc_code = hospital_loc_code;
		this.hospital_location = hospital_location;
		this.hospital_website = hospital_website;
		this.hospital_call = hospital_call;
	}


	@Override
	public void readFields(ResultSet rs) throws SQLException {
		hospital_id = rs.getString(1);
		hospital_name = rs.getString(2);
		hospital_loc_code = rs.getString(3);
		hospital_location = rs.getString(4);
		hospital_website = rs.getString(5);
		hospital_call = rs.getString(6);
	}

	@Override
	public void write(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, hospital_id);
		pstmt.setString(2, this.hospital_name);
		pstmt.setString(3, this.hospital_loc_code);
		pstmt.setString(4, hospital_location);
		pstmt.setString(5, hospital_website);
		pstmt.setString(6, hospital_call);
	}


	@Override
	public String toString() {
		return 
				hospital_id + ","
				+ hospital_name + ","
				+ hospital_loc_code + ","
				+ hospital_location + ","
				+ hospital_website + ","
				+ hospital_call;
	}

	public String getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getHospital_loc_code() {
		return hospital_loc_code;
	}

	public void setHospital_loc_code(String hospital_loc_code) {
		this.hospital_loc_code = hospital_loc_code;
	}

	public String getHospital_location() {
		return hospital_location;
	}

	public void setHospital_location(String hospital_location) {
		this.hospital_location = hospital_location;
	}

	public String getHospital_website() {
		return hospital_website;
	}

	public void setHospital_website(String hospital_website) {
		this.hospital_website = hospital_website;
	}

	public String getHospital_call() {
		return hospital_call;
	}

	public void setHospital_call(String hospital_call) {
		this.hospital_call = hospital_call;
	}

	
	
	
	

}
