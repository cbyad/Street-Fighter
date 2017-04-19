package com.cps.exception;

@SuppressWarnings("serial")
public class InvariantError extends ContractError{


	public InvariantError(String msg) {
		super("Invariant failed: " + msg);
		
	}
	

}
