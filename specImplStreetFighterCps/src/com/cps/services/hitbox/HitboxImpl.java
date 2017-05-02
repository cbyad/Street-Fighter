package com.cps.services.hitbox;

public class HitboxImpl implements Hitbox{

	private int x;
	private int y;
	private int h;
	private int l;

	@Override
	public int PositionX(){
		return this.x;
	}

	@Override
	public int PositionY(){
		return this.y;
	}

	@Override
	public int Length(){
		return this.l;
	}

	@Override
	public int Height(){
		return this.h;
	}

	@Override
	public boolean BelongsTo (int x,int y){
		return ((x-this.x>=0)&&(x-this.x<=this.l)&&(y-this.y>=0)&&(y-this.y<=this.h));
	}

	@Override
	public boolean CollidesWith (Hitbox h){
		if ((this.PositionX()<=h.PositionX())&&(this.PositionY()<=h.PositionY()))
			return this.BelongsTo(h.PositionX(), h.PositionY());
		else if ((this.PositionX()<=h.PositionX())&&(this.PositionY()>=h.PositionY())){
			if ((h.PositionX()<=this.PositionX()+this.Length())&&(h.PositionY()+h.Height()>=this.PositionY()))
				return true;
		}
		else if ((this.PositionX()>=h.PositionX())&&(this.PositionY()<=h.PositionY())){
			if ((this.PositionX()<=h.PositionX()+h.Length())&&(this.PositionY()+this.Height()>=h.PositionY()))
				return true;
		}
		else if ((this.PositionX()>=h.PositionX())&&(this.PositionY()>=h.PositionY()))
			return h.BelongsTo(this.PositionX(), this.PositionY());

		return false;
	}

	@Override
	public boolean EqualsTo (Hitbox h){
		return (this.PositionX()==h.PositionX())&&(this.PositionY()==h.PositionY())
				&&(this.Length()==h.Length())&&(this.Height()==h.Height());
	}


	@Override
	public void init (int x,int y, int h, int l){
		this.x=x;
		this.y=y;
		this.h=h;
		this.l=l;
	}


	@Override
	public void MoveTo (int x,int y){
		this.x=x;
		this.y=y;
	}

	@Override
	public void SetHeight (int h){
		this.h=h;
	}


	@Override
	public void SetLength (int l){
		this.l=l;
	}



}
