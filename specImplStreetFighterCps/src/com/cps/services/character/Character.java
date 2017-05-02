package com.cps.services.character;

import com.cps.services.engine.Commande;
import com.cps.services.engine.Engine;
import com.cps.services.hitbox.Hitbox;

/**
 * Service : Personnage
 * @author cb_mac
 *
 */
public interface Character {
	
	/* Invariants */
	// \inv: positionX() > 0 && positionX() < Engine:: width(engine) - Box:: width(charBox)
	// \inv: positionY() > 0 && positionY() < Engine:: height(engine) - Box:: height(charBox)
	// \inv: dead() = ¬(life() > 0)
	
	/* Observators */
	
	public int positionX();
	public int positionY();
	public Engine engine();
	public Hitbox charBox();
	public int life();
	public int speed();
	public boolean faceRight();
	public boolean dead();

	/* Constructors */
	
	// \pre: init(l,s,f,e,h) requires l > 0 && s > 0
	// \post: life() = l
	// \post: speed() = s
	// \post: faceRight() = f
	// \post: engine() = e
	// \post: charbox() = h
	public void init (int l, int s, boolean f, Engine e, Hitbox h);

	/* Operators */
	
	// \post: (exists i, player(engine(), i) != C => collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre
	// \post: positionX()@pre >= speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre - speed()
	// \post: positionX()@pre < speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = 0
	// \post: faceRight() = faceRight()@pre
	// \post: life() = life()@pre
	// \post: positionY() = positionY()@pre
	public void moveLeft();
	
	// \post: (exists i, player(engine(), i) != C => collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre
	// \post: Engine::width(engine) - Hitbox:: Length(charBox()) - positionX()@pre >= speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = positionX()@pre + speed()
	// \post: Engine::width(engine) - HitBox:: Length(charBox()) - positionX()@pre < speed() && (forAll i, player(engine(), i) != C => ¬collisionwith(charBox(), charBox(player(engine(), i)))) => positionX() = Engine::width(engine) - HitBox:: Length(charBox())
	// \post: faceRight() = faceRight()@pre
	// \post: life() = life()@pre
	// \post: positionY() = positionY()@pre
	public void moveRight();
	
	// \post: faceRight() != faceRight()@pre
	// \post: positionX() = positionX()@pre
	// \post: positionY() = positionY()@pre
	// \post: life() = life()@pre
	public void switchSide();
	
	// \pre: step(Commande) requires ¬dead()
	// \post: step(LEFT) = moveLeft()
	// \post: step(RIGHT) = moveRight()
	// \post: step(NEUTRAL) = null
	public void step (Commande c);
	


}