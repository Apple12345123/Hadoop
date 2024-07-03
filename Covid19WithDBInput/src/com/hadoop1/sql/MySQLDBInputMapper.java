package com.hadoop1.sql;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MySQLDBInputMapper extends Mapper<LongWritable, Covid19EntityRecoder, Covid19EntityRecoder, NullWritable> {

	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Covid19EntityRecoder ivalue, Context context)
			throws IOException, InterruptedException {
		System.out.println("key : " + ikey);
		System.out.println(ivalue);
		context.write(ivalue, n);
	}

}
