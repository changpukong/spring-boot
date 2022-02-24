package yfu.practice.springboot.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDto {

	@XmlElement(name = "Name")
	private String name;

	@XmlElement(name = "Age")
	private String age;

	@XmlElement(name = "Salary")
	private String salary;
	
	@XmlElement(name = "Department")
	private DepartmentDto department;
	
	public EmployeeDto() {
	}
	
	public EmployeeDto(String name, String age, String salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
}