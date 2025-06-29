package com.example.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookDTO {
	@NotNull(message="Enter bookId")
	private int bookId;
	@NotNull(message="Enter bookId")
	private int userId;
	
}
