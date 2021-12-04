package yfu.practice.springboot.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import yfu.practice.springboot.dto.TestDto;
import yfu.practice.springboot.dto.TestDto2;
import yfu.practice.springboot.dto.TestDtoDynamic;
import yfu.practice.springboot.dto.TestDtoDynamic2;

public class DoTestDto {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		// 測試TestDto與TestDto2
		String jsonStr = "{\"YfuName\":\"陳X東\",\"YfuBalance\":\"100\",\"YfuDate\":\"1638547200000\",\"YfuTimestamp\":\"1638622980000\"}";
		TestDto testDto = mapper.readValue(jsonStr, TestDto.class);
		TestDto2 testDto2 = mapper.readValue(jsonStr, TestDto2.class);
		
		System.out.println("Test1");
		System.out.println("TestDto: " + mapper.writeValueAsString(testDto));
		System.out.println("TestDto2: " + mapper.writeValueAsString(testDto2));
		System.out.println();
		
		// 測試TestDtoDynamic與TestDtoDynamic2
		String jsonStr2 = "{\"YfuName\":\"陳X東\",\"YfuBalance\":\"100\",\"YfuDate\":\"2021-12-04\",\"YfuTimestamp\":\"2021-12-04T21:03:00\"}";
		TestDtoDynamic testDtoDynamic = mapper.readValue(jsonStr2, TestDtoDynamic.class);
		TestDtoDynamic2 testDtoDynamic2 = mapper.readValue(jsonStr2, TestDtoDynamic2.class);
		
		System.out.println("Test2");
		System.out.println("TestDtoDynamic: " + mapper.writeValueAsString(testDtoDynamic));
		System.out.println("TestDtoDynamic2: " + mapper.writeValueAsString(testDtoDynamic2));
	}
}