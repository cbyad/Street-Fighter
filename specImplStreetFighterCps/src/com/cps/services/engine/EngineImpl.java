package com.cps.services.engine;

import com.cps.exception.ContractError;
import com.cps.services.player.Player;
import com.cps.services.character.Character;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.hitbox.HitboxImpl;

public class EngineImpl implements Engine{

	private int height ;
	private int width ;

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

		if (i==1) return p1.getChar() ;
		if (i==2) return p2.getChar() ;
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
	}

	public void step(Commande c1, Commande c2) {
		//verifier que le deplacement ne depasse pas les limites du jeu

		if(c1==Commande.LEFT){
			if(p1.getChar().charBox().PositionX()>=0 ){ // deplacement a gauche possible
				this.p1.getChar().step(c1);
			}
		}

		if(c1==Commande.RIGHT){
			if(p1.getChar().charBox().PositionX()<=getWidth()-p1.getChar().charBox().Length() ){ 
				this.p1.getChar().step(c1);
			}
		}

		if(c2==Commande.RIGHT){
			if(p2.getChar().charBox().PositionX()<=getWidth()-p2.getChar().charBox().Length()){ //deplacement a droite possible
				this.p2.getChar().step(c2);
			}
		}

		if(c2==Commande.LEFT){
			if(p2.getChar().charBox().PositionX()>=0){ 
				this.p2.getChar().step(c2);
			}
		}
	}
}
