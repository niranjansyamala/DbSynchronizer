package com.kagami;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

@Entity
@Table(name = "transactionentity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6509861805406296156L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transactionentityid")
	private Long transactionEntityId;

	/*@Column(name = "STATUS")
	private String status;*/

	@Column(name = "statusremark")
	private String statusRemark;

	@Column(name = "entityname")
	private String entityName;

	@Column(name = "entitytype")
	private String entityType;

	@Column(name = "createdby")
	@CreatedBy
	private String createdBy;

	@Column
	@CreationTimestamp
	private java.sql.Timestamp createDateTime;

	@Column
	@UpdateTimestamp
	private java.sql.Timestamp updateDateTime;



	public TransactionEntity() {
	}

	public Long getTransactionEntityId() {
		return transactionEntityId;
	}

	public void setTransactionEntityId(Long transactionEntityId) {
		this.transactionEntityId = transactionEntityId;
	}

	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/

	public String getStatusRemark() {
		return statusRemark;
	}

	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}