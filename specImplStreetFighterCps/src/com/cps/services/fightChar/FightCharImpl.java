package com.cps.services.fightChar;

import com.cps.services.character.CharacterImpl;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.tech.Tech;

public class FightCharImpl extends CharacterImpl implements FightChar{

	private boolean isBlocking ;
	public boolean isBlockstunned ;
	public boolean isHitstunned;
	public boolean isTeching;
	public Tech tech; 
	public boolean techFrame;
	public boolean techHasAlreadyHit; 

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
		if(tech!=null && !isTeching){
			isTeching = true ;
			int damage = tech.damage();
			int hst = tech.hstun();
			int bst = tech.bstun();
			int sf = tech.sframe(); 
			int hf = tech.hframe(); 
			int rf = tech.rframe(); 
			int timePause = sf+ hf+ rf; 

			while(timePause!=0){
				super.step(Commande.NEUTRAL);

				//start-up frame
				while(sf!=0){
					techFrame=true ;
					sf--;
					timePause--;
				}

				//hit frame
				step(Commande.PUNCH);
				techHasAlreadyHit=true;
				while (hf!=0){
					hf-- ;
					timePause-- ;
				}
				
				//recovery frame 
				while(rf!=0){
					rf--;
					timePause-- ;
				}
				isTeching=false;//attaque fini
			}
		}
	}


	@Override
	public void step(Commande c){
		if(c!=Commande.PUNCH)
			super.step(c);
		else {

			for (int i=0;i<2;i++){
				if(this.engine.getPlayer(i+1).getChar()!=this){
					if (this.tech.getHitBox().CollidesWith(this.engine.getPlayer(i+1).getChar().charBox()))
					{

						// si il se protege
						if(((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isBlocking()){
							((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isBlockstunned=true;
							//etourdi pendant block stun de temps TODO

						}

						// il attaque aussi
						if(((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isTeching()){
							// on annule l'attaque
							((FightCharImpl)this.engine.getPlayer(i+1).getChar()).techFrame=false;
							((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isTeching=false;
							((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isHitstunned=true;
							// etourdi pendant hit stun de temps TODO
						}


						// il ne se protege pas 
						if(!((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isTeching()){
							// perte de vie 
							((FightCharImpl)this.engine.getPlayer(i+1).getChar()).life-=this.tech.damage();
							((FightCharImpl)this.engine.getPlayer(i+1).getChar()).isHitstunned=true;
							// etourdi pendant hit stun de temps  TODO

						}

						else{
							// rien a faire 
						}
					}
				}
			}


		}
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
	}
}
