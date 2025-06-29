package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Authour;

public interface AuthourRepo extends JpaRepository<Authour,Integer>{

}
