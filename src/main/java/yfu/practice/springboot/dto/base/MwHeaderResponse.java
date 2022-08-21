package yfu.practice.springboot.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MwHeaderResponse {

	@JsonProperty("MSGID")
	private String msgId;
	
	@JsonProperty("SOURCECHANNEL")
	private String sourceChannel;
	
	@JsonProperty("TXNSEQ")
	private String txnSeq;
	
}