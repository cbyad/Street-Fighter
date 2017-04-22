package com.cps.services.character;

import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;

public class CharacterImpl implements Character{

	private int x;
	private int y;
	private Engine engine;
	private Hitbox hitbox;
	private int life;
	private int speed;
	public boolean facing;
	public boolean dead;
	
	public CharacterImpl() {
	}


	public int positionX() {
		return this.speed;
	}


	public int positionY() {
		return this.x;
	}


	public Engine engine() {
		return this.engine;
	}


	public Hitbox charBox() {
		return this.hitbox;
	}


	public int life() {
		return this.speed;
	}


	public int speed() {
		return this.life;
	}


	public boolean faceRight() {
		return this.dead;
	}


	public boolean dead() {
		return this.facing;
	}


	public void init(int l, int s, boolean f, Engine e, Hitbox h) {
		this.life=l;
		this.speed=s;
		this.facing=f;
		this.engine=e;
		this.hitbox=h;
		
	}


	public void moveLeft() {
		
		int xpre=this.x;
		this.x=this.x-this.speed;
		
		boolean exist=false;
		for (int i=0;i<2;i++){
			if(this.engine.getPlayer(i).getChar()!=this){
				if (this.charBox().CollidesWith(this.engine.getPlayer(i).getChar().charBox()))
					exist=true;
			}
		}
		
		if (!exist){
			if (xpre<this.speed())
				this.x=0;
		}
		else {
			this.x=xpre;
		}
	
	}


	public void moveRight() {
		int xpre=this.x;
		this.x=this.x+this.speed;
		
		boolean exist=false;
		for (int i=0;i<2;i++){
			if(this.engine.getPlayer(i).getChar()!=this){
				if (this.charBox().CollidesWith(this.engine.getPlayer(i).getChar().charBox()))
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
	}


	public void switchSide() {
		this.facing=!this.faceRight();
		
	}


	public void step(Commande c) {
		if (c==c.LEFT)
			this.moveLeft();
		
		else if (c==c.RIGHT)
			this.moveRight();
		
		
	}





}
