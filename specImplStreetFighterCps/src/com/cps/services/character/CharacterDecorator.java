package com.cps.services.character;

import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;



public  abstract class CharacterDecorator implements Character{ 

	protected Character delegate ;
	
	public Character getDelegate (){
		return delegate;
	}
	
	public CharacterDecorator(Character d) {
		this.delegate=d ;
	}
	public int positionX(){
		return delegate.positionX();
	}
	
	public int positionY(){
		return delegate.positionY();
	}
	
	public Engine engine(){
		return delegate.engine();
	}
	
	public Hitbox charBox(){
		return delegate.charBox();
	}
	
	public int life(){
		return delegate.life();
	}
	
	public int speed(){
		return delegate.speed();
	}
	
	public boolean faceRight(){
		return delegate.faceRight();
	}
	
	public boolean dead(){
		return delegate.dead();
	}

	public void init (int l, int s, boolean f, Engine e, Hitbox h){
		delegate.init(l, s, f, e, h);
	}

	public void moveLeft(){
		delegate.moveLeft();
	}
	
	public void moveRight(){
		delegate.moveRight();
	}
	
	public void switchSide(){
		delegate.switchSide();
	}
	
	public void step (Commande c){
		delegate.step(c);
	}
}
