package com.cps.test;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cps.services.hitbox.Hitbox;
import com.cps.services.hitbox.HitboxImpl;

public class HitBoxTest {


	@Before
	public void init() {
	}
	
	
	
	@Test
	public void belongsTo() {
		Hitbox hit = new HitboxImpl();
		hit.init(0, 0, 20, 30);
		
		assertTrue(hit.BelongsTo(1, 1));
		assertTrue(hit.BelongsTo(1, 1));
		assertTrue(hit.BelongsTo(0, 0));
		assertTrue(hit.BelongsTo(10, 0));
		assertTrue(hit.BelongsTo(100, 45));
		assertTrue(hit.BelongsTo(40, 4));
	}
	
	
	@Test
	public void collisions() {
		Hitbox hit1 = new HitboxImpl();
		Hitbox hit2 = new HitboxImpl();
		Hitbox hit3 = new HitboxImpl();
		Hitbox hit4 = new HitboxImpl();
		
		hit1.init(10, 15, 55, 55);
		hit2.init(2, 5, 55,55);
		hit3.init(60, -10, 5, 20);
		hit4.init(6, -9, 3, 3);
		
		assertTrue(hit1.CollidesWith(hit2));
		assertTrue(hit2.CollidesWith(hit1));
		assertTrue(hit4.CollidesWith(hit2));
		assertTrue(hit3.CollidesWith(hit1));
		assertTrue(hit2.CollidesWith(hit4));
		assertTrue(hit1.CollidesWith(hit3));
	
	}
	

	
}



