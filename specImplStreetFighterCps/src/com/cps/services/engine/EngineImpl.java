package com.cps.services.engine;

import com.cps.exception.ContractError;
import com.cps.services.player.Player;
import com.cps.services.character.Character;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.hitbox.HitboxImpl;

public class EngineImpl implements Engine{

	private int height ;
	private int width ;
	private Character char1 ,char2 ; 
	private Player p1 ,p2 ; 
	private boolean gameOver ;
	private int defaultSpace ; //rajoute


	public EngineImpl() {
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Character getChar(int i) {

		if (i==1) return char1 ;
		if (i==2) return char2 ;
		else throw new ContractError("entier different de 1 ou 2 ");

	}

	public Player getPlayer(int i) {
		if (i==1) return p1 ;
		if (i==2) return p2 ;
		else throw new ContractError("entier different de 1 ou 2 ");
	}

	public boolean isGameOver() {
		return gameOver ;
	}


	public void init(int h, int w, int s, Player p1, Player p2) {

		this.height= h;
		this.width=w ;
		this.defaultSpace=s;
		this.p1 =p1 ;
		this.p2 =p2 ;
		this.gameOver =false ;
		//TODO initialiser les personnages sur la scene  pas sur!!!!
		
		Hitbox hit1 = new HitboxImpl(); 
		Hitbox hit2 = new HitboxImpl(); 
		char1.init(w/2-s/2, 0, false, this, hit1);
		char2.init(w/2+s/2, 0, false, this, hit2);
	}

	public void step(Commande c1, Commande c2) {
		//verifier que le deplacement ne depasse pas les limites du jeu

		if(c1==Commande.LEFT){
			if(char1.charBox().PositionX()>=0 ){ // deplacement a gauche possible
				this.char1.step(c1);
			}
		}

		if(c1==Commande.RIGHT){
			if(char1.charBox().PositionX()<=getWidth()-char2.charBox().Length() ){ 
				this.char1.step(c1);
			}
		}

		if(c2==Commande.RIGHT){
			if(char2.charBox().PositionX()<=getWidth()-char2.charBox().Length()){ //deplacement a droite possible
				this.char2.step(c2);
			}
		}

		if(c2==Commande.LEFT){
			if(char2.charBox().PositionX()>=0){ 
				this.char2.step(c2);
			}
		}
	}
}
