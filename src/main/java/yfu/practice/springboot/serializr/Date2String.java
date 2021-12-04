package yfu.practice.springboot.serializr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class Date2String extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(new Date(Long.parseLong(p.getText())));
        } catch (NumberFormatException e) {		// 若text不是數字時，直接返回字串
            return p.getText();
        }
    }
}