package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.BatchStepExecution;

@Repository
public interface BatchStepExecutionRepo extends JpaRepository<BatchStepExecution, Long> {

}