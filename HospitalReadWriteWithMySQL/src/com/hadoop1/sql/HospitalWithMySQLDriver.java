package com.hadoop1.sql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class HospitalWithMySQLDriver  extends Configured implements Tool{
	
	final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://10.100.203.114:3306/mental";
	final String DB_USER = "mentalCare";
	final String DB_PASS = "mental";
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new HospitalWithMySQLDriver(), args);
		// syso   ctrl + space
		System.out.println(exitCode);
		}

	@Override
	public int run(String[] arg0) throws Exception {
		
		Configuration conf = getConf();
		Job job = Job.getInstance(conf, "HDFS From MySQL");
		
		// main class regist
		job.setJarByClass(getClass());
		// mapper class regist
		job.setMapperClass(HospitalWithMySQLMapper.class);
		
		job.setOutputKeyClass(HospitalEntityRecorder.class);
		job.setOutputValueClass(NullWritable.class);
		
		
		// regist : input, output format
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(DBOutputFormat.class);
		
		// database connection config
		DBConfiguration.configureDB(job.getConfiguration(), DB_DRIVER, DB_URL, DB_USER, DB_PASS);
		
		job.setNumReduceTasks(0);
		
		// Output DB Setting
		// INSERT INTO covid19_max(iso_code, date, new_cases) VALUES(?,?,?);
		DBOutputFormat.setOutput(
					job,
					"hospital",			// table name
					"hospital_id",
					"hospital_name",
					"hospital_loc_code",
					"hospital_location",
					"hospital_website",
					"hospital_call"
		);
		
		FileInputFormat.addInputPath(job, new Path(arg0[0]));	// set input file path
		
		int exitCode = job.waitForCompletion(true) ? 0 : 1;
		return exitCode;
	}

}
