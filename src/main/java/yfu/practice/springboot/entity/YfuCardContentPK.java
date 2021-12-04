package yfu.practice.springboot.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class YfuCardContentPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String card;
    
    private Integer orderNumber;
    
    public YfuCardContentPK() {
    }
    
    public YfuCardContentPK(String card, Integer orderNumber) {
        this.card = card;
        this.orderNumber = orderNumber;
    }
}