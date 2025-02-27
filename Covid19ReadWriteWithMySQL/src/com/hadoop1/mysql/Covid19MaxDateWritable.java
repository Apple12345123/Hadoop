package com.hadoop1.mysql;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class Covid19MaxDateWritable implements Writable {
	
	private Text date;
	private IntWritable new_cases;
	
	public Covid19MaxDateWritable() {
		this.date = new Text();
		this.new_cases = new IntWritable(0);
	}
	
	// alt + s , a
	public Covid19MaxDateWritable(Text date, IntWritable new_cases) {
		super();
		this.date = date;
		this.new_cases = new_cases;
	}



	// alt + s, v
	// override method
	@Override
	public void readFields(DataInput in) throws IOException {
		this.date.readFields(in);
		this.new_cases.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		this.date.write(out);
		this.new_cases.write(out);
	}

	// getter & setter
	// alt + s , r
	public Text getDate() {
		return date;
	}

	public void setDate(Text date) {
		this.date = date;
	}

	public IntWritable getNew_cases() {
		return new_cases;
	}

	public void setNew_cases(IntWritable new_cases) {
		this.new_cases = new_cases;
	}

	// generate toString
	// alt + s , s, s
	@Override
	public String toString() {
		return date + "\t" + new_cases;
	}

}
