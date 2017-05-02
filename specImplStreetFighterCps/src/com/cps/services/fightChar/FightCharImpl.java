package com.cps.services.fightChar;

import com.cps.services.character.CharacterImpl;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.tech.Tech;

public class FightCharImpl extends CharacterImpl implements FightChar{

	private boolean isBlocking ;
	public boolean isBlockstunned ;
	public boolean isHitstunned;
	public boolean isTeching;
	public Tech tech; 
	public boolean techFrame;
	public boolean techHasAlreadyHit; 

	public FightCharImpl() {
	}

	@Override
	public boolean isBlocking() {
		return isBlocking;
	}

	@Override
	public boolean isBlockstunned() {
		return false;
	}

	@Override
	public boolean isHitstunned() {
		return isHitstunned;
	}

	@Override
	public boolean isTeching() {
		return isTeching;
	}

	@Override
	public Tech tech() {
		return tech;
	}

	@Override
	public boolean techFrame() {
		return techFrame;
	}

	@Override
	public boolean techHasAlreadyHit() {
		return techHasAlreadyHit;
	}

	@Override
	public void startTech(Tech tech) {
		// TODO Auto-generated method stub

	}





	@Override
	public void step(Commande c){
		//TODO

	}


	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h, Tech tech) {
		super.init(l, s, f, e, h);
		this.tech=tech;
		isBlocking=false ;
		isBlockstunned=false ;
		isHitstunned=false;
		isTeching=false;
		techFrame=false;
		techHasAlreadyHit=false; 
	}
}
