package yfu.practice.springboot.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "Department")
@XmlAccessorType(XmlAccessType.FIELD)
public class DepartmentDto {

	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Group")
	private String group;

	public DepartmentDto() {
	}

	public DepartmentDto(String name, String group) {
		this.name = name;
		this.group = group;
	}
}