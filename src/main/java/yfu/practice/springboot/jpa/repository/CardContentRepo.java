package yfu.practice.springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.jpa.entity.CardContent;
import yfu.practice.springboot.jpa.entity.CardContentPK;

@Repository
public interface CardContentRepo extends JpaRepository<CardContent, CardContentPK> {}
