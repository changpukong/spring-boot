package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.YfuCard;

@Repository
public interface YfuCardRepo extends JpaRepository<YfuCard, String> {
}