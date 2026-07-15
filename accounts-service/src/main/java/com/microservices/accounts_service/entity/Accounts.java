package com.microservices.accounts_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Accounts")
public class Accounts extends BaseEntity{

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="account_number")
	@Id
	private Long accountNumber;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="branch_name")
	private String branchName;
	
}
