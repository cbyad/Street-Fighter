package com.cps.display;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.cps.services.engine.EngineImpl;
import com.cps.services.engine.Commande;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

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
	
	private int wcycle1;
	private int wcycle2;
	
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
		this.wcycle1=0;
		this.wcycle2=0;
	}
	
	public void run(){
		while(true){
			Commande c1=Commande.NEUTRAL;
			Commande c2=Commande.NEUTRAL;
			if (p1left){
				wcycle1++;
				c1=Commande.LEFT;
			}
			
			if (p2left){
				wcycle2++;
				c2=Commande.LEFT;
			}
			
			if (p1right){
				wcycle1++;
				c1=Commande.RIGHT;
			}
			
			if (p2right){
				wcycle2++;
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
			try{
				if (engine.getChar(1).faceRight()){
					if (this.engine.getChar(1).getVSpeed()>0){
						rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1.png")))));
						this.engine.getChar(1).charBox().SetLength(41);
						this.engine.getChar(1).charBox().SetHeight(140);
					}
					else if (this.engine.getChar(1).getVSpeed()<0){
						rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2.png")))));
						this.engine.getChar(1).charBox().SetLength(42);
						this.engine.getChar(1).charBox().SetHeight(115);	
					}
					
					else{
						if (c1==Commande.NEUTRAL){
							wcycle1=0;
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle.png")))));
							this.engine.getChar(1).charBox().SetLength(30);
							
						}
						if ((c1==Commande.LEFT)||(c1==Commande.RIGHT)){
							if ((wcycle1%12)<6){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1.png")))));
								this.engine.getChar(1).charBox().SetLength(48);
							}	
							else{
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2.png")))));
								this.engine.getChar(1).charBox().SetLength(48);
							}
						}
						
						if (c1==Commande.CROUCH){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching.png")))));
							this.engine.getChar(1).charBox().SetLength(61);
							this.engine.getChar(1).charBox().SetHeight(78);
						}
					}
					
				}
				else {
					if (this.engine.getChar(1).getVSpeed()>0){
						rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1_rev.png")))));
						this.engine.getChar(1).charBox().SetLength(41);
						this.engine.getChar(1).charBox().SetHeight(140);
					}
					else if (this.engine.getChar(1).getVSpeed()<0){
						rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2_rev.png")))));
						this.engine.getChar(1).charBox().SetLength(42);
						this.engine.getChar(1).charBox().SetHeight(115);	
					}
					
					else{
						if (c1==Commande.NEUTRAL){
							wcycle1=0;
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle_rev.png")))));
							this.engine.getChar(1).charBox().SetLength(30);
							
						}
						if ((c1==Commande.LEFT)||(c1==Commande.RIGHT)){
							if ((wcycle1%12)<6){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1_rev.png")))));
								this.engine.getChar(1).charBox().SetLength(48);
							}	
							else{
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2_rev.png")))));
								this.engine.getChar(1).charBox().SetLength(48);
							}
						}
						if (c1==Commande.CROUCH){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching_rev.png")))));
							this.engine.getChar(1).charBox().SetLength(61);
							this.engine.getChar(1).charBox().SetHeight(78);
						}
					}
				}
				
				if (engine.getChar(2).faceRight()){
					if (this.engine.getChar(2).getVSpeed()>0){
						rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1.png")))));
						this.engine.getChar(2).charBox().SetLength(41);
						this.engine.getChar(2).charBox().SetHeight(140);
					}
					else if (this.engine.getChar(2).getVSpeed()<0){
						rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2.png")))));
						this.engine.getChar(2).charBox().SetLength(42);
						this.engine.getChar(2).charBox().SetHeight(115);	
					}
					
					else{
						if (c2==Commande.NEUTRAL){
							wcycle2=0;
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle.png")))));
							this.engine.getChar(2).charBox().SetLength(30);
							
						}
						if ((c2==Commande.LEFT)||(c2==Commande.RIGHT)){
							if ((wcycle2%12)<6){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1.png")))));
								this.engine.getChar(2).charBox().SetLength(48);
							}	
							else{
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2.png")))));
								this.engine.getChar(2).charBox().SetLength(48);
							}
						}
						
						if (c2==Commande.CROUCH){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching.png")))));
							this.engine.getChar(2).charBox().SetLength(61);
							this.engine.getChar(2).charBox().SetHeight(78);
						}
					}
					
				}
				else {
					if (this.engine.getChar(2).getVSpeed()>0){
						rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1_rev.png")))));
						this.engine.getChar(2).charBox().SetLength(41);
						this.engine.getChar(2).charBox().SetHeight(140);
					}
					else if (this.engine.getChar(2).getVSpeed()<0){
						rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2_rev.png")))));
						this.engine.getChar(2).charBox().SetLength(42);
						this.engine.getChar(2).charBox().SetHeight(115);	
					}
					
					else{
						if (c2==Commande.NEUTRAL){
							wcycle2=0;
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle_rev.png")))));
							this.engine.getChar(2).charBox().SetLength(30);
							
						}
						if ((c2==Commande.LEFT)||(c2==Commande.RIGHT)){
							if ((wcycle2%12)<6){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1_rev.png")))));
								this.engine.getChar(2).charBox().SetLength(48);
							}	
							else{
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2_rev.png")))));
								this.engine.getChar(2).charBox().SetLength(48);
							}
						}
						
						if (c2==Commande.CROUCH){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching_rev.png")))));
							this.engine.getChar(2).charBox().SetLength(61);
							this.engine.getChar(2).charBox().SetHeight(78);
						}
					}
				}
			} catch (Exception e){
				System.out.println("no care");
			}
			
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
