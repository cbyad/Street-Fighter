package com.cps.services.fightChar;

import com.cps.services.character.Character;
import com.cps.services.tech.Tech;

public interface FightChar /*refine*/ extends Character{

	/*Observators*/
	
	public boolean isBlocking() ;
	public boolean isBlockstunned() ;
	public boolean isHitstunned();
	public boolean isTeching();
	
	// \pre isTeching() 
	public Tech tech(); 
	
	// \pre isTeching() 
	public boolean techFrame();
	
	// \pre isTeching() 
	public boolean techHasAlreadyHit(); 
	
	/*Invariants*/
	
	// \inv isBlocking()== !isBlockstunned() 
	// \inv isBlocking()==!isTeching()
	// \inv isTeching()==!isHitstunned() 
	
	
	
	
	
	/*Operators */
	
	// \pre !isTeching() ^ !isHitstunned() ^ !isBlockStunned()
	// \post isTeching()
	public void startTech(Tech tech);
	
	
	
	// Spec supp pour step
	
	// \pre: step(c) requires !isHitStunned() ^ !isBlockStunned() ^ !isTeching()
	// \post: step(PUNCH)=starTech(punch)
	// \post: (c=BLOCK) => isBlocking()
	
	
}
