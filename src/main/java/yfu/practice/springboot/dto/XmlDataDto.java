package yfu.practice.springboot.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	
@NoArgsConstructor
public class XmlDataDto {
	
	@JacksonXmlProperty(localName = "NO", isAttribute = true)
	private String no;	
	
	@JacksonXmlText
	private String data;
	
}