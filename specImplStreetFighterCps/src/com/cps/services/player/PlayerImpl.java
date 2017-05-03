package com.cps.services.player;

import com.cps.services.character.Character;
import com.cps.services.fightChar.FightChar;

public class PlayerImpl implements Player {

	private FightChar charac;

	public PlayerImpl(){
	}

	@Override
	public FightChar getChar() {
		return this.charac;
	}

	@Override
	public void init() {
		this.charac=null;		
	}

	@Override
	public void setChar(FightChar c) {
		this.charac=c;
	}
}
