package com.cps.services.engine;

import com.cps.services.character.Player;

public class EngineImplBug implements Engine{
	
	public  EngineImplBug() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Character getChar(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayer(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step(Commande c1, Commande c2) {
		// TODO Auto-generated method stub
		
	}

}