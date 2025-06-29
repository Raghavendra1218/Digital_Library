package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import com.example.dto.BookDTO;
import com.example.entity.Authour;
import com.example.entity.Book;
import com.example.entity.Genre;
import com.example.exception.ApplicationException;
import com.example.repo.AuthourRepo;
import com.example.repo.BookRepo;
@Component
public class BookServiceImpl implements BookService {
	@Autowired
	private AuthourRepo authourRepo;
	@Autowired
	private BookRepo bookRepo;
	@Override
	public Authour addAuthour(Authour authour) {
		// TODO Auto-generated method stub
		return authourRepo.save(authour);
	}

	@Override
	public Book searchBookById(int bookId) {
		// TODO Auto-generated method stub
		return bookRepo.findById(bookId).orElseThrow(()->new ApplicationException("Book not found with given id "+bookId));
	
		 
	}

	

	@Override
	public List<Book> allBooks() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public Page<Book> allBookswithPagination(int pageNo, int size) {
		// TODO Auto-generated method stub
		Pageable pageable= PageRequest.of(pageNo, size,Sort.by("bookName"));
		
		return bookRepo.findAll(pageable);
	}

	@Override
	public List<Book> searchByAuthour(int authourId) {
		// TODO Auto-generated method stub
		Authour auth=authourRepo.findById(authourId).get();
		return bookRepo.findByAuthourId(auth);
	}

	@Override
	public List<Book> searchByGenre(Genre genre) {
		// TODO Auto-generated method stub
		return bookRepo.findByGenre(genre);
	}

	@Override
	public void removeBook(int bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook = bookRepo.findById(bookId);

	    if (optionalBook.isPresent()) {
	        bookRepo.deleteById(bookId);
	    } else {
	        throw new ApplicationException("No Book is present with ID "+bookId);
	    }
		
	}

	@Override
	public List<Book> searchByBookName(String bookName) {
		// TODO Auto-generated method stub
		return bookRepo.findByBookName(bookName);
	}

	@Override
	public Book updateCost(int bookId, float cost) {
		// TODO Auto-generated method stub
		Book b=bookRepo.findById(bookId).orElseThrow(()->new ApplicationException("No book with id "+bookId));
		b.setCost(cost);
		bookRepo.save(b);
		
		return b;
	}

	@Override
	public Book addBook(BookDTO book) {
		// TODO Auto-generated method stub
		int authId=book.getAuthId();
		Optional<Authour> optionalAuth=authourRepo.findById(authId);
		if(optionalAuth.isPresent()) {
		
		Book b=new Book();
		Authour auth=optionalAuth.get();
		
		b.setAuthourId(auth);
		b.setBookId(book.getBookId());
		b.setBookName(book.getBookName());
		b.setCost(book.getCost());
		b.setGenre(book.getGenre());
		b.setPublishedDate(book.getPublishedDate());
		b.setStock(book.getStock());
		System.out.println(b.getBookId()+" "+b.getBookName()+" "+b.getCost()+" "+b.getStock()+" "+b.getAuthourId());
		return bookRepo.save(b);
		}
		else {
			throw new ApplicationException("Authour not found");}
	}

	@Override
	public Book updateBook(BookDTO book, int bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook=bookRepo.findById(bookId);
		if(optionalBook.isPresent()) {
			Book b=optionalBook.get();
			int authId=book.getAuthId();
			Optional<Authour> optionalAuth=authourRepo.findById(authId);
			if(optionalAuth.isPresent()) {
			Authour auth=optionalAuth.get();
			
			b.setAuthourId(auth);
			b.setBookId(book.getBookId());
			b.setBookName(book.getBookName());
			b.setCost(book.getCost());
			b.setGenre(book.getGenre());
			b.setPublishedDate(book.getPublishedDate());
			b.setStock(book.getStock());
			System.out.println(b.getBookId()+" "+b.getBookName()+" "+b.getCost()+" "+b.getStock()+" "+b.getAuthourId());
			return bookRepo.save(b);
			}
			else {
				throw new ApplicationException("Authour not found");}
			}
			else {
				throw new ApplicationException("Book not found");}
	}

}
