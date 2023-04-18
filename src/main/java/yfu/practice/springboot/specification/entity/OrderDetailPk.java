package yfu.practice.springboot.specification.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class OrderDetailPk implements Serializable {

  private Integer orderId;

  private String isbn;
}
