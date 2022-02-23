package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.YfuCardContent;
import yfu.practice.springboot.entity.YfuCardContentPK;

@Repository
public interface YfuCardContentRepo extends JpaRepository<YfuCardContent, YfuCardContentPK> {

}