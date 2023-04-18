package yfu.practice.springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yfu.practice.springboot.jpa.entity.CardOwner;

@Repository
public interface CardOwnerRepo extends JpaRepository<CardOwner, String> {}
