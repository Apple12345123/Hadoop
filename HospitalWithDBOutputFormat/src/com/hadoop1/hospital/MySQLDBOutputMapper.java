package com.hadoop1.hospital;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MySQLDBOutputMapper extends Mapper<LongWritable, Text, HospitalEntityRecorder, NullWritable>{
	
	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		if(ikey.get() != 0) {
			String[] strs = ivalue.toString().split(",");
			HospitalEntityRecorder writable = new HospitalEntityRecorder(
				strs[0],
				strs[1],
				strs[5],
				strs[10],
				strs[12],
				strs[11]);
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
