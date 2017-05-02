package com.cps.services.character;

import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;

public class CharacterImpl implements Character{

	protected int x;
	protected int y;
	protected Engine engine;
	protected Hitbox hitbox;
	protected int life;
	protected int speed;
	protected boolean facing;
	protected boolean dead;
	
	public CharacterImpl() {
	}

	@Override
	public int positionX() {
		return this.x;
	}

	@Override
	public int positionY() {
		return this.y;
	}

	@Override
	public Engine engine() {
		return this.engine;
	}

	@Override
	public Hitbox charBox() {
		return this.hitbox;
	}

	@Override
	public int life() {
		return this.life;
	}

	@Override
	public int speed() {
		return this.speed;
	}

	@Override
	public boolean faceRight() {
		return this.facing;
	}

	@Override
	public boolean dead() {
		return this.dead;
	}

	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h) {
		this.life=l;
		this.speed=s;
		this.facing=f;
		this.engine=e;
		this.hitbox=h;
		this.x=this.hitbox.PositionX();
		this.y=this.hitbox.PositionY();
	}

	@Override
	public void moveLeft() {

		int xpre=this.x;
		this.x=this.x-this.speed;
		this.charBox().MoveTo(this.positionX(), this.positionY());

		boolean exist=false;
		for (int i=0;i<2;i++){
			if(this.engine.getPlayer(i+1).getChar()!=this){
				if (this.charBox().CollidesWith(this.engine.getPlayer(i+1).getChar().charBox())){
					exist=true;
					
				}
			}
		}

		if (!exist){
			if (xpre<this.speed())
				this.x=0;
		}
		else {
			this.x=xpre;
		}
		this.charBox().MoveTo(this.positionX(), this.positionY());
	}

	@Override
	public void moveRight() {
		int xpre=this.x;
		this.x=this.x+this.speed;
		this.charBox().MoveTo(this.positionX(), this.positionY());
		boolean exist=false;
		for (int i=0;i<2;i++){
			if(this.engine.getChar(i+1)!=this){
				if (this.charBox().CollidesWith(this.engine.getPlayer(i+1).getChar().charBox()))
					exist=true;
			}
		}

		if (!exist){
			if (xpre>this.engine.getWidth()-this.charBox().Length()-this.speed())
				this.x=this.engine.getWidth()-this.charBox().Length();
		}
		else {
			this.x=xpre;
		}
		this.charBox().MoveTo(this.positionX(), this.positionY());
	}

	public void switchSide() {
		this.facing=!this.faceRight();
	}
	
	@Override
	public void step(Commande c) {
		if (c==Commande.LEFT)
			this.moveLeft();

		else if (c==Commande.RIGHT)
			this.moveRight();
	}


}
