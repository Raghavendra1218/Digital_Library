package com.example.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

	public ApplicationException(String msg) {
		super(msg);
	}

}
