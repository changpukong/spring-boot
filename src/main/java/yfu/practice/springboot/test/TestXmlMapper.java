package yfu.practice.springboot.test;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import yfu.practice.springboot.dto.XmlDataDto;
import yfu.practice.springboot.dto.XmlDto;

public class TestXmlMapper {
	
	public static void main(String[] args) throws JsonProcessingException {
		XmlDto xmlDto = new XmlDto();
		xmlDto.setXmlDataList(Arrays.asList(new XmlDataDto("1", "something"), new XmlDataDto("2", "something2")));
		
		System.err.println(new XmlMapper().writeValueAsString(xmlDto));
	}

}
