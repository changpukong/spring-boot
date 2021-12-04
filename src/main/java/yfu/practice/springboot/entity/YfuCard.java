package yfu.practice.springboot.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import yfu.practice.springboot.generator.DateIdGenerator;

@Entity
@Table(name = "YFU_CARD")
@Data
public class YfuCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YFU_CARD_SEQ")
    @GenericGenerator(name = "YFU_CARD_SEQ", strategy = "yfu.practice.springboot.generator.DateIdGenerator",    // 預設SEQUENCE名稱與Generator相同
            parameters = { @Parameter(name = DateIdGenerator.PREFIX_WORD, value = "C"),
                    @Parameter(name = DateIdGenerator.DATE_FORMAT, value = "yyMMdd"),
                    @Parameter(name = DateIdGenerator.SEQ_FORMAT, value = "%03d") })
    @Column(name = "CARD_ID")
    private String cardId;

    @Column(name = "TYPE")
    private String type;
    
    // 一對多
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true,
            /*
             * mappedBy表示此屬性不是表格的欄位，而是對應到參考物件(YfuCardContent)的指定屬性(card)。
             * 被參考的屬性為外來鍵，是關聯這兩個表格的關鍵欄位。
             */
            mappedBy = "card")
    @ToString.Exclude           // 因YfuCardContent有參考到本類別，避免Lombok自動生成的toString形成無窮迴圈，故本屬性排除在toString外
    @EqualsAndHashCode.Exclude
    private Set<YfuCardContent> contents;

    // 一對一(兩表格有相同PK)，關鍵在YfuCardOwner
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "card")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private YfuCardOwner owner;

    // 多對多
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)	// 設為ALL也不會將YfuMember刪除，但保險起見還是設為PERSIST
    // 寫在維護方
    @JoinTable(name = "YFU_CARD_EDITOR",
        joinColumns = {
                /*
                 * name為多方表格的欄位名稱，referencedColumnName為一方表格的欄位名稱
                 * 若兩個欄位名稱一致，referencedColumnName可省略
                 */
                @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
        }, inverseJoinColumns = {   // 另一個多方表格
                @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
        }
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<YfuMember> editors;
}