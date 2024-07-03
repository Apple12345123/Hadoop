package com.hadoop1.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class HospitalEntityRecorder implements DBWritable{
	private String hospital_id;
	private String mon_start_time;
	private String mon_end_time;
	private String tue_start_time;
    private String tue_end_time;
    private String wed_start_time;
    private String wed_end_time;
    private String thu_start_time;
	private String thu_end_time;
	private String fri_start_time;
    private String fri_end_time;
    private String sat_start_time;
    private String sat_end_time;
    private String sun_start_time;
	private String sun_end_time;
    private String sun_holiday;
    private String lunchtime;
    private String holiday;
    
    public HospitalEntityRecorder() {}
    
	public HospitalEntityRecorder(String hospital_id, String mon_start_time, String mon_end_time, String tue_start_time, String tue_end_time, 
									String wed_start_time, String wed_end_time, String thu_start_time, String thu_end_time, 
									String fri_start_time, String fri_end_time, String sat_start_time, String sat_end_time, 
									String sun_start_time, String sun_end_time, String sun_holiday,String lunchtime, String holiday) {
		super();
		this.hospital_id = hospital_id;
		this.mon_start_time = mon_start_time;
		this.mon_end_time = mon_end_time;
		this.tue_start_time = tue_start_time;
		this.tue_end_time = tue_end_time;
		this.wed_start_time = wed_start_time;
		this.wed_end_time = wed_end_time;
		this.thu_start_time = thu_start_time;
		this.thu_end_time = thu_end_time;
		this.fri_start_time = fri_start_time;
		this.fri_end_time = fri_end_time;
		this.sat_start_time = sat_start_time;
		this.sat_end_time = sat_end_time;
		this.sun_start_time = sun_start_time;
		this.sun_end_time = sun_end_time;
		this.sun_holiday = sun_holiday;
		this.lunchtime = lunchtime;
		this.holiday = holiday;
	}


	@Override
	public void readFields(ResultSet rs) throws SQLException {
		hospital_id = rs.getString(1);
		mon_start_time = rs.getString(2);
		mon_end_time = rs.getString(3);
		tue_start_time = rs.getString(4);
		tue_end_time = rs.getString(5);
		wed_start_time = rs.getString(6);
		wed_end_time = rs.getString(7);
		thu_start_time = rs.getString(8);
		thu_end_time = rs.getString(9);
		fri_start_time = rs.getString(10);
		fri_end_time = rs.getString(11);
		sat_start_time = rs.getString(12);
		sat_end_time = rs.getString(13);
		sun_start_time = rs.getString(14);
		sun_end_time = rs.getString(15);
		sun_holiday = rs.getString(16);
		lunchtime = rs.getString(17);
		holiday = rs.getString(18);
	}

	@Override
	public void write(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, this.hospital_id);
		pstmt.setString(2, this.mon_start_time);
		pstmt.setString(3, this.mon_end_time);
		pstmt.setString(4, this.tue_start_time);
		pstmt.setString(5, this.tue_end_time);
		pstmt.setString(6, this.wed_start_time);
		pstmt.setString(7, this.wed_end_time);
		pstmt.setString(8, this.thu_start_time);
		pstmt.setString(9, this.thu_end_time);
		pstmt.setString(10, this.fri_start_time);
		pstmt.setString(11, this.fri_end_time);
		pstmt.setString(12, this.sat_start_time);
		pstmt.setString(13, this.sat_end_time);
		pstmt.setString(14, this.sun_start_time);
		pstmt.setString(15, this.sun_end_time);
		pstmt.setString(16, this.sun_holiday);
		pstmt.setString(17, this.lunchtime);
		pstmt.setString(18, this.holiday);
	}


	@Override
	public String toString() {
		return 
				hospital_id + ","
				+ mon_start_time + ","
				+ mon_end_time + ","
				+ tue_start_time + ","
				+ tue_end_time + ","
				+ wed_start_time + ","
				+ wed_end_time + ","
				+ thu_start_time + ","
				+ thu_end_time + ","
				+ fri_start_time + ","
				+ fri_end_time + ","
				+ sat_start_time + ","
				+ sat_end_time + ","
				+ sun_start_time + ","
				+ sun_end_time + ","
				+ sun_holiday + ","
				+ lunchtime + ","
				+ holiday;
	}

	public String getHospital_id() {
		return hospital_id;
	}

	public String getMon_start_time() {
		return mon_start_time;
	}

	public String getMon_end_time() {
		return mon_end_time;
	}

	public String getTue_start_time() {
		return tue_start_time;
	}

	public String getTue_end_time() {
		return tue_end_time;
	}

	public String getWed_start_time() {
		return wed_start_time;
	}

	public String getWed_end_time() {
		return wed_end_time;
	}

	public String getThu_start_time() {
		return thu_start_time;
	}

	public String getThu_end_time() {
		return thu_end_time;
	}

	public String getFri_start_time() {
		return fri_start_time;
	}

	public String getFri_end_time() {
		return fri_end_time;
	}

	public String getSat_start_time() {
		return sat_start_time;
	}

	public String getSat_end_time() {
		return sat_end_time;
	}

	public String getSun_start_time() {
		return sun_start_time;
	}

	public String getSun_end_time() {
		return sun_end_time;
	}

	public String getSun_holiday() {
		return sun_holiday;
	}

	public String getLunchtime() {
		return lunchtime;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}

	public void setMon_start_time(String mon_start_time) {
		this.mon_start_time = mon_start_time;
	}

	public void setMon_end_time(String mon_end_time) {
		this.mon_end_time = mon_end_time;
	}

	public void setTue_start_time(String tue_start_time) {
		this.tue_start_time = tue_start_time;
	}

	public void setTue_end_time(String tue_end_time) {
		this.tue_end_time = tue_end_time;
	}

	public void setWed_start_time(String wed_start_time) {
		this.wed_start_time = wed_start_time;
	}

	public void setWed_end_time(String wed_end_time) {
		this.wed_end_time = wed_end_time;
	}

	public void setThu_start_time(String thu_start_time) {
		this.thu_start_time = thu_start_time;
	}

	public void setThu_end_time(String thu_end_time) {
		this.thu_end_time = thu_end_time;
	}

	public void setFri_start_time(String fri_start_time) {
		this.fri_start_time = fri_start_time;
	}

	public void setFri_end_time(String fri_end_time) {
		this.fri_end_time = fri_end_time;
	}

	public void setSat_start_time(String sat_start_time) {
		this.sat_start_time = sat_start_time;
	}

	public void setSat_end_time(String sat_end_time) {
		this.sat_end_time = sat_end_time;
	}

	public void setSun_start_time(String sun_start_time) {
		this.sun_start_time = sun_start_time;
	}

	public void setSun_end_time(String sun_end_time) {
		this.sun_end_time = sun_end_time;
	}

	public void setSun_holiday(String sun_holiday) {
		this.sun_holiday = sun_holiday;
	}

	public void setLunchtime(String lunchtime) {
		this.lunchtime = lunchtime;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	
	
	
	

}
