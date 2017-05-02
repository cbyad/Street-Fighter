package com.cps.services.fightChar;

import com.cps.services.character.CharacterContract;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.tech.Tech;

public class FightCharContract extends CharacterContract implements FightChar{ // pas sur 

	public FightCharContract(FightChar delagate) {
		super(delagate);
	}
	//TODO

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



	

}
