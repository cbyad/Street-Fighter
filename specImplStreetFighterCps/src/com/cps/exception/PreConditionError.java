package com.cps.exception;

@SuppressWarnings("serial")
public class PreConditionError extends ContractError{

	
	public PreConditionError(String msg) {
		super("PreCondition failed"+msg);
	}
	
}
