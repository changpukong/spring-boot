package yfu.practice.springboot.specification.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.Data;

@Entity
@IdClass(OrderDetailPk.class)
@Data
public class OrderDetail implements Serializable {

  @Id
  @Column(name = "ORDER_ID")
  private Integer orderId;

  @Id
  @Column(name = "ISBN")
  private String isbn;

  @Column(name = "QUANTITY")
  private Integer quantity;

  /*
   * 複合主鍵使用@IdClass時，@MapsId的value沒有效果；
   * 承上，若使用@EmbeddedId，則@MapsId的value可指定對應到@EmbeddedId物件的屬性名稱
   */
  @MapsId // 表示對應到Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ISBN")
  private Book book;
}
