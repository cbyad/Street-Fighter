package com.cps.services.character;

import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;

public class CharacterImpl implements Character{
	protected int grav;
	protected int x;
	protected int y;
	protected Engine engine;
	protected Hitbox hitbox;
	protected int life;
	protected int speed;
	protected boolean facing;
	protected boolean dead;
	protected boolean jumping;
	protected boolean crouching;
	protected int vspeed;
	protected int hStand;
	protected int hCrouch;
	protected int yStand;
	public String name;
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public void setName(String name){
		this.name=name;
	}
	
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
	public int getVSpeed(){
		return this.vspeed;
	}
	
	@Override
	public int getYStand(){
		return yStand;
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
		this.jumping=false;
		this.crouching=false;
		this.vspeed=0;
		this.hStand=this.hitbox.Height();
		this.hCrouch=(2*hStand)/3;
		this.grav=5;
		this.yStand=this.y;
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

	@Override
	public void switchSide() {
		this.facing=!this.faceRight();
	}

	@Override
	public void crouch(){
	
		this.y= this.y-hCrouch+hStand;
		this.hitbox.MoveTo(this.x, this.y);
		this.hitbox.SetHeight(hCrouch);
	
		
		this.crouching=true;
	}
	
	@Override
	public void jump(){
		this.jumping=true;
		this.vspeed=this.speed*5;
		this.y-=this.vspeed;
	}
	
	@Override
	public void step(Commande c) {
		
		for (int i=0;i<2;i++){
			if(this.engine.getPlayer(i+1).getChar()!=this){
				if (this.charBox().CollidesWith(this.engine.getPlayer(i+1).getChar().charBox())){
					int xpre=this.x;
					if (this.x<this.engine.getPlayer(i+1).getChar().positionX()){
						this.x-=this.speed;
						if (this.x<0)
							this.x=0;
					}
					else{
						this.x+=this.speed;
						if (this.x>this.engine.getWidth()-this.charBox().Length())
							this.x=this.engine.getWidth()-this.charBox().Length();
					}
				}
			}
		}
		
		if (this.crouching){
			this.crouching=false;
			this.y= yStand;
			this.hitbox.MoveTo(this.x, this.y);
			this.hitbox.SetHeight(hStand);
		}
		
		if (this.jumping){
			if (this.positionY()<yStand){
				this.vspeed-=this.grav;
				this.y-=this.vspeed;
	
				for (int i=0;i<2;i++){
					if(this.engine.getPlayer(i+1).getChar()!=this){
						if (this.charBox().CollidesWith(this.engine.getPlayer(i+1).getChar().charBox())){
							if (this.x<this.engine.getPlayer(i+1).getChar().positionX()){
								this.x-=this.speed;
							
							}
							else{
								this.x+=this.speed;
							}
							
						}
					}
				}

			
				this.charBox().MoveTo(this.positionX(), this.positionY());
				
				
			}
			if (this.positionY()>yStand){
				this.y=yStand;

			}
			
			if (this.positionY()==yStand){
				this.vspeed=0;
				this.jumping=false;
			}
			
		}
		

				
			
		if (c==Commande.NEUTRAL){
				
		}
		else{
			for (int i=0;i<2;i++){
				if(this.engine.getPlayer(i+1).getChar()!=this){
					if(this.faceRight()){
						if (this.positionX()>this.engine.getPlayer(i+1).getChar().positionX())
							this.switchSide();
					}
					else {
						if (this.positionX()<this.engine.getPlayer(i+1).getChar().positionX())
							this.switchSide();
					}
				}
			}
			if (c==Commande.LEFT){
	
				this.moveLeft();
			}
	
			else if (c==Commande.RIGHT){
				
				this.moveRight();
			}
				
			else if (c==Commande.CROUCH){
				if (!jumping)
					this.crouch();
			}
				
			else if (c==Commande.JUMP){
				if (!jumping)	
					this.jump();
			}
			
		}
	}

	@Override
	public int hStand() {
		return hStand;
	}

	@Override
	public int hCrouch() {
		return hCrouch;
	}


}