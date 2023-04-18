package yfu.practice.springboot.jpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CARD_CONTENT")
@IdClass(CardContentPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardContent implements Serializable {

  private static final long serialVersionUID = 1L;

  /*
   * 若要新增多方時同時新增一方，要加上CascadeType.PERSIST
   * 不建議在多方使用CascadeType.ALL，避免砍一個多方時，將一方及一方設有Cascade的多方都一併刪除
   */
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CARD_ID")
  private Card card;

  @Id
  @Column(name = "ORDER_NUMBER")
  private Integer orderNumber;

  @Column(name = "CONTENT")
  private String content;
}
