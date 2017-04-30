package com.cps.services.fightChar;

import com.cps.services.character.CharacterDecorator;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.tech.Tech;

public abstract class FightCharDecorator extends CharacterDecorator implements FightChar{
	
	
	public FightCharDecorator(FightChar delegate) {
		super(delegate);
	}
	
	@Override
	public FightChar getDelegate(){
		return (FightChar) super.getDelegate();
	}
	
	@Override
	public boolean isBlocking() {
		return getDelegate().isBlocking();
	}

	@Override
	public boolean isHitstunned() {
		return getDelegate().isHitstunned();
	}

	@Override
	public boolean isTeching() {
		return getDelegate().isTeching();
	}

	@Override
	public Tech tech() {
		return getDelegate().tech();
	}

	@Override
	public boolean techFrame() {
		return getDelegate().techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() {
		return getDelegate().techHasAlreadyHit();
	}

	@Override
	public void startTech(Tech tech) {
		getDelegate().startTech(tech);
	}


	@Override
	public int positionX() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int positionY() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Engine engine() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Hitbox charBox() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int life() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int speed() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean faceRight() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean dead() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void switchSide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void step(Commande c) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isBlockstuned() {
		// TODO Auto-generated method stub
		return false;
	}

}
