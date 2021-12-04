package yfu.practice.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "YFU_CARD_CONTENT")
@IdClass(YfuCardContentPK.class)
@Data
public class YfuCardContent implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    /*
     * 若要新增多方時同時新增一方，要加上CascadeType.PERSIST
     * 極度不建議在多方使用CascadeType.ALL，避免砍一個多方時，將一方及一方設有Cascade的多方都一併刪除
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CARD_ID")
    private YfuCard card;
    
    @Id
    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "CONTENT")
    private String content;
    
    public YfuCardContent() {
    }
    
    public YfuCardContent(YfuCard card, Integer orderNumber, String content) {
    	this.card = card;
    	this.orderNumber = orderNumber;
    	this.content = content;
    }
}