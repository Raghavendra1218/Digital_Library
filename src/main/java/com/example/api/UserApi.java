package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BorrowBookDTO;
import com.example.entity.Book;
import com.example.entity.Transaction;
import com.example.entity.User;
import com.example.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/users")
public class UserApi {
	@Autowired
	private UserService userService;
	@PostMapping
	public User addNewUser(@RequestBody @Valid User user) {
		User u=userService.addNewUser(user);
		return u;
	}
	@PostMapping("/borrow")
	public Book borrowBook(@RequestBody @Valid BorrowBookDTO borrowDTO) {
		// TODO Auto-generated method stub
		return userService.borrowBook(borrowDTO);
	}
	@GetMapping("/stock-check/")
	public String checkAvailability(@RequestParam("Id") int bookId) {
		// TODO Auto-generated method stub
		
		if( userService.checkAvailability(bookId))
			return "Available";
		else
			return "Not Available";
	}
	@PutMapping("/return/{tid}")
	public Transaction returnBook(@PathVariable("tid") int tid) {
		return userService.returnBook(tid);
	}
	@GetMapping("/transactions/")
	public List<Transaction> chkTransactionByUse(@RequestParam("userId")int userId) {
		// TODO Auto-generated method stub
		return userService.chkTransactionByUser(userId);}
	
}
