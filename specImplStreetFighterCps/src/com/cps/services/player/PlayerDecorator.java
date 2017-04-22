package com.cps.services.player;

import com.cps.services.character.Character;

public class PlayerDecorator implements Player {

	private Player delegate;
	
	public PlayerDecorator(Player d){
		this.delegate=d;
	}
	
	public Character getChar() {
		return delegate.getChar();
	}

	public void init() {
		delegate.init();
	}

	public void setChar(Character c) {
		delegate.setChar(c);
	}

}
