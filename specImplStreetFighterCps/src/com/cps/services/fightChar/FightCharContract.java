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

	public int positionX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int positionY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Engine engine() {
		// TODO Auto-generated method stub
		return null;
	}

	public Hitbox charBox() {
		// TODO Auto-generated method stub
		return null;
	}

	public int life() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int speed() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean faceRight() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean dead() {
		// TODO Auto-generated method stub
		return false;
	}

	public void init(int l, int s, boolean f, Engine e, Hitbox h) {
		// TODO Auto-generated method stub
		
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

	public void switchSide() {
		// TODO Auto-generated method stub
		
	}

	public void step(Commande c) {
		// TODO Auto-generated method stub
		
	}

	public boolean isBlocking() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isBlockstuned() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isHitstunned() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isTeching() {
		// TODO Auto-generated method stub
		return false;
	}

	public Tech tech() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean techFrame() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean techHasAlreadyHit() {
		// TODO Auto-generated method stub
		return false;
	}

	public void startTech(Tech tech) {
		// TODO Auto-generated method stub
		
	}

	public void block(Tech tech) {
		// TODO Auto-generated method stub
		
	}

	

}
