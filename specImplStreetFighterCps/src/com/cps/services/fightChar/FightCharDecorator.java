package com.cps.services.fightChar;

import com.cps.services.character.CharacterDecorator;

public abstract class FightCharDecorator extends CharacterDecorator implements FightChar{
	
	
	public FightCharDecorator(FightChar delegate) {
		super(delegate);
	}
	

}
