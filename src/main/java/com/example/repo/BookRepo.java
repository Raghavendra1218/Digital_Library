package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Authour;
import com.example.entity.Book;
import com.example.entity.Genre;


public interface BookRepo extends JpaRepository<Book,Integer>{
	List<Book> findByGenre(Genre genre);
	List<Book> findByAuthourId(Authour auth);
	List<Book> findByBookName(String bookName);

}
