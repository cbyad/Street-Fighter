package com.cps.services.engine;

import com.cps.services.player.Player;
import com.cps.services.character.Character;

public class EngineImplBug implements Engine{
	
	public  EngineImplBug() {
		// TODO Auto-generated constructor stub
	}
	

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Character getChar(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Player getPlayer(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public void init(int h, int w, int s, Player p1, Player p2) {
		// TODO Auto-generated method stub
		
	}

	public void step(Commande c1, Commande c2) {
		// TODO Auto-generated method stub
		
	}

}
