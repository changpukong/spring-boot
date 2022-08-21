package yfu.practice.springboot.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseTemplate<T> {

	@JsonProperty("MWHEADER")
	private MwHeaderResponse mwHeader;
	
	@JsonProperty("TRANRS")
	private T tranrs;
	
}