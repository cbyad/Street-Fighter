package com.cps.services.player;

import com.cps.services.character.Character;

public abstract class PlayerDecorator implements Player {

	private Player delegate;
	
	public PlayerDecorator(Player d){
		this.delegate=d;
	}
	
	@Override
	public Character getChar() {
		return delegate.getChar();
	}

	@Override
	public void init() {
		delegate.init();
	}

	@Override
	public void setChar(Character c) {
		delegate.setChar(c);
	}

}
