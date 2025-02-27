package com.hadoop1.test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		// Mapper : ["hello", 1]["World", 1]["Hello", 1]["Choi", 1]["Java", 1]
		// key : "hello", values : [1,1,1]
		// key : "Word", values : [1]
		// key : "Choi", values : [1]
		// key : "Java", values : [1]
		int total = 0;
		
		// process values
		for (IntWritable val : values) {
			total += val.get();
		}
		
		context.write(_key, new IntWritable(total));
	
	} // end reduce method
}


