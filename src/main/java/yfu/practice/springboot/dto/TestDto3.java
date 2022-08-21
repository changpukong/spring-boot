package yfu.practice.springboot.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TestDto3 {
	
	private BigDecimal yfuBalance;
	
	private Date yfuDate;
	
	private Timestamp yfuTimestamp;
	
	private String yfuName;

}