package yfu.practice.springboot.jpa.entity;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "CARD_OWNER")
@Data
@NoArgsConstructor
public class CardOwner implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "CARD_ID")
  private String cardId; // 因為PK來自Card，所以沒設定自增主鍵

  @Column(name = "OWNER")
  private String owner;

  @MapsId // 讓Hibernate知道主鍵來自FK
  @OneToOne(fetch = FetchType.LAZY) // 此處沒加cascade依然可以對Card進行新增、修改，但不能刪除
  @JoinColumn(name = "CARD_ID")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Card card;

  public CardOwner(Card card, String owner) {
    this.card = card;
    this.owner = owner;
  }
}
