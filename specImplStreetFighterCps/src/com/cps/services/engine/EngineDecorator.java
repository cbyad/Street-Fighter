package com.cps.services.engine;

import com.cps.services.character.Player;


/****************************************
 *  OK
 * *************************************/
public  abstract class EngineDecorator implements Engine{ 

	private Engine delegate ;

	public EngineDecorator(Engine d) {
		this.delegate=d ;
	}
	@Override
	public int getHeight() {
		return delegate.getHeight() ;
	}

	@Override
	public int getWidth() {
		return delegate.getWidth() ;
	}

	@Override
	public Character getChar(int i) {
		return delegate.getChar(i);
	}

	@Override
	public Player getPlayer(int i) {
		return delegate.getPlayer(i);
	}

	@Override
	public boolean isGameOver() {
		return delegate.isGameOver();
	}

	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {
		delegate.init(h, w, s, p1, p2);
	}

	@Override
	public void step(Commande c1, Commande c2) {
		delegate.step(c1, c2);
	}

}