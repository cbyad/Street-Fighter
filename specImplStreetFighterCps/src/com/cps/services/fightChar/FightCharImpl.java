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
	public boolean slideLeft;
	private String name;
	
	
	public void setName(String name){
		this.name=name;
	}
	
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
		super.moveLeft();
	}

	@Override
	public void moveRight() {
		super.moveRight();
	}


	@Override
	public void step(Commande c){
		if (!dead()){
			this.techFrame=false;
			if (!this.techHasAlreadyHit()){
				for (int i=0;i<2;i++){
					if(this.engine.getPlayer(i+1).getChar()!=this){
						if (this.engine.getPlayer(i+1).getChar().isTeching()){
							if(this.engine.getPlayer(i+1).getChar().techFrame()){
								if (this.charBox().CollidesWith(this.engine.getPlayer(i+1).getChar().tech().getHitBox())){
									techHasAlreadyHit=true;
									if (isBlocking/*&&(this.facing!=this.engine.getPlayer(i+1).getChar().faceRight())*/){
										this.isBlocking=false;
										this.isBlockstunned=true;
										cycle[0]=this.engine.getPlayer(i+1).getChar().tech().bstun();
										this.techHasAlreadyHit=true;
								
									}
									else {
										this.isHitstunned=true;
										cycle[0]=this.engine.getPlayer(i+1).getChar().tech().hstun();
										this.life-=this.engine.getPlayer(i+1).getChar().tech().damage();
										if (this.engine.getPlayer(i+1).getChar().faceRight())
											this.slideLeft=false;
										else
											this.slideLeft=true;
										if (this.life<=0){
											this.dead=true;
										}
									}
								}
							}
						}
					}
				}
			}
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
			
			if ((this.crouching)&&!isTeching){
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
							if (name=="jackie"){

								if (this.faceRight()){
									t.init(10, 4, 5, 2, 4, 3, this.x+69, this.y+14, 9, 28);
								}
								else {
									t.init(10, 4, 5, 2, 4, 3, this.x-39, this.y+14,9,38);
								}
							}	
							else if (name=="elsa"){
								this.crouch();
								if (this.faceRight()){
									t.init(8, 3, 1, 3, 4, 5, this.x+34, this.y+13, 9, 27);
								}
								else{
									t.init(8, 3, 1, 3, 4, 5, this.x-28, this.y+13, 9, 27);
								}
							}
							this.startTech(t);
						}
					}
					
					else if (c==Commande.KICK){
						if (!jumping){
							Tech t=new TechImpl();
							if (name=="jackie"){

								if (this.faceRight()){
									t.init(5, 3, 3, 4, 7, 6, this.x+48, this.y+18, 17, 43);
								}
								else {
									t.init(5, 3, 3, 4, 7, 6, this.x-44, this.y+18, 17, 43);
								}
							}
							else if (name=="elsa"){
								if (this.faceRight()){
									t.init(14, 3, 1, 8, 4, 2, this.x+47, this.y+3, 23, 43);
								}
								else {
									t.init(5, 5, 3, 2, 7, 6, this.x-44, this.y+3, 23, 43);
								}
							}
							this.startTech(t);
						}
					}
					
					else if (c==Commande.SPECIAL){
						if (!jumping){
							Tech t=new TechImpl();
							if (name=="jackie"){

								if (this.faceRight()){
									t.init(15, 8, 2, 1, 5, 10, this.x+52, this.y+6, 36, 18);
								}
								else{
									t.init(15, 8, 2, 1, 5, 10, this.x-19, this.y+6, 36, 18);
								}
							}
							else if (name=="elsa"){
								if (this.faceRight()){
									t.init(40, 10, 1, 1, 6, 8, this.x+44, this.y+25, 53, 53);
								}
								else {
									t.init(40, 10, 1, 1, 6, 6, this.x-54, this.y+25, 53, 53);
								}
							}
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
							this.techFrame=true;
							cycle[1]--;
						}
						
						else {
							if (cycle[2]>0){
								cycle[2]--;
							}
							else {
								if (crouching){
									this.crouching=false;
									this.y= yStand;
									this.hitbox.MoveTo(this.x, this.y);
									this.hitbox.SetHeight(hStand);
								}
								isTeching=false;
							}
						}
					}
				}
				
				if (isHitstunned){
					isTeching=false;
					isBlocking=false;
					isBlockstunned=false;
					if (crouching){
						this.y= yStand;
						this.hitbox.MoveTo(this.x, this.y);
						this.hitbox.SetHeight(hStand);
					}
					crouching=false;
					if (cycle[0]>0){
						cycle[0]--;
						if (this.slideLeft){
							this.x-=this.speed/3;
							if (this.x<0)
								this.x=0;
						}
						else {
							this.x+=this.speed/3;
							if (this.x>this.engine.getWidth()-this.charBox().Length())
								this.x=this.engine.getWidth()-this.charBox().Length();
						}
					}
					else{
						isHitstunned=false;
						techHasAlreadyHit=false;
					}
					this.charBox().MoveTo(this.x, this.y);
				}
				
				if (isBlockstunned){
					isTeching=false;
					isBlocking=false;
					isHitstunned=false;
					crouching=false;
					if (cycle[0]>0){
						cycle[0]--;
					}
					else{
						isBlockstunned=false;
						techHasAlreadyHit=false;
						isBlocking=true;
					}
				}
				
			}
			
		}
		this.charBox().MoveTo(this.x, this.y);
	}


	@Override
	public void init(int l, int s, boolean f, Engine e, Hitbox h, Tech tech) {
		super.init(l, s, f, e, h);
		this.tech=tech;
		isBlocking=false ;
		isBlockstunned=false ;
		isHitstunned=false;
		isTeching=false;
		techFrame=false;
		techHasAlreadyHit=false; 
		cycle=new int[3];
	}
}
