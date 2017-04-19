package com.cps.exception;

@SuppressWarnings("serial")
public class ContractError extends Error {
	//private static final long serialVersionUID = 507039714756819924L;

	public ContractError(String message) {
		super(message);
	}
}
