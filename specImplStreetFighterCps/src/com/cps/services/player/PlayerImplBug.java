package com.cps.services.player;

import com.cps.services.character.Character;

public class PlayerImplBug implements Player {

	private Character charac;
	
	public PlayerImplBug(){
	}
	
	@Override
	public Character getChar() {
		return this.charac;
	}

	@Override
	public void init() {
		this.charac=null;		
	}

	@Override
	public void setChar(Character c) {
		this.charac=null;
	}
}
