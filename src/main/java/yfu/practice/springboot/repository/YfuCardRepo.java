package yfu.practice.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.YfuCard;

@Repository
public interface YfuCardRepo extends JpaRepository<YfuCard, String> {
	
	List<YfuCard> findByTypeIn(Collection<String> types);
}