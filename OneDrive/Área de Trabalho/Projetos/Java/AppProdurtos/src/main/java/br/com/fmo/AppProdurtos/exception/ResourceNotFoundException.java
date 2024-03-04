package br.com.fmo.AppProdurtos.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message){
		super(message);
	}
	
}
