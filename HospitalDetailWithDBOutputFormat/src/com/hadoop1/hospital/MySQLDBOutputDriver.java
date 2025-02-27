package com.hadoop1.hospital;

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

		job.setOutputKeyClass(HospitalEntityRecorder.class);
		job.setOutputValueClass(NullWritable.class);

		// import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
		job.setOutputFormatClass(DBOutputFormat.class);
		job.setNumReduceTasks(0); // reduce task create 0, Mapper only job

		// import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
		DBConfiguration.configureDB(job.getConfiguration(), "com.mysql.cj.jdbc.Driver", // JDBC Driver
				"jdbc:mysql://10.100.203.114:3306/mental", // connect db url
				"mentalCare", // username
				"mental" // password
		);

		DBOutputFormat.setOutput( // make INSERT query
				job, "hospital_id", "mon_start_time", "mon_end_time", "tue_start_time", "tue_end_time",
				"wed_start_time", "wed_end_time", "thu_start_time", "thu_end_time", "fri_start_time", "fri_end_time",
				"sat_start_time", "sat_end_time", "sun_start_time", "sun_end_time", "sun_holiday", "lunchtime",
				"holiday");

		FileInputFormat.addInputPath(job, new Path(args[0])); // set input file path
		int exitCode = job.waitForCompletion(true) ? 0 : 1;

		return exitCode;
	}

	public static void main(String[] args) throws Exception {
		// import org.apache.hadoop.conf.Configuration;
		int result = ToolRunner.run(new Configuration(), new MySQLDBOutputDriver(), args);
		System.out.println(result);
		System.exit(result);
	}

}
