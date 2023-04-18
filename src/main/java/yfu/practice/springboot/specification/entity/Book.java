package yfu.practice.springboot.specification.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Book implements Serializable {

  @Id
  @Column(name = "ISBN")
  private String isbn;

  @Column(name = "BOOK_NAME")
  private String bookName;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "AUTHOR")
  private String author;

  @Column(name = "PUBLICATION_DATE")
  private Date publicationDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "PUBLISHER_ID")
  private Publisher publisher;

  @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<OrderDetail> orderDetails;
}
