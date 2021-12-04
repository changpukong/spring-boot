package yfu.practice.springboot.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TestDtoDynamic2 {
    
	@JsonProperty("YfuName")
    private String yfuName;

    @JsonAnySetter
    private Map<String, Object> dynamicProp = new HashMap<>();
}