package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Data
@RequiredArgsConstructor
public class Book {
	@Id
	private int bookId;

	@NotNull(message="please enter book name")
	@NotBlank(message="book name cannot be blank")
	private String bookName;
	@JsonFormat(pattern="dd-MM-yyyy")
	@PastOrPresent(message="Date cannot be in future")
	private LocalDate publishedDate;
	@PositiveOrZero(message="cost cannot be negative")
	private float cost;
	@PositiveOrZero(message="stock cannot be negative")
	private int stock;
	@ManyToOne @JoinColumn(name="authourId")
	private Authour authourId;
	@NotNull(message="please enter genre")
	@Enumerated(EnumType.STRING)
	private Genre genre;
	

}
