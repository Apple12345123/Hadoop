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
		
		DBConfiguration.configureDB(job.getConfiguration(), 
				"com.mysql.cj.jdbc.Driver",						// JDBC Driver
				"jdbc:mysql://10.100.203.114:3306/mental",	// connect db url
				"mentalCare",									// username
				"mental");
		
		DBInputFormat.setInput(
				job, 
				HospitalEntityRecorder.class,		// recode class 
				"hospital_id",
				"mon_start_time",
				"mon_end_time",
				"tue_start_time",
				"tue_end_time",
				"wed_start_time",
				"wed_end_time",
				"thu_start_time",
				"thu_end_time",
				"fri_start_time",
				"fri_end_time",
				"sat_start_time",
				"sat_end_time",
				"sun_start_time",
				"sun_end_time",
				"sun_holiday",
				"lunchtime",
				"holiday");
		
		job.setNumReduceTasks(0);		// Mapper only job
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(args)); // java.util.Arrays import
		int exitCode = ToolRunner.run(new Configuration(),new MySQLDBInputDriver(), args);
		System.out.println(exitCode);
		System.exit(exitCode);
	}

	

}
