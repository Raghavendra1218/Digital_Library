package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Penalty {
	@Id @GeneratedValue
	private int penaltyId;
	private int noOfDays;
	private float amount;
	private String remarks;
	
}
