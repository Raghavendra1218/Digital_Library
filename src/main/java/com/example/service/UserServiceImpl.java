package com.example.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.BorrowBookDTO;
import com.example.entity.Book;
import com.example.entity.Penalty;
import com.example.entity.Transaction;
import com.example.entity.User;
import com.example.exception.ApplicationException;
import com.example.repo.BookRepo;
import com.example.repo.PenaltyRepo;
import com.example.repo.TransactionRepo;
import com.example.repo.UserRepo;
@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private PenaltyRepo penaltyRepo;
	@Autowired
	private TransactionRepo trxnRepo;
	@Override
	public User addNewUser(User u) {
		// TODO Auto-generated method stub
		
	    
		User user=userRepo.save(u);
		return user;
	}

	@Override
	public boolean checkAvailability(int bookId) {
		// TODO Auto-generated method stub
		
		return bookRepo.findById(bookId).orElseThrow(()->new ApplicationException("Book not found with ID "+bookId) ).getStock()>0;
	}

	@Override
	public List<Transaction> chkTransactionByUser(int userId) {
		// TODO Auto-generated method stub
		User u=userRepo.findById(userId).orElseThrow(()->new ApplicationException("User not found with ID "+userId));
		return trxnRepo.findByUser(u);
	}

	@Override
	public Book borrowBook(BorrowBookDTO borrowDTO) {
		// TODO Auto-generated method stub
		int userId=borrowDTO.getUserId();
		int bookId=borrowDTO.getBookId();
		User user=userRepo.findById(userId).orElseThrow(()->new ApplicationException("User not found with ID "+userId));
		Book book=bookRepo.findById(bookId).orElseThrow(()->new ApplicationException("Book not found with ID "+bookId));
		if(book.getStock()==0)
			throw new ApplicationException("Stock over for book Id "+book.getBookId()+" "+book.getBookName());
		Transaction tr=new Transaction();
		tr.setBook(book);
		tr.setUser(user);
		tr.setAmount(book.getCost());
		tr.setStatus("Borrowed");
		book.setStock(book.getStock()-1);
		trxnRepo.save(tr);
		bookRepo.save(book);
		return book;
	}

	@Override
	public Transaction returnBook(int tid) {
		// TODO Auto-generated method stub
		Transaction trxn=trxnRepo.findById(tid).orElseThrow(()-> new ApplicationException("No transaction found with Id "+tid));
		LocalDate borrowedDate=trxn.getBorrowedDate();
		LocalDate returnedDate=LocalDate.now();
		Duration duration =Duration.between(borrowedDate.atStartOfDay(), returnedDate.atStartOfDay());
		int noOfDays=(int)duration.toDays();
		Book b=trxn.getBook();
		b.setStock(b.getStock()+1);
		bookRepo.save(b);
		trxn.setReturnedDate(returnedDate);
		if(trxn.getStatus().equals("Returned"))
		throw new ApplicationException("book already returned with transaction Id "+tid);
		if(noOfDays>30) {
			int lateDays=noOfDays-30;
			Penalty p=new Penalty();
			p.setAmount(lateDays*30);
			p.setNoOfDays(lateDays);
			p.setRemarks("Paid");
			trxn.setPenaltyId(p);
			trxn.setAmount(lateDays*30+trxn.getAmount());
			penaltyRepo.save(p);
			}
		trxn.setStatus("Returned");
		trxnRepo.save(trxn);
		return trxn;
	}

}
