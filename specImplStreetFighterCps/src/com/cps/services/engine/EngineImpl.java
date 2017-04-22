package com.cps.services.engine;

import com.cps.exception.ContractError;
import com.cps.services.player.Player;
import com.cps.services.character.Character;

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
	}

	public void step(Commande c1, Commande c2) {
		this.char1.step(c1);
		this.char2.step(c2);
		
	}

}
