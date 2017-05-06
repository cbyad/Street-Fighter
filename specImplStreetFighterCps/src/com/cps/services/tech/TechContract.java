package com.cps.services.tech;

import com.cps.exception.PostConditionError;
import com.cps.exception.PreConditionError;

public class TechContract extends TechDecorator{

	public TechContract(Tech d) {
		super(d);
	}
	
	public void checkInvariant(){

	}
	


	public void init(int damage, int hstun , int bstun , int sframe , int hframe,int rframe){
		// pre: damage>=0
		if (damage<0){
			throw new PreConditionError("damage>=0");
		}
		
		// pre: hstun>=0
		if (hstun<0){
			throw new PreConditionError("hstun>=0");
		}
		
		// pre: bstun>=0
		if (bstun<0){
			throw new PreConditionError("bstun>=0");
		}
		
		// pre: sframe>=0
		if (sframe<0){
			throw new PreConditionError("sframe>=0");
		}
		
		// pre: hframe>=0
		if (hframe<0){
			throw new PreConditionError("hframe>=0");
		}
		
		// pre: rframe>=0
		if (rframe<0){
			throw new PreConditionError("rframe>=0");
		}
		
		super.init(damage, hstun, bstun, sframe, hframe, rframe);
		
		// post: damage()=damage
		if (super.damage()!=damage){
			throw new PostConditionError("damage()=damage");
		}
		
		// post: hstun()=hstun
		if (super.hstun()!=hstun){
			throw new PostConditionError("hstun()=hstun");
		}
		
		// post: bstun()=bstun
		if (super.bstun()!=bstun){
			throw new PostConditionError("bstun()=bstun");
		}
		
		// post: sframe()=sframe
		if (super.sframe()!=sframe){
			throw new PostConditionError("sframe()=sframe");
		}
		
		// post: hframe()=hframe
		if (super.hframe()!=hframe){
			throw new PostConditionError("hframe()=hframe");
		}
		
		// post: rframe()=rframe
		if (super.rframe()!=rframe){
			throw new PostConditionError("rframe()=rframe");
		}
	}

}
