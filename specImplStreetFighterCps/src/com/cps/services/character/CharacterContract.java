package com.cps.services.character;

import com.cps.exception.PostConditionError;
import com.cps.exception.PreConditionError;
import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;


public class CharacterContract extends CharacterDecorator{
	
	public CharacterContract(Character service) {
		super(service);
	}
	
	public void checkInvariant(){
		// positionX() > 0 && positionX() < Engine:: width(engine) - Box:: width(charBox)
		if (!((super.positionX()>0)&&(super.positionX()<super.engine().getWidth()-super.charBox().Length())))
			throw new PostConditionError("positionX()>0 && positionX() < Engine:: width(engine) - Box:: width(charBox) ");
		// positionY() > 0 && positionY() < Engine:: height(engine) - Box:: height(charBox)
		if (!((super.positionY()>0)&&(super.positionY()<super.engine().getHeight()-super.charBox().Height())))
			throw new PostConditionError("positionY()>0 && positionY() < Engine:: height(engine) - Box:: height(charBox)");
		// dead() = ¬(life() > 0)
		if ((super.dead()&&(super.life()>0))||(!super.dead()&&(super.life()<=0)))
			throw new PostConditionError("dead()= ¬(life() >0)");
	}


		public void moveLeft(){
			
			int x=super.positionX();
			int y=super.positionY();
			boolean f=super.faceRight();
			int l=super.life();
			
			super.moveLeft();
			checkInvariant();
			
			// (exists i, player(engine(), i) != C => collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre
			boolean exist=false;
			for (int i=0;i<2;i++){
				if (super.engine().getPlayer(i).getChar().equals(super.delegate)){
					if ((super.charBox().CollidesWith(super.engine().getPlayer(i).getChar().charBox())))
							exist=true;
				}
			}
			if (exist){
				if (super.positionX()!=x)
					throw new PostConditionError("(exists i, player(engine(),i)!=C => collisionwith(charBox(),charBox(player(engine(),i))))=> positionX()=positionX()@pre");
			}
			
			// positionX()@pre >= speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre - speed()
			else {
				if (x>=super.speed()){
					for (int i=0;i<2;i++){
						if (super.engine().getPlayer(i).getChar()!=super.delegate){
							if (!super.charBox().CollidesWith(super.engine().getPlayer(i).getChar().charBox())){
								if (super.positionX()!=x-super.speed())
									throw new PostConditionError("positionX()@pre >= speed() && (forAll i, player(engine(),i) !=C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre - speed()");
							}
						}
					}
				}
			
			
			// positionX()@pre < speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = 0
				else {
					for (int i=0;i<2;i++){
						if (super.engine().getPlayer(i).getChar()!=super.delegate){
							if (!super.charBox().CollidesWith(super.engine().getPlayer(i).getChar().charBox())){
								if (super.positionX()!=0)
									throw new PostConditionError("positionX()@pre >= speed() && (forAll i, player(engine(),i) !=C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre - speed()");
							}
						}
					}
				}
			}
			
			// faceRight() = faceRight()@pre
			if (super.faceRight()!=f)
				throw new PostConditionError("faceRight()=faceRight()@pre");
			
			// life() = life()@pre
			if (super.life()!=l)
				throw new PostConditionError("life()=life()@pre");
			
			// positionY() = positionY()@pre
			if (super.positionY()==y)
				throw new PostConditionError("positionY()=positionY()@pre");
		}
		
