package yfu.practice.springboot.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import yfu.practice.springboot.serializr.Date2String;
import yfu.practice.springboot.serializr.Timestamp2String;

@Data
public class TestDto2 {
    
    @JsonProperty("YfuName")
    @JsonAlias("YFU_NAME")
    private String yfuName;
    
    @JsonProperty("YfuBalance")
    @JsonAlias("YFU_BALANCE")
    private String yfuBalance;
    
    @JsonProperty("YfuDate")
    @JsonAlias("YFU_DATE")
    @JsonDeserialize(using = Date2String.class)		// 增加反序列化方式，使Date資料能轉成正確字串
    private String yfuDate;
    
    @JsonProperty("YfuTimestamp")
    @JsonAlias("YFU_TIMESTAMP")
    @JsonDeserialize(using = Timestamp2String.class)	// 增加反序列化方式，使Timestamp資料能轉成正確字串
    private String yfuTimestamp;
}