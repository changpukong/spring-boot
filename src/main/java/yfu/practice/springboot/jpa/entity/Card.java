package yfu.practice.springboot.jpa.entity;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import yfu.practice.springboot.jpa.generator.DateIdGenerator;

@Entity
@Table(name = "CARD")
@Data
public class Card implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARD_SEQ")
  @GenericGenerator(
      name = "CARD_SEQ", // 預設SEQUENCE名稱與Generator相同
      strategy = "yfu.practice.springboot.jpa.generator.DateIdGenerator",
      parameters = {
        @Parameter(name = DateIdGenerator.PREFIX_WORD, value = "C"),
        @Parameter(name = DateIdGenerator.DATE_FORMAT, value = "yyMMdd"),
        @Parameter(name = DateIdGenerator.SEQ_FORMAT, value = "%03d")
      })
  @Column(name = "CARD_ID")
  private String cardId;

  @Column(name = "TYPE")
  private String type;

  // 一對多
  @OneToMany(
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true, // 移除集合中的Entity，等同於刪除該筆資料
      /*
       * mappedBy表示此屬性不是本表格的欄位，而是對應到參考物件(CardContent)的屬性(card)
       * 以表格關係來看，mappedBy為外來鍵
       */
      mappedBy = "card")
  @ToString.Exclude // 因CardContent有參考到本類別，避免Lombok自動生成的toString()形成無窮迴圈
  @EqualsAndHashCode.Exclude
  private Set<CardContent> cardContents;

  // 一對一(兩個表格有相同PK)
  @OneToOne(
      fetch = FetchType.EAGER, // 一對一關聯中，非"關係擁有方"(無FK)一定會查詢關聯的資料，因此FetchType.LAZY等同於FetchType.EAGER
      cascade = CascadeType.ALL,
      mappedBy = "card")
  private CardOwner cardOwner;

  // 多對多
  @ManyToMany(
      fetch = FetchType.LAZY,
      /*
       * 此處的級聯是針對Member
       * 無CascadeType.REMOVE是避免刪除多對多關係(CardEditor)而誤刪Member
       */
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinTable( // 有@JoinTable是維護方
      name = "CARD_EDITOR",
      joinColumns = {
        /*
         * name為多方表格的欄位名稱，referencedColumnName為一方表格的欄位名稱
         * 若兩個欄位名稱一致，referencedColumnName可省略
         */
        @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
      },
      inverseJoinColumns = { // 另一個多方表格
        @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
      })
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Member> editors;  // 不可使用List，因為JPA在實作刪除關聯表時，是先將所有Entity刪除，再新增剩餘的Entity，會有效能問題
}
