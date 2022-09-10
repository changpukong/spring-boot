package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import yfu.practice.springboot.entity.BatchJobExecutionContext;

public interface BatchJobExecutionContextRepo extends JpaRepository<BatchJobExecutionContext, Long> {

}