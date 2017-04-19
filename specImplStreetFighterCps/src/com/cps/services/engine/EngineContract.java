package com.cps.services.engine;

import com.cps.exception.InvariantError;
import com.cps.exception.PostConditionError;
import com.cps.exception.PreConditionError;
import com.cps.services.character.CharacterImpl;
import com.cps.services.character.Player;

public class EngineContract extends EngineDecorator{

	public EngineContract(Engine service) {
		super(service);
	}

	public void checkInvariant(){
		//pour le moment je suppose qie player et charactere designe la meme chose
		
		//\inv: (perso=getChar(i)):Character , isGameOver() == perso.isDead()  , \exist i:int \in {1,2}
		Character perso1 = getChar(1);
		Character perso2 =getChar(2);
		
		if(!isGameOver() == perso1.isDead() ) 
			throw new InvariantError("isGameOver() == Character::isDead(getPlayer(1))");

		if(!isGameOver() == perso2.isDead()) 
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
		if(!(char1.getPositionX()==(w/2 - s/2))) throw new PostConditionError("char1.getPositionX()==(w/2 - s/2)");
		
		
		// \post: (char2=getChar(2)):Character , char2.getPositionX() == (w/2 + s/2)
		Character char2 = getChar(2);
		if(!(char2.getPostionX()==(w/2 + s/2))) throw new PostConditionError("char2.getPositionX()==(w/2 + s/2)");
		
		// \post: (char1=getChar(1)):Character , char1.getPositionY() == 0
		Character char1 = getChar(1);
		if(!(char1.getPositionY()==0)) throw new PostConditionError("char1.getPositionY()==0");
		
		// \post: (char2=getChar(2)):Character , char2.getPositionY() == 0
		Character char2 = getChar(2);
		if(!(char2.getPositionY()==0)) throw new PostConditionError("char2.getPositionY()==0");
		
		// \post: (char1=getChar(1)):Character , char1.isFaceRight() 
		Character char1 = getChar(1);
		if(!(char1.isFaceRight())) throw new PostConditionError("char1.isFaceRight()");
		
		// \post: (char2=getChar(2)):Character , !char1.isFaceRigth() 
		Character char1 = getChar(1);
		if(!(!char2.isFaceRigth())) throw new PostConditionError("!char2.isFaceRigth()");

	}

	@Override
	public void step(Commande c1, Commande c2) {
		// TODO Auto-generated method stub

	}

}
