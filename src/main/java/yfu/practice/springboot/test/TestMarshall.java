package yfu.practice.springboot.test;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import yfu.practice.springboot.dto.xml.DepartmentDto;
import yfu.practice.springboot.dto.xml.EmployeeDto;

public class TestMarshall {
	
	public static void main(String[] args) throws JAXBException {
		// Data
		EmployeeDto employee = new EmployeeDto("Horo", "500", "100000");
		employee.setDepartment(new DepartmentDto("SDD", "PG"));
		
		// Method 1
		JAXBContext context = JAXBContext.newInstance(EmployeeDto.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(employee, System.err);
		System.err.println();
		
		// Method 2
		Map<String, Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Jaxb2Marshaller springMarshaller = new Jaxb2Marshaller();
		springMarshaller.setPackagesToScan("yfu.practice.springboot.dto.xml");
		springMarshaller.setMarshallerProperties(properties);
		springMarshaller.createMarshaller().marshal(employee, System.err);
	}

}