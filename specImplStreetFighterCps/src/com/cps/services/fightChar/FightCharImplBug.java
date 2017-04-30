package com.cps.services.fightChar;

import com.cps.services.character.CharacterImpl;
import com.cps.services.tech.Tech;

public class FightCharImplBug extends CharacterImpl implements FightChar{

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
