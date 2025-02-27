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

		job.setOutputKeyClass(Covid19EntityRecoder.class);
		job.setOutputValueClass(NullWritable.class);

		job.setInputFormatClass(DBInputFormat.class);

		FileOutputFormat.setOutputPath(job, new Path(args[0]));

		DBConfiguration.configureDB(job.getConfiguration(), "com.mysql.cj.jdbc.Driver",
				"jdbc:mysql://10.100.203.58:3306/hadoop_test", "hadoop_test", "12345");

		DBInputFormat.setInput(job, Covid19EntityRecoder.class, // recode class
				"SELECT * FROM covid19", "SELECT count(*) FROM covid19");

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
