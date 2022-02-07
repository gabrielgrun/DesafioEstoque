package br.com.zitrus.zitrus.exception;

public class NoBalanceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Produto sem quantidade disponível em estoque!";

	public NoBalanceException() {
		super(MESSAGE);
	}
}
