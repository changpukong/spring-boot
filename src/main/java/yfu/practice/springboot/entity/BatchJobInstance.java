package yfu.practice.springboot.entity;

import java.io.Serializable;

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
@Table(name = "BATCH_JOB_INSTANCE")
@Data
public class BatchJobInstance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "JOB_INSTANCE_ID")
	private Long jobInstanceId;

	@Column(name = "VERSION")
	private Long version;
	
	@Column(name = "JOB_NAME")
	private String jobName;
	
	@Column(name = "JOB_KEY")
	private String jobKey;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
    @JoinColumn(name = "JOB_INSTANCE_ID")
	private BatchJobExecution batchJobExecution;
	
}