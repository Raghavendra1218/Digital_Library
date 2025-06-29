package com.example.demo;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.entity.Book;
import com.example.repo.BookRepo;
import com.example.service.UserServiceImpl;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private BookRepo bookRepo;
	@InjectMocks
	private UserServiceImpl userServImpl;
	@Test
	public void testAvbltyBookTest() {
		Book mockBook=new Book();
		mockBook.setBookId(101);
		mockBook.setStock(30);
		when(bookRepo.findById(101)).thenReturn(Optional.of(mockBook));
		boolean status=userServImpl.checkAvailability(101);
		assertEquals(true,status);
	}
}
