package com.hadoop1.had;

import org.apache.hadoop.fs.Path;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class WordCount {
	// ctrl + shift + o
	public static class MyMapper extends MapReduceBase 
	implements Mapper<LongWritable, Text, Text, IntWritable>{
		// Alt + s + v		produce override
		private final static IntWritable ONE = new IntWritable(1);
		
		private Text word = new Text();
		
		@Override
		public void map(LongWritable key, 
						Text value, 
						OutputCollector<Text, IntWritable> output, 
						Reporter reporter)
		
				throws IOException {
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			
			while(tokenizer.hasMoreTokens()) {
				String str = tokenizer.nextToken();
				word.set(str);
				output.collect(word, ONE);
			} // end while
		} // end map method
	} // end mapper class

	public static class MyReduce extends MapReduceBase
	implements Reducer<Text, IntWritable,Text, IntWritable> {

		@Override
		public void reduce(Text key, 
							Iterator<IntWritable> values, 
							OutputCollector<Text, IntWritable> output,
							Reporter reporter) throws IOException {
			int sum =0;
			
			while(values.hasNext()) {
				sum += values.next().get();
			} // end while
			
			output.collect(key, new IntWritable(sum));
			
		} // end reduce method
	} // end MyReduce class
	
	public static void main(String[] args) throws IOException {
		JobConf conf = new JobConf(WordCount.class);
		
		conf.setJobName("wordcount");
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		conf.setMapperClass(MyMapper.class);
		// Optional
		conf.setCombinerClass(MyReduce.class);
		conf.setReducerClass(MyReduce.class);
		
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		JobClient.runJob(conf);
	} // end main method
} // end WordCount
