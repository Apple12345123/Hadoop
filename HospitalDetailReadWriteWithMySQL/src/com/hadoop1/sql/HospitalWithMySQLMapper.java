package com.hadoop1.sql;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HospitalWithMySQLMapper extends Mapper<LongWritable, Text, HospitalEntityRecorder, NullWritable> {

	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {

		HospitalEntityRecorder recoder = new HospitalEntityRecorder();
// [JDQ4MTAxMiM1MSMkMSMkMCMkMDMkMzgxMTkxIzExIyQxIyQ3IyQ4OSQyNjEyMjIjNzEjJDEjJDgjJDgz, 현대온누리약국, 김호일정형외과옆, 부산과학기술대 방향, , , , , , , N, , , N]
		if (ikey.get() != 0) {

			String[] strs = ivalue.toString().split(",", 40);

			String hospital_id = strs[0];
			String mon_start_time = strs[22];
			String mon_end_time = strs[23];
			String tue_start_time = strs[24];
			String tue_end_time = strs[25];
			String wed_start_time = strs[26];
			String wed_end_time = strs[27];
			String thu_start_time = strs[28];
			String thu_end_time = strs[29];
			String fri_start_time = strs[30];
			String fri_end_time = strs[31];
			String sat_start_time = strs[32];
			String sat_end_time = strs[33];
			String sun_start_time = strs[20];
			String sun_end_time = strs[21];
			String sun_holiday = strs[8];
			String lunchtime = strs[16];
			String holiday = strs[9];

			recoder.setHospital_id(hospital_id);
			recoder.setMon_start_time(mon_start_time);
			recoder.setMon_end_time(mon_end_time);
			recoder.setTue_start_time(tue_start_time);
			recoder.setTue_end_time(tue_end_time);
			recoder.setWed_start_time(wed_start_time);
			recoder.setWed_end_time(wed_end_time);
			recoder.setThu_start_time(thu_start_time);
			recoder.setThu_end_time(thu_end_time);
			recoder.setFri_start_time(fri_start_time);
			recoder.setFri_end_time(fri_end_time);
			recoder.setSat_start_time(sat_start_time);
			recoder.setSat_end_time(sat_end_time);
			recoder.setSun_start_time(sun_start_time);
			recoder.setSun_end_time(sun_end_time);
			recoder.setSun_holiday(sun_holiday);
			recoder.setLunchtime(lunchtime);
			recoder.setHoliday(holiday);
			
			context.write(recoder, n);
		}

	}
}
