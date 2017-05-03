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
	public boolean p1block;
	public boolean p2block;
	public boolean p1tech1;
	public boolean p2tech1;
	public TestHitboxView rec;
	public TestHitboxView rec2;
	public TestHitboxView rechit;
	public TestHitboxView rechit2;
	public Scene scene;
	public EngineImpl engine;
	
	private int wcycle1;
	private int wcycle2;
	
	private int techcycle1[];
	private int techcycle2[];
	
	private String tech1;
	private String tech2;
	
	public realTimeDealer(TestHitboxView rec, TestHitboxView rec2, TestHitboxView rechit, TestHitboxView rechit2, Scene scene, EngineImpl engine){
		super();
		this.p1left=false;
		this.p2left=false;
		this.p1right=false;
		this.p2right=false;
		this.p1jump=false;
		this.p2jump=false;
		this.p1crouch=false;
		this.p2crouch=false;
		this.p1block=false;
		this.p2block=false;
		this.p1tech1=false;
		this.p2tech1=false;
		this.rec=rec;
		this.rec2=rec2;
		this.scene=scene;
		this.engine=engine;
		this.wcycle1=0;
		this.wcycle2=0;
		this.rechit=rechit;
		this.rechit2=rechit2;
		this.techcycle1=new int[3];
		this.techcycle2=new int[3];
	}
	
	public void run(){
		while(true){
			Commande c1=Commande.NEUTRAL;
			Commande c2=Commande.NEUTRAL;
			
			
			if (p1left){
				p1block=false;
				wcycle1++;
				c1=Commande.LEFT;
			}
			
			if (p2left){
				p2block=false;
				wcycle2++;
				c2=Commande.LEFT;
			}
			
			if (p1right){
				p1block=false;
				wcycle1++;
				c1=Commande.RIGHT;
			}
			
			if (p2right){
				p2block=false;
				wcycle2++;
				c2=Commande.RIGHT;
			}
			
			if (p1jump){
				p1block=false;
				c1=Commande.JUMP;
			}
			
			if (p2jump){
				p2block=false;
				c2=Commande.JUMP;
			}
			
			if (p1crouch){
				p1block=false;
				c1=Commande.CROUCH;
			}
			
			if (p2crouch){
				p2block=false;
				c2=Commande.CROUCH;
			}
			
			if (p1block){
				c1=Commande.BLOCK;
			}
			
			if (p2block){
				c2=Commande.BLOCK;
			}
			
			if (p1tech1){
				c1=Commande.PUNCH;
				this.tech1="punch";
			}
			
			if (p2tech1){
				c2=Commande.PUNCH;
				this.tech2="punch";
			}
			
			engine.step(c1, c2);
			
			if (p1tech1){
				techcycle1[0]=engine.getChar(1).tech().sframe();
				techcycle1[1]=engine.getChar(1).tech().hframe();
				techcycle1[2]=engine.getChar(1).tech().rframe();
				p1tech1=false;
			}
			
			if (p2tech1){
				techcycle2[0]=engine.getChar(2).tech().sframe();
				techcycle2[1]=engine.getChar(2).tech().hframe();
				techcycle2[2]=engine.getChar(2).tech().rframe();
				p2tech1=false;
			}

			try{
				if (engine.getChar(1).faceRight()){
					if (this.engine.getChar(1).isTeching()){
						if (techcycle1[0]>0){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching1.png")))));
							this.engine.getChar(1).charBox().SetLength(68);
							this.engine.getChar(1).charBox().SetHeight(100);
							rec.setX(engine.getChar(1).positionX());

							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
							techcycle1[0]--;
						}
						else {
							if (techcycle1[1]>0){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching2.png")))));
								this.engine.getChar(1).charBox().SetLength(68);
								this.engine.getChar(1).charBox().SetHeight(97);
								rec.setX(engine.getChar(1).positionX());

								rec.setHeight(92);
								rec.setWidth(97);
								techcycle1[1]--;
							}
							else {
								if (techcycle1[2]>0){
									rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching3.png")))));
									this.engine.getChar(1).charBox().SetLength(65);
									this.engine.getChar(1).charBox().SetHeight(107);
									rec.setX(engine.getChar(1).positionX());

									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									techcycle1[2]--;
								}
							}
						}
					}
					else {
						if (this.engine.getChar(1).isBlocking()){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/blocking.png")))));
							this.engine.getChar(1).charBox().SetLength(57);
							this.engine.getChar(1).charBox().SetHeight(107);
							rec.setX(engine.getChar(1).positionX());

							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
						}
						
						else {
							
							if (this.engine.getChar(1).getVSpeed()>0){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1.png")))));
								this.engine.getChar(1).charBox().SetLength(41);
								this.engine.getChar(1).charBox().SetHeight(140);
								rec.setX(engine.getChar(1).positionX());

								rec.setHeight(engine.getChar(1).charBox().Height());
								rec.setWidth(engine.getChar(1).charBox().Length());
							}
							else if (this.engine.getChar(1).getVSpeed()<0){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2.png")))));
								this.engine.getChar(1).charBox().SetLength(42);
								this.engine.getChar(1).charBox().SetHeight(115);	
								rec.setX(engine.getChar(1).positionX());

								rec.setHeight(engine.getChar(1).charBox().Height());
								rec.setWidth(engine.getChar(1).charBox().Length());
							}
							
							else{
								if (c1==Commande.NEUTRAL){
									wcycle1=0;
									rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle.png")))));
									this.engine.getChar(1).charBox().SetLength(30);
									rec.setX(engine.getChar(1).positionX());

									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									
								}
								if ((c1==Commande.LEFT)||(c1==Commande.RIGHT)){
									if ((wcycle1%12)<6){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1.png")))));
										this.engine.getChar(1).charBox().SetLength(48);
										rec.setX(engine.getChar(1).positionX());

										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
									}	
									else{
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2.png")))));
										this.engine.getChar(1).charBox().SetLength(48);
										rec.setX(engine.getChar(1).positionX());

										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
									}
								}
								
								if (c1==Commande.CROUCH){
									rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching.png")))));
									this.engine.getChar(1).charBox().SetLength(61);
									this.engine.getChar(1).charBox().SetHeight(78);
									rec.setX(engine.getChar(1).positionX());

									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
								}
							}
						}
					}
				}
				else {
					if (this.engine.getChar(1).isTeching()){
						if (techcycle1[0]>0){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching1_rev.png")))));
							this.engine.getChar(1).charBox().SetLength(68);
							this.engine.getChar(1).charBox().SetHeight(100);
							rec.setX(engine.getChar(1).positionX());

							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
							techcycle1[0]--;
						}
						else {
							if (techcycle1[1]>0){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching2_rev.png")))));
								this.engine.getChar(1).charBox().SetLength(68);
								this.engine.getChar(1).charBox().SetHeight(97);
								rec.setX(engine.getChar(1).positionX()-39);

								rec.setHeight(92);
								rec.setWidth(97);
								techcycle1[1]--;
							}
							else {
								if (techcycle1[2]>0){
									rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching3_rev.png")))));
									this.engine.getChar(1).charBox().SetLength(65);
									this.engine.getChar(1).charBox().SetHeight(107);
									rec.setX(engine.getChar(1).positionX());

									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									techcycle1[2]--;
								}
							}
						}
					}
					else {
						if (this.engine.getChar(1).isBlocking()){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/blocking_rev.png")))));
							this.engine.getChar(1).charBox().SetLength(57);
							this.engine.getChar(1).charBox().SetHeight(107);
							rec.setX(engine.getChar(1).positionX());

							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
						}
						else {
							if (this.engine.getChar(1).getVSpeed()>0){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1_rev.png")))));
								this.engine.getChar(1).charBox().SetLength(41);
								this.engine.getChar(1).charBox().SetHeight(140);
								rec.setX(engine.getChar(1).positionX());

								rec.setHeight(engine.getChar(1).charBox().Height());
								rec.setWidth(engine.getChar(1).charBox().Length());
							}
							else if (this.engine.getChar(1).getVSpeed()<0){
								rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2_rev.png")))));
								this.engine.getChar(1).charBox().SetLength(42);
								this.engine.getChar(1).charBox().SetHeight(115);
								rec.setX(engine.getChar(1).positionX());

								rec.setHeight(engine.getChar(1).charBox().Height());
								rec.setWidth(engine.getChar(1).charBox().Length());
							}
							
							else{
								if (c1==Commande.NEUTRAL){
									wcycle1=0;
									rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle_rev.png")))));
									this.engine.getChar(1).charBox().SetLength(30);
									rec.setX(engine.getChar(1).positionX());

									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									
								}
								if ((c1==Commande.LEFT)||(c1==Commande.RIGHT)){
									if ((wcycle1%12)<6){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(48);
										rec.setX(engine.getChar(1).positionX());

										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
									}	
									else{
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(48);
										rec.setX(engine.getChar(1).positionX());

										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
									}
								}
								if (c1==Commande.CROUCH){
									rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching_rev.png")))));
									this.engine.getChar(1).charBox().SetLength(61);
									this.engine.getChar(1).charBox().SetHeight(78);
									rec.setX(engine.getChar(1).positionX());

									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
								}
							}
						}
					}
				}
				
				if (engine.getChar(2).faceRight()){
					if (this.engine.getChar(2).isTeching()){
						if (techcycle2[0]>0){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching1.png")))));
							this.engine.getChar(2).charBox().SetLength(68);
							this.engine.getChar(2).charBox().SetHeight(100);
							rec2.setX(engine.getChar(2).positionX());

							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
							techcycle2[0]--;
						}
						else {
							if (techcycle2[1]>0){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching2.png")))));
								this.engine.getChar(2).charBox().SetLength(68);
								this.engine.getChar(2).charBox().SetHeight(97);
								rec2.setX(engine.getChar(2).positionX());

								rec2.setHeight(92);
								rec2.setWidth(97);
								techcycle2[1]--;
							}
							else {
								if (techcycle2[2]>0){
									rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching3.png")))));
									this.engine.getChar(2).charBox().SetLength(65);
									this.engine.getChar(2).charBox().SetHeight(107);
									rec2.setX(engine.getChar(2).positionX());

									rec2.setHeight(engine.getChar(1).charBox().Height());
									rec2.setWidth(engine.getChar(1).charBox().Length());
									techcycle2[2]--;
								}
							}
						}
					}
					else {
						if (this.engine.getChar(2).isBlocking()){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/blocking.png")))));
							this.engine.getChar(2).charBox().SetLength(57);
							this.engine.getChar(2).charBox().SetHeight(107);
							rec2.setX(engine.getChar(2).positionX());

							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
						}
						else {
							if (this.engine.getChar(2).getVSpeed()>0){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1.png")))));
								this.engine.getChar(2).charBox().SetLength(41);
								this.engine.getChar(2).charBox().SetHeight(140);
								rec2.setX(engine.getChar(2).positionX());

								rec2.setHeight(engine.getChar(2).charBox().Height());
								rec2.setWidth(engine.getChar(2).charBox().Length());
							}
							else if (this.engine.getChar(2).getVSpeed()<0){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2.png")))));
								this.engine.getChar(2).charBox().SetLength(42);
								this.engine.getChar(2).charBox().SetHeight(115);
								rec2.setX(engine.getChar(2).positionX());

								rec2.setHeight(engine.getChar(2).charBox().Height());
								rec2.setWidth(engine.getChar(2).charBox().Length());
							}
							
							else{
								if (c2==Commande.NEUTRAL){
									wcycle2=0;
									rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle.png")))));
									this.engine.getChar(2).charBox().SetLength(30);
									rec2.setX(engine.getChar(2).positionX());

									rec2.setHeight(engine.getChar(2).charBox().Height());
									rec2.setWidth(engine.getChar(2).charBox().Length());
									
								}
								if ((c2==Commande.LEFT)||(c2==Commande.RIGHT)){
									if ((wcycle2%12)<6){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1.png")))));
										this.engine.getChar(2).charBox().SetLength(48);
										rec2.setX(engine.getChar(2).positionX());

										rec2.setHeight(engine.getChar(2).charBox().Height());
										rec2.setWidth(engine.getChar(2).charBox().Length());
									}	
									else{
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2.png")))));
										this.engine.getChar(2).charBox().SetLength(48);
										rec2.setX(engine.getChar(2).positionX());

										rec2.setHeight(engine.getChar(2).charBox().Height());
										rec2.setWidth(engine.getChar(2).charBox().Length());
									}
								}
								
								if (c2==Commande.CROUCH){
									rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching.png")))));
									this.engine.getChar(2).charBox().SetLength(61);
									this.engine.getChar(2).charBox().SetHeight(78);
									rec2.setX(engine.getChar(2).positionX());

									rec2.setHeight(engine.getChar(2).charBox().Height());
									rec2.setWidth(engine.getChar(2).charBox().Length());
								}
							}
						}
					}
					
				}
				else {
					if (this.engine.getChar(2).isTeching()){
						if (techcycle2[0]>0){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching1_rev.png")))));
							this.engine.getChar(2).charBox().SetLength(68);
							this.engine.getChar(2).charBox().SetHeight(100);
							rec2.setX(engine.getChar(2).positionX());

							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
							techcycle2[0]--;
						}
						else {
							if (techcycle2[1]>0){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching2_rev.png")))));
								this.engine.getChar(2).charBox().SetLength(68);
								this.engine.getChar(2).charBox().SetHeight(97);
								rec2.setX(engine.getChar(2).positionX()-39);

								rec2.setHeight(92);
								rec2.setWidth(97);
								techcycle2[1]--;
							}
							else {
								if (techcycle2[2]>0){
									rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching3_rev.png")))));
									this.engine.getChar(2).charBox().SetLength(65);
									this.engine.getChar(2).charBox().SetHeight(107);
									rec2.setX(engine.getChar(2).positionX());

									rec2.setHeight(engine.getChar(1).charBox().Height());
									rec2.setWidth(engine.getChar(1).charBox().Length());
									techcycle2[2]--;
								}
							}
						}
					}
					else {
						if (this.engine.getChar(2).isBlocking()){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/blocking_rev.png")))));
							this.engine.getChar(2).charBox().SetLength(57);
							this.engine.getChar(2).charBox().SetHeight(107);
							rec2.setX(engine.getChar(2).positionX());

							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
						}
						else {
							if (this.engine.getChar(2).getVSpeed()>0){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1_rev.png")))));
								this.engine.getChar(2).charBox().SetLength(41);
								this.engine.getChar(2).charBox().SetHeight(140);
								rec2.setX(engine.getChar(2).positionX());

								rec2.setHeight(engine.getChar(2).charBox().Height());
								rec2.setWidth(engine.getChar(2).charBox().Length());
							}
							else if (this.engine.getChar(2).getVSpeed()<0){
								rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2_rev.png")))));
								this.engine.getChar(2).charBox().SetLength(42);
								this.engine.getChar(2).charBox().SetHeight(115);	
								rec2.setX(engine.getChar(2).positionX());

								rec2.setHeight(engine.getChar(2).charBox().Height());
								rec2.setWidth(engine.getChar(2).charBox().Length());
							}
							
							else{
								if (c2==Commande.NEUTRAL){
									wcycle2=0;
									rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle_rev.png")))));
									this.engine.getChar(2).charBox().SetLength(30);
									rec2.setX(engine.getChar(2).positionX());

									rec2.setHeight(engine.getChar(2).charBox().Height());
									rec2.setWidth(engine.getChar(2).charBox().Length());
									
								}
								if ((c2==Commande.LEFT)||(c2==Commande.RIGHT)){
									if ((wcycle2%12)<6){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1_rev.png")))));
										this.engine.getChar(2).charBox().SetLength(48);
										rec2.setX(engine.getChar(2).positionX());

										rec2.setHeight(engine.getChar(2).charBox().Height());
										rec2.setWidth(engine.getChar(2).charBox().Length());
									}	
									else{
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2_rev.png")))));
										this.engine.getChar(2).charBox().SetLength(48);
										rec2.setX(engine.getChar(2).positionX());

										rec2.setHeight(engine.getChar(2).charBox().Height());
										rec2.setWidth(engine.getChar(2).charBox().Length());
									}
								}
								
								if (c2==Commande.CROUCH){
									rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching_rev.png")))));
									this.engine.getChar(2).charBox().SetLength(61);
									this.engine.getChar(2).charBox().SetHeight(78);
									rec2.setX(engine.getChar(2).positionX());

									rec2.setHeight(engine.getChar(2).charBox().Height());
									rec2.setWidth(engine.getChar(2).charBox().Length());
								}
							}
						}
					}
				}
			} catch (Exception e){
				System.out.println("no care");
			}
			
			rec.setY(engine.getChar(1).positionY());

			
			rechit.setX(engine.getChar(1).charBox().PositionX());
			rechit.setY(engine.getChar(1).charBox().PositionY());
			rechit.setHeight(engine.getChar(1).charBox().Height());
			rechit.setWidth(engine.getChar(1).charBox().Length());
	
		
	

			rec2.setY(engine.getChar(2).positionY());

			
			rechit2.setX(engine.getChar(2).charBox().PositionX());
			rechit2.setY(engine.getChar(2).charBox().PositionY());
			rechit2.setHeight(engine.getChar(2).charBox().Height());
			rechit2.setWidth(engine.getChar(2).charBox().Length());
			rechit.setX(engine.getChar(1).charBox().PositionX());
			rechit.setY(engine.getChar(1).charBox().PositionY());
			rechit.setHeight(engine.getChar(1).charBox().Height());
			rechit.setWidth(engine.getChar(1).charBox().Length());
			
			
			
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
