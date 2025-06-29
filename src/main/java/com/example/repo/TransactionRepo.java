package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Transaction;
import com.example.entity.User;

public interface TransactionRepo  extends JpaRepository<Transaction,Integer>{
	List<Transaction> findByUser(User u);
}
