package com.cps.services.tech;

public interface FightChar {

	/*Observators*/
	
	public boolean isBlocking() ;
	public boolean isBlockstunne() ;
	public boolean isHitstunned();
	public boolean isTeching();
	
	// \pre isTeching() 
	public Tech tech(); 
	
	// \pre isTeching() 
	public boolean techFrame();
	
	// \pre isTeching() 
	public boolean techHasAlreadyHit(); 
	
	/*Invariants*/
	//TODO
	
	
	
	
	
	/*Operators */
	
	// \pre !isTeching() 
	public void startTech(Tech tech);
	
	
}
