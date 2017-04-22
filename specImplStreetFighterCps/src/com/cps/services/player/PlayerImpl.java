package com.cps.services.player;

import com.cps.services.character.Character;

public class PlayerImpl implements Player {

	private Character charac;
	
	public PlayerImpl(){
	}

	public Character getChar() {
		return this.charac;
	}

	public void init() {
		this.charac=null;		
	}

	public void setChar(Character c) {
		this.charac=c;
	}
}
