package com.example.entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id @GeneratedValue
	private int transactionId;
	@CreationTimestamp
	private LocalDate borrowedDate;
	private LocalDate returnedDate;
	@ManyToOne  @JoinColumn(name="userId")
	private User user;
	@ManyToOne @JoinColumn(name="bookId")
	private Book book;
	private float amount;
	private String status;
	@OneToOne @JoinColumn(name="penaltyId")
	private Penalty penaltyId;
}
