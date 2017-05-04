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
	public void moveLeft() {
		getDelegate().moveLeft();
	}

	@Override
	public void moveRight() {
		getDelegate().moveRight();

	}

	@Override
	public void switchSide() {
		getDelegate().switchSide();
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
		return getDelegate().isBlocking();
	}

	@Override
	public boolean isBlockstunned() {
		return getDelegate().isBlockstunned();
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
		// \pre isTeching() 
		if(!isTeching()) 
			throw new PreConditionError("isTeching() ");
		return getDelegate().tech();
	}


	@Override
	public boolean techFrame() {
		// \pre isTeching() 
				if(!isTeching()) 
					throw new PreConditionError("isTeching() ");
				return getDelegate().techFrame();
	}

	@Override
	public boolean techHasAlreadyHit() {
		// \pre isTeching() 
				if(!isTeching()) 
					throw new PreConditionError("isTeching() ");
				return getDelegate().techHasAlreadyHit();
	}

	@Override
	public void startTech(Tech tech) {
		// \pre !isTeching() ^ !isHitstunned() ^ !isBlockStunned()
		if(!(!isHitstunned() && !isBlockstunned() && !isTeching()))
			throw new PreConditionError
			("!isHitStunned() ^ !isBlockStunned() ^ !isTeching()");

		//Traitement 
		getDelegate().startTech(tech);

		checkInvariant();

		// \post isTeching()
		if(! isTeching()) 
			throw new PostConditionError("isTeching()");
	}

	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h, Tech tech) {
		super.init(l, s, f, e, h);

		//traitement 
		getDelegate().init(l, s, f, e, h, tech);
		
		checkInvariant();
		
		if(!(tech()==tech)) throw new PostConditionError("tech()==tech");
		
	}

}
