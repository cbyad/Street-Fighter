package com.cps.services.player;

import com.cps.services.fightChar.FightChar;

public abstract class PlayerDecorator implements Player {

	private Player delegate;
	
	public PlayerDecorator(Player d){
		this.delegate=d;
	}
	
	@Override
	public FightChar getChar() {
		return delegate.getChar();
	}

	@Override
	public void init() {
		delegate.init();
	}

	@Override
	public void setChar(FightChar c) {
		delegate.setChar(c);
	}

}
