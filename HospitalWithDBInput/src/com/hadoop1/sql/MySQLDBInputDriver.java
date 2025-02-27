package com.hadoop1.sql;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MySQLDBInputDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "HDFS From MySQL");

		job.setJarByClass(MySQLDBInputDriver.class);
		job.setMapperClass(MySQLDBInputMapper.class);

		job.setOutputKeyClass(HospitalEntityRecorder.class);
		job.setOutputValueClass(NullWritable.class);

		job.setInputFormatClass(DBInputFormat.class);

		FileOutputFormat.setOutputPath(job, new Path(args[0]));

		DBConfiguration.configureDB(job.getConfiguration(), "com.mysql.cj.jdbc.Driver", // JDBC Driver
				"jdbc:mysql://10.100.203.114:3306/mental", // connect db url
				"mentalCare", // username
				"mental");

		DBInputFormat.setInput(job, HospitalEntityRecorder.class, // recode class
				"hospital_id", "hospital_name", "hospital_loc_code", "hospital_location", "hospital_website",
				"hospital_call");

		job.setNumReduceTasks(0); // Mapper only job
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(args)); // java.util.Arrays import
		int exitCode = ToolRunner.run(new Configuration(), new MySQLDBInputDriver(), args);
		System.out.println(exitCode);
		System.exit(exitCode);
	}

}
