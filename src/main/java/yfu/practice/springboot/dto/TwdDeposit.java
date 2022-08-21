package yfu.practice.springboot.dto;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yfu.practice.springboot.dto.base.Listable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwdDeposit implements Listable {

	private String idNo;
	
	private String type;
	
	private String acctNo;
	
	private String amt;
	
	@Override
	public List<String> toList() {
		return Arrays.asList(idNo, type, acctNo, amt);
	}
	
}