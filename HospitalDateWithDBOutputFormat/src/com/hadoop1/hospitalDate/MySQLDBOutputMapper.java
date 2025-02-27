package com.hadoop1.hospitalDate;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class MySQLDBOutputMapper extends Mapper<LongWritable, Text, HospitalDateEntityRecorder, NullWritable> {
	
	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		if(ikey.get() != 0) {
			String[] strs = ivalue.toString().split(",");
			HospitalDateEntityRecorder writable = new HospitalDateEntityRecorder(
				strs[0],
				strs[1],
				strs[2]
			);
			context.write(writable, n);
		}
	} // end map
	
	public double isNumeric(String strNum) {
		if(strNum == null) return 0;
		try {
		double d = Double.parseDouble(strNum);
		return d;
		} catch(NumberFormatException e) {
			return 0;
		}
	}
	
	

}
