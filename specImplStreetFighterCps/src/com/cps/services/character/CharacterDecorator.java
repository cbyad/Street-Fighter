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
	@Override
	public int positionX(){
		return delegate.positionX();
	}
	
	@Override
	public int positionY(){
		return delegate.positionY();
	}
	
	@Override
	public Engine engine(){
		return delegate.engine();
	}
	
	@Override
	public Hitbox charBox(){
		return delegate.charBox();
	}
	
	@Override
	public int life(){
		return delegate.life();
	}
	
	@Override
	public int speed(){
		return delegate.speed();
	}
	
	@Override
	public boolean faceRight(){
		return delegate.faceRight();
	}
	
	@Override
	public boolean dead(){
		return delegate.dead();
	}

	@Override
	public void init (int l, int s, boolean f, Engine e, Hitbox h){
		delegate.init(l, s, f, e, h);
	}

	@Override
	public void moveLeft(){
		delegate.moveLeft();
	}
	
	@Override
	public void moveRight(){
		delegate.moveRight();
	}
	
	@Override
	public void switchSide(){
		delegate.switchSide();
	}
	
	@Override
	public void step (Commande c){
		delegate.step(c);
	}


}
