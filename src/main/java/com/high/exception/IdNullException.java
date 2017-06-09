package com.high.exception;

public class IdNullException extends Exception {
	private static final long serialVersionUID = -5380150544581739045L;

	public IdNullException(String message) {
		System.out.println(message);
		printStackTrace();
	}
}
