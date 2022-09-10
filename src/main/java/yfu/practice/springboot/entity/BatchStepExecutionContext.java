package yfu.practice.springboot.entity;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "BATCH_STEP_EXECUTION_CONTEXT")
@Data
public class BatchStepExecutionContext implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STEP_EXECUTION_ID")
	private Long stepExecutionId;
	
	@Column(name = "SHORT_CONTEXT")
	private String shortContext;
	
	@Column(name = "SERIALIZED_CONTEXT")
	private transient Clob serializedContext;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
    @JoinColumn(name = "STEP_EXECUTION_ID")
	private BatchStepExecution batchStepExecution;
	
}