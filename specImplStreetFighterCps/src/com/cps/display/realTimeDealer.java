package com.cps.display;

import com.cps.services.engine.EngineImpl;
import com.cps.services.engine.Commande;

import javafx.scene.Scene;

public class realTimeDealer extends Thread {

	public boolean p1left;
	public boolean p2left;
	public boolean p1right;
	public boolean p2right;
	public boolean p1jump;
	public boolean p2jump;
	public boolean p1crouch;
	public boolean p2crouch;
	public TestHitboxView rec;
	public TestHitboxView rec2;
	public Scene scene;
	public EngineImpl engine;
	
	public realTimeDealer(TestHitboxView rec, TestHitboxView rec2, Scene scene, EngineImpl engine){
		super();
		this.p1left=false;
		this.p2left=false;
		this.p1right=false;
		this.p2right=false;
		this.p1jump=false;
		this.p2jump=false;
		this.p1crouch=false;
		this.p2crouch=false;
		this.rec=rec;
		this.rec2=rec2;
		this.scene=scene;
		this.engine=engine;
	}
	
	public void run(){
		while(true){
			Commande c1=Commande.NEUTRAL;
			Commande c2=Commande.NEUTRAL;
			if (p1left){
				c1=Commande.LEFT;
			}
			
			if (p2left){
				c2=Commande.LEFT;
			}
			
			if (p1right){
				c1=Commande.RIGHT;
			}
			
			if (p2right){
				c2=Commande.RIGHT;
			}
			
			if (p1jump){
				c1=Commande.JUMP;
			}
			
			if (p2jump){
				c2=Commande.JUMP;
			}
			
			if (p1crouch){
				c1=Commande.CROUCH;
			}
			
			if (p2crouch){
				c2=Commande.CROUCH;
			}
			
			engine.step(c1, c2);
			
			rec.setX(engine.getChar(1).positionX());
			rec.setY(engine.getChar(1).positionY());
			rec.setHeight(engine.getChar(1).charBox().Height());
			rec.setWidth(engine.getChar(1).charBox().Length());
	
		
	
			rec2.setX(engine.getChar(2).positionX());
			rec2.setY(engine.getChar(2).positionY());
			rec2.setHeight(engine.getChar(2).charBox().Height());
			rec2.setWidth(engine.getChar(2).charBox().Length());
			/*
			this.p1left=false;
			this.p2left=false;
			this.p1right=false;
			this.p2right=false;
			this.p1jump=false;
			this.p2jump=false;
			this.p1crouch=false;
			this.p2crouch=false;
			*/
			try{
				Thread.sleep(50);
			} catch (InterruptedException e){
				System.err.println("Issue with sleep");
			}
		}
	}
}
