package com.hadoop1.mysql;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Covid19WithMySQLReducer extends Reducer<Text, Covid19MaxDateWritable, Covid19MaxDateEntityRecorder, NullWritable> {
	
	NullWritable n = NullWritable.get();

	public void reduce(Text _key, Iterable<Covid19MaxDateWritable> values, Context context) throws IOException, InterruptedException {
		
		int max = Integer.MIN_VALUE;
		
		Covid19MaxDateEntityRecorder maxRecorder = new Covid19MaxDateEntityRecorder();
		maxRecorder.setIso_code(_key.toString());
		
		// process values
		for (Covid19MaxDateWritable val : values) {

			if(max < val.getNew_cases().get()) {
				// new_cases max date
				max = val.getNew_cases().get();
				
				maxRecorder.setDate(val.getDate().toString());
				maxRecorder.setNew_cases(max);
			}
		} // end for
		
		context.write(maxRecorder, n);
	} // end reduce

}
