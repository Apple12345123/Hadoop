package com.hadoop1.covid19;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class MySQLDBOutputDriver extends Configured implements Tool {
	
	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "MySQL Writer");
		
		job.setJarByClass(MySQLDBOutputDriver.class);
		job.setMapperClass(MySQLDBOutputMapper.class);
		
		job.setOutputKeyClass(Covid19EntityRecoder.class);
		job.setOutputValueClass(NullWritable.class);
		
		// import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
		job.setOutputFormatClass(DBOutputFormat.class);
		job.setNumReduceTasks(0);	// reduce task create 0, Mapper only job
		
		// import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
		DBConfiguration.configureDB(
				job.getConfiguration(),
				"com.mysql.cj.jdbc.Driver",						// JDBC Driver
				"jdbc:mysql://10.100.203.58:3306/hadoop_test",	// connect db url
				"hadoop_test",									// username
				"12345"											// password
		);
		
		DBOutputFormat.setOutput(			// make INSERT query
				job,
				"covid19",					// output table name 
				"iso_code", 				// pastmt set column name
				"date",
				"new_cases",
				"new_cases_14_days",
				"new_cases_14_days_100k",
				"total_cases",
				"total_cases_100k",
				"new_deaths",
				"new_deaths_14_days",
				"new_deaths_14_days_100k",
				"total_deaths",
				"total_deaths_100k");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));	// set input file path
		int exitCode = job.waitForCompletion(true) ? 0:1;
		
		return exitCode;
	}

	public static void main(String[] args) throws Exception {
		// import org.apache.hadoop.conf.Configuration;
		int result = ToolRunner.run(new Configuration(), new MySQLDBOutputDriver(), args);
		System.out.println(result);
		System.exit(result);
	}

}
