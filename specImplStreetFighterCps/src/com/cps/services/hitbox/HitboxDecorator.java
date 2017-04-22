package com.cps.services.hitbox;



/****************************************
 *  OK
 * *************************************/
public  abstract class HitboxDecorator implements Hitbox{ 

	private Hitbox delegate ;

	public HitboxDecorator(Hitbox d) {
		this.delegate=d ;
	}
	
	public int PositionX(){
		return delegate.PositionX();
	}
	
	public int PositionY(){
		return delegate.PositionY();
	}
	
	public int Height(){
		return delegate.Height();
	}
	
	public int Length(){
		return delegate.Length();
	}
	
	public boolean BelongsTo (int x,int y){
		return delegate.BelongsTo(x, y);
	}
	
	public boolean CollidesWith (Hitbox h){
		return delegate.CollidesWith(h);
	}
	
	public boolean EqualsTo (Hitbox h){
		return delegate.EqualsTo(h);
	}
	
	public void init (int x,int y, int h, int l){
		delegate.init(x, y, h, l);
	}
	
	public void MoveTo (int x,int y){
		delegate.MoveTo(x, y);
	}
	
	public void setHeight(int h){
		delegate.SetHeight(h);
	}
	
	public void setLength(int l){
		delegate.SetLength(l);
	}

}
