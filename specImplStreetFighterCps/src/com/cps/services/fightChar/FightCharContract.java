package com.cps.services.fightChar;

import com.cps.exception.InvariantError;
import com.cps.exception.PostConditionError;
import com.cps.exception.PreConditionError;
import com.cps.services.character.CharacterContract;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.tech.Tech;

public class FightCharContract extends CharacterContract implements FightChar{ // pas sur 

	public FightCharContract(FightChar delagate) {
		super(delagate);
	}

	public FightChar getDelegate() {
		return (FightChar)this.getDelegate();
	}

	@Override
	public void checkInvariant(){
		super.checkInvariant();

		if( !(isBlocking()==!isBlockstunned())) 
			throw new InvariantError("isBlocking()== !isBlockstunned() ");

		if(!(isBlocking()==!isTeching())) 
			throw new InvariantError("isBlocking()== !isTeching() ");

		if(!(isTeching()==!isHitstunned()))
			throw new InvariantError("isTeching()==!isHitstunned()");
	}



	@Override
	public int positionX() {

		return getDelegate().positionX();
	}

	@Override
	public int positionY() {
		return getDelegate().positionY();
	}

	@Override
	public Engine engine() {
		return getDelegate().engine();
	}

	@Override
	public Hitbox charBox() {
		return getDelegate().charBox();
	}

	@Override
	public int life() {
		return getDelegate().life();
	}

	@Override
	public int speed() {
		return getDelegate().speed();
	}

	@Override
	public boolean faceRight() {
		return getDelegate().faceRight();
	}

	@Override
	public boolean dead() {
		return getDelegate().dead();
	}

	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h) {
		getDelegate().init(l, s, f, e, h);
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
		super.step(c);

		// \pre: step(c) requires !isHitStunned() ^ !isBlockStunned() ^ !isTeching()
		if(!(!isHitstunned() && !isBlockstunned() && !isTeching()))
			throw new PreConditionError("!isHitStunned() ^ !isBlockStunned() ^ !isTeching()");

		checkInvariant();

		// \post: step(PUNCH)=starTech(punch)

		// \post: (c=BLOCK) => isBlocking()	
		if( c==Commande.BLOCK)
			if(!isBlocking()) throw new PostConditionError("(c=BLOCK) => isBlocking()");
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
		// \pre !isTeching() ^ !isHitstunned() ^ !isBlockStunned()
		if(!(!isHitstunned() && !isBlockstunned() && !isTeching()))
			throw new PreConditionError
			("!isHitStunned() ^ !isBlockStunned() ^ !isTeching()");

		//Traitement 
		startTech(tech);

		//capture
		checkInvariant();

		// \post isTeching()
		if(! isTeching()) 
			throw new PostConditionError("isTeching()");
		
		checkInvariant();

	}





}
