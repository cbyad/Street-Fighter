package com.cps.services.player;

import com.cps.exception.PostConditionError;
import com.cps.services.character.Character;
import com.cps.services.fightChar.FightChar;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(Player service){
		super(service);
	}
	
	public void checkInvariant(){
		
	}
	
	public void init(){
		
	}
	
	public void setChar(FightChar c){
		
		super.setChar(c);
		checkInvariant();
		
		// \post: getChar()=c
		if(super.getChar()!=c)
			throw new PostConditionError("getChar()=c");
		
	}
}
