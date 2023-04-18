package yfu.practice.springboot.jpa.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
  @SequenceGenerator(name = "MEMBER_SEQ", allocationSize = 1)
  @Column(name = "MEMBER_ID")
  private Long memberId;

  @Column(name = "NAME")
  private String name;

  @ManyToMany(
      fetch = FetchType.LAZY,
      mappedBy = "editors") // 不建議加上CascadeType.REMOVE，避免刪除Member時，將該名Member有權異動的卡片全部刪除
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Card> cards;

  public Member(Long memberId) {
    this.memberId = memberId;
  }

  public Member(String name) {
    this.name = name;
  }
}
