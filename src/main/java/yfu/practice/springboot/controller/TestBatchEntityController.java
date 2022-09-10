package yfu.practice.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yfu.practice.springboot.entity.BatchJobExecution;
import yfu.practice.springboot.entity.BatchJobExecutionContext;
import yfu.practice.springboot.entity.BatchJobInstance;
import yfu.practice.springboot.entity.BatchStepExecution;
import yfu.practice.springboot.entity.BatchStepExecutionContext;
import yfu.practice.springboot.repository.BatchJobExecutionContextRepo;
import yfu.practice.springboot.repository.BatchJobExecutionRepo;
import yfu.practice.springboot.repository.BatchJobInstanceRepo;
import yfu.practice.springboot.repository.BatchStepExecutionContextRepo;
import yfu.practice.springboot.repository.BatchStepExecutionRepo;

@RestController
@RequestMapping("/testBatchEntity")
public class TestBatchEntityController {
	
	@Autowired
	private BatchJobExecutionRepo batchJobExecutionRepo;
	
	@Autowired
	private BatchJobExecutionContextRepo batchJobExecutionContextRepo;
	
	@Autowired
	private BatchJobInstanceRepo batchJobInstanceRepo;
	
	@Autowired
	private BatchStepExecutionRepo batchStepExecutionRepo;
	
	@Autowired
	private BatchStepExecutionContextRepo batchStepExecutionContextRepo;
	
	@GetMapping(value = "/test")
	public void test() throws Exception {
		long jobId = 700;
		BatchJobExecution batchJobExecution = batchJobExecutionRepo.findById(jobId).orElseThrow(Exception::new);
		batchJobExecution.getBatchJobExecutionContext();
		batchJobExecution.getBatchJobInstance();
		batchJobExecution.getBatchJobExecutionParamsSet();
		batchJobExecution.getBatchStepExecutionSet();
		
		BatchJobExecutionContext batchJobExecutionContext = batchJobExecutionContextRepo.findById(jobId).orElseThrow(Exception::new);
		batchJobExecutionContext.getBatchJobExecution();
		
		BatchJobInstance batchJobInstance = batchJobInstanceRepo.findById(jobId).orElseThrow(Exception::new);
		batchJobInstance.getBatchJobExecution();
		
		long stepId= 710;
		BatchStepExecution batchStepExecution = batchStepExecutionRepo.findById(stepId).orElseThrow(Exception::new);
		batchStepExecution.getBatchJobExecution();
		batchStepExecution.getBatchStepExecutionContext();
		
		BatchStepExecutionContext batchStepExecutionContext = batchStepExecutionContextRepo.findById(stepId).orElseThrow(Exception::new);
		batchStepExecutionContext.getBatchStepExecution();
	}

}