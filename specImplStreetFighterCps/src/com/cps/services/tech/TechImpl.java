package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

public class TechImpl implements Tech{
	private int damage ;
	private int hstun ;
	private int bstun ;
	private int sframe;
	private int hframe;
	private int rframe;
	private Hitbox hitbox ;
	
	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getHstun() {
		return hstun;
	}

	@Override
	public int getBstun() {
		return bstun;
	}

	@Override
	public int getSframe() {
		return sframe;
	}

	@Override
	public int getHframe() {
		return hframe;
	}

	@Override
	public int getRframe() {
		return rframe;
	}

	@Override
	public Hitbox getHitBox(int x, int y,int h, int l) {
		hitbox.init(x, y, h, l);
		return hitbox ;
	}

	@Override
	public void init(int damage, int hstun, int bstun, int sframe, int hframe, int rframe) {
		this.damage=damage;
		this.hstun=hstun;
		this.bstun=bstun;
		this.sframe=sframe;
		this.hframe=hframe;
		this.rframe=rframe;
		
	}

}
