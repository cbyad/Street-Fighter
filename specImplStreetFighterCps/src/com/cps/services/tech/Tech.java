package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

public interface Tech {

	/*Observators*/
	public int getDamage();
	public int getHstun();
	public int getBstun();
	public int getSframe();
	public int getHframe();
	public int getRframe();
	public Hitbox getHitBox(int x ,int y,int h,int l);
	
	/*Constructors*/
	public void init(int damage, int hstun , int bstun , int sframe , int hframe ,int rframe);
}
