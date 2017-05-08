package com.cps.services.player;

import com.cps.services.fightChar.FightChar;

public interface Player {

	public FightChar getChar();

	public void init();
	
	// \post: getChar()=c
	public void setChar(FightChar c);
}
