package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

public abstract class TechDecorator implements Tech{
	private Tech delegate ;
	
	public TechDecorator(Tech d) {
		this.delegate=d ;
	}
	
	@Override
	public int damage() {
		return delegate.damage();
	}

	@Override
	public int hstun() {
		return delegate.hstun();
	}

	@Override
	public int bstun() {
		return delegate.bstun();
	}

	@Override
	public int sframe() {
		return delegate.sframe();
	}
	
	@Override
	public int hframe() {
		return delegate.hframe();
	}

	@Override
	public int rframe() {
		return delegate.rframe();
	}
	
	@Override
	public Hitbox getHitBox(int x ,int y,int h,int l){
		return delegate.getHitBox(x, y, h, l);
	}

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe) {
		delegate.init(damage, hstun, bstun, sframe, hframe, rframe);
	}

}
