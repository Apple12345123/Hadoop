package com.hadoop1.sql;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HospitalWithMySQLMapper extends Mapper<LongWritable, Text, HospitalEntityRecorder, NullWritable> {
	
	NullWritable n = NullWritable.get();

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		
		HospitalEntityRecorder recoder = new HospitalEntityRecorder();
// 암호화요양기호,요양기관명,종별코드,종별코드명,시도코드,시도코드명,시군구코드,시군구코드명,읍면동,우편번호,주소,전화번호,병원홈페이지,개설일자,총의사수,의과일반의 인원수,의과인턴 인원수,의과레지던트 인원수,의과전문의 인원수,치과일반의 인원수,치과인턴 인원수,치과레지던트 인원수,치과전문의 인원수,한방일반의 인원수,한방인턴 인원수,한방레지던트 인원수,한방전문의 인원수,조산사 인원수,좌표(X),좌표(Y)		
		String[] strs = ivalue.toString().split(",");
		String hospital_id = strs[0];
		String hospital_name = strs[1];
		String hospital_loc_code = strs[5];
	    String hospital_location = strs[10]; 
	    String hospital_website = strs[12];
	    String hospital_call = strs[11];
	    
	    recoder.setHospital_id(hospital_id);
		recoder.setHospital_name(hospital_name);
		recoder.setHospital_loc_code(hospital_loc_code);
		recoder.setHospital_location(hospital_location);
		recoder.setHospital_website(hospital_website);
		recoder.setHospital_call(hospital_call);
		
		if(hospital_name.indexOf("정신") > -1) {
			context.write(recoder, n);
		}
	}
}
