package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.BatchJobInstance;

@Repository
public interface BatchJobInstanceRepo extends JpaRepository<BatchJobInstance, Long> {

}