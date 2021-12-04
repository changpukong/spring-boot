package yfu.practice.springboot.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TestDtoDynamic {
    
	@JsonProperty("YfuName")
    private String yfuName;

    @JsonAnySetter
    private Map<String, Object> dynamicProp = new HashMap<>();

    @JsonAnyGetter	// 可平展化dynamicProp，也就是JSON字串中會直接以key-value顯示dynamicProp的內容
    public Map<String, Object> getDynamicProp() {
        return dynamicProp;
    }
}