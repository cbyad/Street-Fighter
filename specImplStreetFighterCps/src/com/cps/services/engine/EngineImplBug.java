package com.cps.services.engine;

import com.cps.exception.ContractError;
import com.cps.services.player.Player;
import com.cps.services.character.Character;
import com.cps.services.fightChar.FightChar;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.hitbox.HitboxImpl;

public class EngineImplBug implements Engine{

	private int height ;
	private int width ;

	private Player p1 ,p2 ; 
	private boolean gameOver ;
	private int defaultSpace ; //rajoute


	public EngineImplBug() {
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public FightChar getChar(int i) {

		if (i==1) return p1.getChar() ;
		if (i==2) return p2.getChar() ;
		else throw new ContractError("entier different de 1 ou 2 ");

	}
	
	@Override
	public Player getPlayer(int i) {
		if (i==1) return p1 ;
		if (i==2) return p2 ;
		else throw new ContractError("entier different de 1 ou 2 ");
	}
	
	@Override
	public boolean isGameOver() {
		return gameOver ;
	}

	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {

		this.height= h;
		this.width=w ;
		this.defaultSpace=s;
		this.p1 =p1 ;
		this.p2 =p2 ;
		this.gameOver =false ;
	}
	
	@Override
	public void step(Commande c1, Commande c2) {
		//verifier que le deplacement ne depasse pas les limites du jeu
				this.p1.getChar().step(c1);
				this.p2.getChar().step(c2);
	}
}
