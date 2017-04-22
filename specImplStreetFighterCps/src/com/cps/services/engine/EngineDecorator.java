package com.cps.services.engine;

import com.cps.services.player.Player;
import com.cps.services.character.Character;


/****************************************
 *  OK
 * *************************************/
public  abstract class EngineDecorator implements Engine{ 

	private Engine delegate ;

	public EngineDecorator(Engine d) {
		this.delegate=d ;
	}

	public int getHeight() {
		return delegate.getHeight() ;
	}


	public int getWidth() {
		return delegate.getWidth() ;
	}

	public Character getChar(int i) {
		return delegate.getChar(i);
	}

	public Player getPlayer(int i) {
		return delegate.getPlayer(i);
	}

	public boolean isGameOver() {
		return delegate.isGameOver();
	}

	public void init(int h, int w, int s, Player p1, Player p2) {
		delegate.init(h, w, s, p1, p2);
	}

	public void step(Commande c1, Commande c2) {
		delegate.step(c1, c2);
	}

}
