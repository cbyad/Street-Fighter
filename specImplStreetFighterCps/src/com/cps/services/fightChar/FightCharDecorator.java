package com.cps.services.fightChar;

import com.cps.services.character.CharacterDecorator;
import com.cps.services.tech.Tech;

public abstract class FightCharDecorator extends CharacterDecorator implements FightChar{
	
	
	public FightCharDecorator(FightChar delegate) {
		super(delegate);
	}

	
	public FightChar getDelegate(){
		return (FightChar)this.getDelegate();
	}
	
	
	@Override
	public boolean isBlocking() {
		return this.getDelegate().isBlocking();
	}

	@Override
	public boolean isBlockstunned() {
		return this.getDelegate().isBlockstunned();
	}

	@Override
	public boolean isHitstunned() {
		return this.getDelegate().isHitstunned();
	}

	@Override
	public boolean isTeching() {
		return this.getDelegate().isTeching();
	}

	@Override
	public Tech tech() {
		return this.getDelegate().tech();
	}

	@Override
	public boolean techFrame() {
		return this.getDelegate().techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() {
		return this.getDelegate().techHasAlreadyHit();
	}

	@Override
	public void startTech(Tech tech) {
		this.getDelegate().startTech(tech);
	}

}
