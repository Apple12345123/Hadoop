package com.hadoop1.covid19;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MySQLDBOutputMapper extends Mapper<LongWritable, Text, Covid19EntityRecoder, NullWritable> {
	
	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		if(ikey.get() != 0) {
			String[] strs = ivalue.toString().split(",");
			Covid19EntityRecoder writable = new Covid19EntityRecoder(
				strs[0],
				strs[1],
				(int)isNumeric(strs[2]),
				(int)isNumeric(strs[3]),
				isNumeric(strs[4]),
				(int)isNumeric(strs[5]),
				isNumeric(strs[6]),
				(int)isNumeric(strs[7]),
				(int)isNumeric(strs[8]),
				isNumeric(strs[9]),
				(int)isNumeric(strs[10]),
				isNumeric(strs[11])

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

} // end mapper class
