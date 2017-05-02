package com.cps.services.fightChar;

import com.cps.services.character.CharacterDecorator;
import com.cps.services.tech.Tech;

public abstract class FightCharDecorator extends CharacterDecorator implements FightChar{
	
	
	public FightCharDecorator(FightChar delegate) {
		super(delegate);
	}

	@Override
	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlockstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHitstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tech tech() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean techFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean techHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startTech(Tech tech) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void block(Tech tech) {
		// TODO Auto-generated method stub
		
	}
	

}
