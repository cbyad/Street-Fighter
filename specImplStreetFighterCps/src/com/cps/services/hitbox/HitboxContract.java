package com.cps.services.hitbox;

import com.cps.exception.InvariantError;
import com.cps.exception.PostConditionError;
import com.cps.exception.PreConditionError;


public class HitboxContract extends HitboxDecorator{

	public HitboxContract(Hitbox service) {
		super(service);
	}

	public void checkInvariant(){
		// ( PositionX()<H1.PositionX() ) ^ ( PositionY()<H1.PositionY() ) => CollidesWith(H1) = ( H1.PositionX()-PositionX() < Length() ) ^ ( H1.PositionY()-PositionY() <Height() )
		// ( PositionX()<H1.PositionX() ) ^ ( PositionY()>H1.PositionY() ) => CollidesWith(H1) = ( H1.PositionX()-PositionX() < Length() ) ^ ( PositionY()-H1.PositionY() <H1.Height() )
		// ( PositionX()>H1.PositionX() ) ^ ( PositionY()<H1.PositionY() ) => CollidesWith(H1) = ( PositionX()-H1.PositionX() < H1.Length() ) ^ ( H1.PositionY()-PositionY() <Height() )
		// ( PositionX()>H1.PositionX() ) ^ ( PositionY()>H1.PositionY() ) => CollidesWith(H1) = ( PositionX()-H1.PositionX() < H1.Length() ) ^ ( PositionY()-H1.PositionY() <H1.Height() )
		// EqualsTo(H1) = (PositionX()=H1.PositionX()) ^ (PositionY()=H1.PositionY()) ^ (Height()=H1.Height()) ^ (Length()=H1.Length())
		// BelongsTo(x,y) = (x-PositionX()>0)&&(x-PositionX()<Length())&&(y-PositionY()>0)&&(y-PositionY()<Height())
	}


	

	public void init (int x,int y, int h, int l){
		//  h>=0
		if (h<0)
			throw new PreConditionError("h>0");
		//  l>=0
		if (l<0)
			throw new PreConditionError("l>=0");
		
		super.init(x, y, h, l);
		
		checkInvariant();
		
		// PositionX() = x
		if(super.PositionX()!=x)
			throw new PostConditionError("PositionX()=x");
		// PositionY() = y
		if (super.PositionY()!=y)
			throw new PostConditionError("PositionY()=y");
		// Length()=l
		if (super.Length()!=l)
			throw new PostConditionError("Length()==l");
		// Height()=h
		if (super.Height()!=h)
			throw new PostConditionError("Height()=h");
	}
	

	

	public void MoveTo (int x,int y){
		int h=super.Height();
		int l=super.Length();
		super.MoveTo(x, y);
		checkInvariant();
		
		// PositionX() = x
		if(super.PositionX()!=x)
			throw new PostConditionError("PositionX()=x");
		// PositionY() = y
		if (super.PositionY()!=y)
			throw new PostConditionError("PositionY()=y");
		

		// forAll u,v:int × int, BelongsTo(u,v) = Belongsto(u-(x-PositionX()@pre),v-(y-PositionY()@pre)@pre
		
		// Length() = Length()@pre
		if (super.Length()!=l)
			throw new PostConditionError("Length()=Length()@pre");
		
		// Height() = Height()@pre
		if (super.Height()!=h)
			throw new PostConditionError("Height()=Height()@pre");
	}

	public void SetHeight(int h) {
		
		int l=super.Length();
		int x=super.PositionX();
		int y=super.PositionY();
		
		super.setHeight(h);
		checkInvariant();
		
		// Height() = h
		if (super.Height()!=h)
			throw new PostConditionError("Height()=h");
		
		// Length() = Length()@pre
		if (super.Length()!=l)
			throw new PostConditionError("Length()=Length()@pre");
		
		// PositionX() = PositionX()@pre
		if (super.PositionX()!=x)
			throw new PostConditionError("PositionX()=PositionX()@pre");
		
		// PositionY() = PositionY ()@pre
		if (super.PositionY()!=y)
			throw new PostConditionError("PositionY()=PositionY()@pre");
	}

	public void SetLength(int l) {
		
		int h=super.Height();
		int x=super.PositionX();
		int y=super.PositionY();
		
		super.setLength(l);
		checkInvariant();
		
		// Length() = l
		if (super.Length()!=l)
			throw new PostConditionError("Length()=l");
		
		// Height() = Height()@pre
		if (super.Height()!=h)
			throw new PostConditionError("Height()=Height()@pre");
		
		// PositionX() = PositionX()@pre
		if (super.PositionX()!=x)
			throw new PostConditionError("PositionX()=PositionX()@pre");
		
		// PositionY() = PositionY()@pre
		if (super.PositionY()!=y)
			throw new PostConditionError("PositionY()=PositionY()@pre");
		
	
	}
}
