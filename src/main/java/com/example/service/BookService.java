package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dto.BookDTO;
import com.example.entity.Authour;
import com.example.entity.Book;
import com.example.entity.Genre;

public interface BookService {
	Authour addAuthour(Authour authour);
	Book searchBookById(int bookId);
	Book addBook(BookDTO book);
	List<Book> allBooks();
	Page<Book> allBookswithPagination(int pageNo,int size);
	List<Book> searchByAuthour(int authourId);
	List<Book> searchByGenre(Genre genre);
	Book updateBook(BookDTO book,int bookId);
	void removeBook(int bookId);

	List<Book> searchByBookName(String bookName);
	public Book updateCost(int bookId, float cost);

}
