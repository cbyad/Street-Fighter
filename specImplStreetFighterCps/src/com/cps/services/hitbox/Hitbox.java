package com.cps.services.hitbox;


/**
 * Service : Moteur de jeu
 * @author cb_mac
 *
 */
/****************************************
 *  OK
 * *************************************/
public interface Hitbox {

	/* Invariants */
	// \inv: ( PositionX()<H1.PositionX() ) ^ ( PositionY()<H1.PositionY() ) => CollidesWith(H1) = ( H1.PositionX()-PositionX() < Length() ) ^ ( H1.PositionY()-PositionY() <Height() )
	// \inv: ( PositionX()<H1.PositionX() ) ^ ( PositionY()>H1.PositionY() ) => CollidesWith(H1) = ( H1.PositionX()-PositionX() < Length() ) ^ ( PositionY()-H1.PositionY() <H1.Height() )
	// \inv: ( PositionX()>H1.PositionX() ) ^ ( PositionY()<H1.PositionY() ) => CollidesWith(H1) = ( PositionX()-H1.PositionX() < H1.Length() ) ^ ( H1.PositionY()-PositionY() <Height() )
	// \inv: ( PositionX()>H1.PositionX() ) ^ ( PositionY()>H1.PositionY() ) => CollidesWith(H1) = ( PositionX()-H1.PositionX() < H1.Length() ) ^ ( PositionY()-H1.PositionY() <H1.Height() )
	// \inv: EqualsTo(H1) = (PositionX()=H1.PositionX()) ^ (PositionY()=H1.PositionY()) ^ (Height()=H1.Height()) ^ (Length()=H1.Length())
	// \inv: BelongsTo(x,y) = (x-PositionX()>0)&&(x-PositionX()<Length())&&(y-PositionY()>0)&&(y-PositionY()<Height())
		
	
	/* Observators */
	
	public int PositionX();
	public int PositionY();
	public int Length();
	public int Height();
	public boolean BelongsTo (int x,int y);
	public boolean CollidesWith (Hitbox h);
	public boolean EqualsTo (Hitbox h);
	
	
	/* Constructors */
	
	// \pre: h>=0
	// \pre: l>=0
	// \post: PositionX() = x
	// \post: PositionY() = y
	// \post: Length()=l
	// \post: Height()=h
	public void init (int x,int y, int h, int l);
	
	/* Operators: */
	
	// \post: PositionX() = x
	// \post: PositionY() = y
	// \post: forAll u,v:int × int, BelongsTo(u,v) = Belongsto(u-(x-PositionX()@pre),v-(y-PositionY()@pre)@pre
	// \post: Length() = Length()@pre
	// \post: Height() = Height()@pre
	public void MoveTo (int x,int y);

	// \post: Height() = h
	// \post: Length() = Length()@pre
	// \post: PositionX() = PositionX()@pre
	// \post: PositionY() = PositionY()@pre
	public void SetHeight (int h);
			
	
	// \post: Length() = l
	// \post: Height() = Height()@pre
	// \post: PositionX() = PositionX()@pre
	// \post: PositionY() = PositionY()@pre
	public void SetLength (int l);
	


}