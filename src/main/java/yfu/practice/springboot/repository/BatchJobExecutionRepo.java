package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.BatchJobExecution;

@Repository
public interface BatchJobExecutionRepo extends JpaRepository<BatchJobExecution, Long> {

}