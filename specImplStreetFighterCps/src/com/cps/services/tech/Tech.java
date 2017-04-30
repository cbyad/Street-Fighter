package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

public interface Tech {

	public int getDamage() ;
	public int getHstun();
	public int getBstun();
	public int getSframe();
	public int getRframe();
	public Hitbox getHitBox(int x ,int y);
	
}
