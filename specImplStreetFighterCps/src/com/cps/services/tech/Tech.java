package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

public interface Tech {

	/*Observators*/
	public int damage();
	public int hstun();
	public int bstun();
	public int sframe();
	public int hframe();
	public int rframe();
	public Hitbox getHitBox(int x ,int y,int h,int l);
	
	/*Constructors*/
	public void init(int damage, int hstun , int bstun , int sframe , int hframe ,int rframe);
}
