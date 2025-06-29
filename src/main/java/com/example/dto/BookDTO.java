package com.example.dto;

import java.time.LocalDate;

import com.example.entity.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class BookDTO {
	@Id
	private int bookId;
	private String bookName;
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate publishedDate;
	private float cost;
	private int stock;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private int authId;

}
