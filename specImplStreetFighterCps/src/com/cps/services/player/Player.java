package com.cps.services.player;

import com.cps.services.character.Character;

public interface Player {

	public Character getChar();

	public void init();
	
	// \post: getChar()=c
	public void setChar(Character c);
}
