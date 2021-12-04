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
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "YFU_MEMBER")
@Data
public class YfuMember implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YFU_MEMBER_SEQ")
    @SequenceGenerator(name = "YFU_MEMBER_SEQ", allocationSize = 1)
    @Column(name = "MEMBER_ID")
    private Long memberId;
    
    @Column(name = "NAME")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "editors", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<YfuCard> cards;
    
    public YfuMember() {
    }
    
    public YfuMember(String name) {
    	this.name = name;
    }
}