package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDTO;
import com.example.entity.Authour;
import com.example.entity.Book;
import com.example.entity.Genre;
import com.example.service.BookServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")

public class BookApi {
	@Autowired
	private BookServiceImpl bookServiceImpl;
	@PostMapping("/authours")
	public Authour addNewAuthor(@RequestBody  @Valid Authour authour) {
		return bookServiceImpl.addAuthour(authour);
	}
	@PostMapping
	public Book addNewBook(@RequestBody  @Valid BookDTO book) {
		// TODO Auto-generated method stub

		return bookServiceImpl.addBook(book);
	}
	
	@GetMapping("/{bookId}")
	public Book searchBookById(@PathVariable int bookId) {
		// TODO Auto-generated method stub
		return bookServiceImpl.searchBookById(bookId);
	
		 
	}
	@GetMapping
	public List<Book> allBooks() {
		// TODO Auto-generated method stub
		return bookServiceImpl.allBooks();
	}
	@GetMapping("/search/genre/")
	public List<Book> searchByGenre(@RequestParam("genre") Genre genre) {
		// TODO Auto-generated method stub
		return bookServiceImpl.searchByGenre(genre);
	}
	@GetMapping("/search/authour/")
	public List<Book> searchByAuthour(@RequestParam("authourId") int authourId) {
		// TODO Auto-generated method stub
		return bookServiceImpl.searchByAuthour(authourId);
	}
	@GetMapping("/search/book-name/")
	public List<Book> searchByBookName(@RequestParam("book-name")String bookName) {
		// TODO Auto-generated method stub
		return bookServiceImpl.searchByBookName(bookName);
	}
	@GetMapping("/page")
	public List<Book> allBookswithPagination(@RequestParam("pageNo") int pageNo,@RequestParam("size") int size) {
		// TODO Auto-generated method stub
		
		return bookServiceImpl.allBookswithPagination(pageNo,size).toList();
	}
	@PostMapping("/update")
	public Book updateBook(@RequestBody @Valid BookDTO book,int bookId) {
		return bookServiceImpl.updateBook(book, bookId);
	}
	@PostMapping("/remove/{bookId}")
	public void removeBook(@PathVariable int bookId) {
		bookServiceImpl.removeBook(bookId);
		
	}

}
