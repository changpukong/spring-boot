package yfu.practice.springboot.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "root")
public class XmlDto {
	
	@JacksonXmlProperty(localName = "DATA")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<XmlDataDto> xmlDataList;

}