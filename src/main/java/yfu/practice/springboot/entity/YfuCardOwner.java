package yfu.practice.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "YFU_CARD_OWNER")
@Data
public class YfuCardOwner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CARD_ID")
    private String cardId;      // 因所有操作都是透過YfuCard進行，所以沒設定自增主鍵
    
    @Column(name = "OWNER")
    private String owner;

    @OneToOne(fetch = FetchType.LAZY)     // 此處沒加cascade依然可以對YfuCard進行新增、修改，但不能刪除，要同時刪除則需自行加上cascade
    @MapsId                         // 共享主鍵的關鍵
    @JoinColumn(name = "CARD_ID")   // 指定此表格對應的欄位名稱
    private YfuCard card;
    
    public YfuCardOwner() {
    }
    
    public YfuCardOwner(YfuCard card, String owner) {
    	this.card = card;
    	this.owner = owner;
    }
}