package com.cps.services.fightChar;

import com.cps.services.character.CharacterImpl;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.tech.Tech;
import com.cps.services.tech.TechImpl;

public class FightCharImpl extends CharacterImpl implements FightChar{

	private boolean isBlocking ;
	public boolean isBlockstunned ;
	public boolean isHitstunned;
	public boolean isTeching;
	public Tech tech; 
	public boolean techFrame;
	public boolean techHasAlreadyHit; 
	public int cycle[];

	public FightCharImpl() {
	}

	@Override
	public boolean isBlocking() {
		return isBlocking;
	}

	@Override
	public boolean isBlockstunned() {
		return isBlockstunned;
	}

	@Override
	public boolean isHitstunned() {
		return isHitstunned;
	}

	@Override
	public boolean isTeching() {
		return isTeching;
	}

	@Override
	public Tech tech() {
		return tech;
	}

	@Override
	public boolean techFrame() {
		return techFrame;
	}

	@Override
	public boolean techHasAlreadyHit() {
		return techHasAlreadyHit;
	}

	@Override
	public void startTech(Tech tech) {
		this.isTeching=true;
		cycle[0]=tech.sframe();
		cycle[1]=tech.hframe();
		cycle[2]=tech.rframe();
		this.tech=tech;
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
	public void step(Commande c){
		this.isBlocking=false;
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
		

		if (!this.isBlockstunned&&!this.isHitstunned&&!this.isTeching){	
				
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
				
				else if (c==Commande.BLOCK){
					if (!jumping)
						this.isBlocking=true;
				}
				
				else if (c==Commande.PUNCH){
					if (!jumping){
						Tech t=new TechImpl();
						t.init(10, 4, 5, 2, 4, 3);
						this.startTech(t);
					}
				}
				
			}
		}
		
		else {
			if (isTeching){
				if (cycle[0]>0){
					cycle[0]--;
				}
				else {
					if (cycle[1]>0){
						cycle[1]--;
					}
					
					else {
						if (cycle[2]>0){
							cycle[2]--;
						}
						else {
							isTeching=false;
						}
					}
				}
			}
		}
		this.charBox().MoveTo(this.x, this.y);

	}


	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h, Tech tech) {
		super.init(l, s, f, e, h);
		//this.tech=tech;
		isBlocking=false ;
		isBlockstunned=false ;
		isHitstunned=false;
		isTeching=false;
		techFrame=false;
		techHasAlreadyHit=false; 
		cycle=new int[3];
	}
}
