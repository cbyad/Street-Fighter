package com.cps.services.fightChar;

import com.cps.services.character.Character;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
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
	
	// \inv isBlocking()==!isTeching()
	// \inv isTeching()==!isHitstunned() 
	
	/*Constructor*/
	// on recupere les meme specification de character mais on ajoute:
	// \post tech()==tech
    // !isTeching()
    // !techFrame()
    // !techHasAlreadyHit()
	public void init (int l, int s, boolean f, Engine e, Hitbox h,Tech tech);
	
	
	/*Operators */
	
	// \pre !isTeching() ^ !isHitstunned() ^ !isBlockStunned()
	// \post isTeching()
	public void startTech(Tech tech);
	
	
	// Specification supplementaire pour step()
	
	// \pre: step(c) requires !isHitStunned() ^ !isBlockStunned() ^ !isTeching()
	// \post: step(PUNCH)=starTech(punch)
	// \post: (c=BLOCK) => isBlocking()=true
	
	
}
