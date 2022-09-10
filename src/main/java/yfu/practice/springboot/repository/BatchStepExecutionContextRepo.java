package yfu.practice.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yfu.practice.springboot.entity.BatchStepExecutionContext;

@Repository
public interface BatchStepExecutionContextRepo extends JpaRepository<BatchStepExecutionContext, Long> {

}