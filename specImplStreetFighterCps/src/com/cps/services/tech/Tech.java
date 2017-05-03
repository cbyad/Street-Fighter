package com.cps.services.tech;

import com.cps.services.hitbox.Hitbox;

/*********************
 * 
 * @author cb_mac
 *Tech ok
 */
public interface Tech {

	/*Observators*/
	public int damage();
	public int hstun();
	public int bstun();
	public int sframe();
	public int hframe();
	public int rframe();
	public Hitbox getHitBox();

	/*Constructors*/
	public void init(int damage, int hstun , int bstun , int sframe , int hframe ,int rframe);
	public void init(int damage, int hstun , int bstun , int sframe ,
			int hframe,int x, int y, int h , int l);
}
