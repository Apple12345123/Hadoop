package com.hadoop1.hospital;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MySQLDBOutputMapper extends Mapper<LongWritable, Text, HospitalEntityRecorder, NullWritable> {

	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		if (ikey.get() != 0) {
			String[] strs = ivalue.toString().split(",");
			HospitalEntityRecorder writable = new HospitalEntityRecorder(strs[0], strs[22], strs[23], strs[24],
					strs[25], strs[26], strs[27], strs[28], strs[29], strs[30], strs[31], strs[32], strs[33], strs[20],
					strs[21], strs[8], strs[16], strs[9]);
			context.write(writable, n);
		}
	} // end map

	public double isNumeric(String strNum) {
		if (strNum == null)
			return 0;
		try {
			double d = Double.parseDouble(strNum);
			return d;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
