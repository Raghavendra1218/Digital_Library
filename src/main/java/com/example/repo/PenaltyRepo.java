package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Penalty;

public interface PenaltyRepo  extends JpaRepository<Penalty,Integer>{

}
