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
	
	// pre: damage>=0
	// pre: hstun>=0
	// pre: bstun>=0
	// pre: sframe>=0
	// pre: hframe>=0
	// pre: rframe>=0
	// post: damage()=damage
	// post: hstun()=hstun
	// post: bstun()=bstun
	// post: sframe()=sframe
	// post: hframe()=hframe
	// post: rframe()=rframe
	public void init(int damage, int hstun , int bstun , int sframe , int hframe,int rframe);
	
	// pre: damage>=0
	// pre: hstun>=0
	// pre: bstun>=0
	// pre: sframe>=0
	// pre: hframe>=0
	// pre: rframe>=0
	// post: damage()=damage
	// post: hstun()=hstun
	// post: bstun()=bstun
	// post: sframe()=sframe
	// post: hframe()=hframe
	// post: rframe()=rframe
	// post: getHitBox().PositionX()=x
	// post: getHitbox().PositionY()=y
	// post: getHitbox().Height()=h
	//post: getHitbox().Length=l
	public void init(int damage, int hstun , int bstun , int sframe ,
			int hframe, int rframe, int x, int y, int h , int l);
}
