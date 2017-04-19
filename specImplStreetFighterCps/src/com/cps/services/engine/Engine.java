package com.cps.services.engine;

import com.cps.services.character.Player;

/**
 * Service : Moteur de jeu
 * @author cb_mac
 *
 */
/****************************************
 *  OK
 * *************************************/
public interface Engine {

	/*Observators*/

	public int getHeight();
	public int getWidth();

	// \pre: i:int \in {1,2}
	public Character getChar(int i);

	// \pre: i:int \in {1,2}
	public Player getPlayer(int i);

	public boolean isGameOver();

	/*Invariants*/

	// \inv: (perso=getChar(i)):Character , isGameOver() == perso.isDead()  , \exist i:int \in {1,2}
	

	/*Constructors*/

	// \pre : h>0
	// \pre : s>0
	// \pre : w>s
	// \pre : p1 != p2

	// \post : getHeight() == h
	// \post : getWidth() == w
	// \post : getPlayer(1) == p1
	// \post : getPlayer(2) == p2

	// \post: (char1=getChar(1)):Character , char1.getPositionX()==(w/2 - s/2)
	// \post: (char2=getChar(2)):Character , char2.getPositionX() == (w/2 + s/2)
	// \post: (char1=getChar(1)):Character , char1.getPositionY() == 0
	// \post: (char2=getChar(2)):Character , char2.getPositionY() == 0
	// \post: (char1=getChar(1)):Character , char1.isFaceRight() 
	// \post: (char2=getChar(2)):Character , !char2.isFaceRigth() 
	public void init(int h, int w, int s ,Player p1 ,Player p2);

	/*Operators*/
	
	// \pre : !isGameOver()
	// \post: char1:Character ,  (char1=getChar(1)) == char1.step(c1)  
	// \post: char2:Character,   (char2=getChar(2)) == char2.step(c2)
	
	public void step(Commande c1 , Commande c2);
	
}