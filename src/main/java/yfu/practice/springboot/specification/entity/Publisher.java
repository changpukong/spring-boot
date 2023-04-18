package yfu.practice.springboot.specification.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Publisher implements Serializable {

    @Id
    @Column(name = "PUBLISHER_ID")
    private String publisherId;

    @Column(name = "PUBLISHER_NAME")
    private String publisherName;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Book> books;
}
