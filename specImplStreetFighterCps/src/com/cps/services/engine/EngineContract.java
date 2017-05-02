package com.cps.services.engine;

import com.cps.exception.InvariantError;
import com.cps.exception.PostConditionError;
import com.cps.exception.PreConditionError;
import com.cps.services.character.Character;
import com.cps.services.player.Player;

public class EngineContract extends EngineDecorator{

	public EngineContract(Engine service) {
		super(service);
	}

	public void checkInvariant(){
		//\inv: (perso=getChar(i)):Character , isGameOver() == perso.isDead()  , \exist i:int \in {1,2}
		Character perso1 = getChar(1);
		Character perso2 =getChar(2);
		
		if(!isGameOver() == perso1.dead() ) 
			throw new InvariantError("isGameOver() == Character::isDead(getPlayer(1))");

		if(!isGameOver() == perso2.dead()) 
			throw new InvariantError("isGameOver() == Character::isDead(getPlayer(2))");
	}


	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {

		/*Precondition*/
		
		// \pre : h>0
		if(!(h>0)) throw new PreConditionError("h>0");

		// \pre : s>0
		if(!(s>0)) throw new PreConditionError("s>0");

		// \pre : w>s
		if(!(w>s)) throw new PreConditionError("w>s");

		// \pre : p1 != p2
		if(!(p1!=p2)) throw new PreConditionError("p1!=p2");


		//Traitement
		super.init(h, w, s, p1, p2);

		//post-init invariant
		checkInvariant();

		/*Postcondition*/

		// \post : getHeight() == h
		if(!(getHeight()==h)) throw new PostConditionError("getHeight==h");

		// \post : getWidth() == w
		if(!(getWidth()==w)) throw new PostConditionError("getWidth==w");

		// \post : getPlayer(1) == p1
		if(!(getPlayer(1)==p1)) throw new PostConditionError("getPlayer(1)==p1");

		// \post : getPlayer(2) == p2
		if(!(getPlayer(2)==p2)) throw new PostConditionError("getPlayer(2)==p2");


		// \post: (char1=getChar(1)):Character , char1.getPositionX()==(w/2 - s/2)
		Character char1 = getChar(1);
		if(!(char1.positionX()==(w/2 - s/2))) throw new PostConditionError("char1.getPositionX()==(w/2 - s/2)");
		
		
		// \post: (char2=getChar(2)):Character , char2.getPositionX() == (w/2 + s/2)
		Character char2 = getChar(2);
		if(!(char2.positionX()==(w/2 + s/2))) throw new PostConditionError("char2.getPositionX()==(w/2 + s/2)");
		
		// \post: (char1=getChar(1)):Character , char1.getPositionY() == 0
		Character char11 = getChar(1);
		if(!(char11.positionY()==0)) throw new PostConditionError("char1.getPositionY()==0");
		
		// \post: (char2=getChar(2)):Character , char2.getPositionY() == 0
		Character char21 = getChar(2);
		if(!(char21.positionY()==0)) throw new PostConditionError("char2.getPositionY()==0");
		
		// \post: (char1=getChar(1)):Character , char1.isFaceRight() 
		Character char111 = getChar(1);
		if(!(char111.faceRight())) throw new PostConditionError("char1.isFaceRight()");
		
		// \post: (char2=getChar(2)):Character , !char1.isFaceRigth() 
		Character char1111 = getChar(1);
		if(!(!char1111.faceRight())) throw new PostConditionError("!char2.isFaceRigth()");

	}

	@Override
	public void step(Commande c1, Commande c2) {
		
		// \pre !isGameOver()
		if(isGameOver())  throw new PreConditionError("!isGameOver() ");
		checkInvariant();
		
		//traitement 
		super.step(c1, c2);
		checkInvariant();
		
		// \post: char1:Character ,  (char1=getChar(1)) == char1.step(c1)
		// \post: char2:Character,   (char2=getChar(2)) == char2.step(c2)
	}

}
