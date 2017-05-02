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
	
	
	@Override
	public boolean isBlocking() {
		
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
	public boolean isBlockstunned() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void step(Commande c){
			//TODO
		
	}



	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h, Tech tech) {
		// TODO Auto-generated method stub
		
	}
}
