package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

public abstract class TechDecorator implements Tech{
	private Tech delegate ;
	
	public TechDecorator(Tech d) {
		this.delegate=d ;
	}
	
	@Override
	public int getDamage() {
		return delegate.getDamage();
	}

	@Override
	public int getHstun() {
		return delegate.getBstun();
	}

	@Override
	public int getBstun() {
		return delegate.getBstun();
	}

	@Override
	public int getSframe() {
		return delegate.getSframe();
	}

	@Override
	public int getHframe() {
		return delegate.getHframe();
	}

	@Override
	public int getRframe() {
		return delegate.getRframe();
	}

	@Override
	public Hitbox getHitBox(int x, int y) {
		return delegate.getHitBox(x, y);
	}

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe) {
		delegate.init(damage, hstun, bstun, sframe, hframe, rframe);
	}

}
