package com.example.repo;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<User,Integer>{

}
