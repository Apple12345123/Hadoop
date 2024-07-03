package com.hadoop1.test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// auto import		ctrl + shift + o
// save 			ctrl + s
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private final IntWritable one = new IntWritable(1);

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		
		String words[] = line.split(" ");
		for(String word : words) {
			context.write(new Text(word), one);
			// syso + ctrl + space
			System.out.println("word : " + word);
			System.out.println("one : " + one);
		}
	} // end map method
} // end WordMapperClass