		public void moveRight()	{	
			int x=super.positionX();
			int y=super.positionY();
			boolean f=super.faceRight();
			int l=super.life();
			
			super.moveRight();
			checkInvariant();
			
			// (exists i, player(engine(), i) != C => collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre
			boolean exist=false;
			for (int i=0;i<2;i++){
				if (super.engine().getPlayer(i)!=super.delegate){
					if ((super.charBox().CollidesWith(super.engine().getPlayer(i).getChar().charBox())))
							exist=true;
				}
			}
			if (exist){
				if (super.positionX()!=x)
					throw new PostConditionError("(exists i, player(engine(),i)!=C => collisionwith(charBox(),charBox(player(engine(),i))))=> positionX()=positionX()@pre");
			}
			
			// Engine::width(engine) - Hitbox:: Length(charBox()) - positionX()@pre >= speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre + speed()
			else {
				if (super.engine().getWidth()-super.charBox().Length()-x>=super.speed()){
					for (int i=0;i<2;i++){
						if (super.engine().getPlayer(i).getChar()!=super.delegate){
							if (!super.charBox().CollidesWith(super.engine().getPlayer(i).getChar().charBox())){
								if (super.positionX()!=x+super.speed())
									throw new PostConditionError("Engine::width(engine) - Hitbox:: Length(charBox()) - positionX()@pre >= speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre + speed()");
							}
						}
					}
				}
			
			
			//  Engine::width(engine) - HitBox:: Length(charBox()) - positionX()@pre < speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = Engine::width(engine) - HitBox:: Length(charBox())

				else {
					for (int i=0;i<2;i++){
						if (super.engine().getPlayer(i).getChar()!=super.delegate){
							if (!super.charBox().CollidesWith(super.engine().getPlayer(i).getChar().charBox())){
								if (super.positionX()!=super.engine().getWidth()-super.charBox().Length())
									throw new PostConditionError("Engine::width(engine) - HitBox:: Length(charBox()) - positionX()@pre < speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = Engine::width(engine) - HitBox:: Length(charBox())");
							}
						}
					}
				}
			}
			
			// faceRight() = faceRight()@pre
			if (super.faceRight()!=f)
				throw new PostConditionError("faceRight()=faceRight()@pre");
			
			// life() = life()@pre
			if (super.life()!=l)
				throw new PostConditionError("life()=life()@pre");
			
			// positionX() = positionX()@pre
			if (super.positionX()==x)
				throw new PostConditionError("positionX()=positionX()@pre");
		}
		

		public void switchSide(){
			
			boolean f=super.faceRight();
			int x=super.positionX();
			int y=super.positionY();
			int l=super.life();
			
			super.switchSide();
			checkInvariant();
			
			// faceRight() != faceRight()@pre
			if(super.faceRight()==f)
				throw new PostConditionError("faceRight()!=faceRight()@pre");
			
			// positionX() = positionX()@pre
			if (super.positionX()!=x)
				throw new PostConditionError("positionX()=positionX()@pre");
				
			// positionY() = positionY()@pre
			if (super.positionY()!=y)
				throw new PostConditionError("positionY()=positionY()@pre");
			
			// life() = life()@pre
			if (super.life()!=l)
				throw new PostConditionError("life()=life()@pre");
			
		}
		

		public void step (Commande c){
			
			// step(Commande) requires ¬dead()
			if (super.dead())
				throw new PreConditionError("step(Commande) requires ¬dead()");
			
			super.step(c);
			checkInvariant();
			
			
			// step(LEFT) = moveLeft()
			// step(RIGHT) = moveRight()
			// step(NEUTRAL) = null
		}

		@Override
		public void init(int l, int s, boolean f, Engine e, Hitbox h) {
			
			// init(l,s,f,e,h) requires l > 0 && s > 0
			if ((l<=0)||(s<=0))
				throw new PreConditionError("init(l,s,f,e,h) requires l>0 && s>0");
			
			super.init(l, s, f, e, h);
			checkInvariant();
			
			// life() = l
			if (super.life()!=l)
				throw new PostConditionError("life()=l");
			
			// speed() = s
			if (super.speed()!=s)
				throw new PostConditionError("speed()=s");
				
			// faceRight() = f
			if (super.faceRight()!=f)
				throw new PostConditionError("faceRight()=f");
			
			// engine() = e
			if (super.engine()!=e)
				throw new PostConditionError("engine()=e");
			
			// charbox() = h
			if (super.charBox()!=h)
				throw new PostConditionError("charBox()=h");
			
		}


}
