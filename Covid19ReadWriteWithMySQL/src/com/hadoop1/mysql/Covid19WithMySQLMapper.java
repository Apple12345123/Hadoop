package com.hadoop1.mysql;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Covid19WithMySQLMapper extends Mapper<LongWritable, Covid19EntityRecorder, Text, Covid19MaxDateWritable> {

	public void map(LongWritable ikey, Covid19EntityRecorder ivalue, Context context) throws IOException, InterruptedException {
		
		String iso_code = ivalue.getIso_code();
		String date = ivalue.getDate();
		int new_cases = ivalue.getNew_cases();
		
		Covid19MaxDateWritable writable = new Covid19MaxDateWritable();
		writable.setDate(new Text(date));
		writable.setNew_cases(new IntWritable(new_cases));
		
		context.write(new Text(iso_code), writable);  // output reduce
	}

}
