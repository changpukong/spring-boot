package yfu.practice.springboot.jpa.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yfu.practice.springboot.jpa.entity.Card;

@Repository
public interface CardRepo extends JpaRepository<Card, String> {

  List<Card> findByTypeIn(Collection<String> types);
}
