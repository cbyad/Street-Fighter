package com.cps.display;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.cps.services.engine.EngineImpl;
import com.cps.services.engine.Commande;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
	public boolean p1tech2;
	public boolean p2tech2;
	public boolean p1tech3;
	public boolean p2tech3;
	public AssetDisplay rec;
	public AssetDisplay rec2;
	public AssetDisplay rechit;
	public AssetDisplay rechit2;
	public AssetDisplay rectech1;
	public AssetDisplay rectech2;
	public AssetDisplay lifebox1;
	public AssetDisplay lifebox2;
	public Scene scene;
	public EngineImpl engine;
	
	private int wcycle1;
	private int wcycle2;
	
	private int techcycle1[];
	private int techcycle2[];
	
	private String tech1;
	private String tech2;
	
	public realTimeDealer(AssetDisplay rec, AssetDisplay rec2, AssetDisplay rechit, AssetDisplay rechit2, AssetDisplay rectech1, AssetDisplay rectech2, AssetDisplay lifebox1, AssetDisplay lifebox2, Scene scene, EngineImpl engine){
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
		this.p1tech2=false;
		this.p2tech2=false;
		this.p1tech3=false;
		this.p2tech3=false;
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
		this.rectech1=rectech1;
		this.rectech2=rectech2;
		this.lifebox1=lifebox1;
		this.lifebox2=lifebox2;
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
			
			if (p1tech2){
				c1=Commande.KICK;
				this.tech1="kick";
			}
			
			if (p2tech2){
				c2=Commande.KICK;
				this.tech2="kick";
			}
			
			if (p1tech3){
				c1=Commande.SPECIAL;
				this.tech1="special";
			}
			
			if (p2tech3){
				c2=Commande.SPECIAL;
				this.tech2="special";
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
			
			if (p1tech2){
				techcycle1[0]=engine.getChar(1).tech().sframe();
				techcycle1[1]=engine.getChar(1).tech().hframe();
				techcycle1[2]=engine.getChar(1).tech().rframe();
				p1tech2=false;
			}
			
			if (p2tech2){
				techcycle2[0]=engine.getChar(2).tech().sframe();
				techcycle2[1]=engine.getChar(2).tech().hframe();
				techcycle2[2]=engine.getChar(2).tech().rframe();
				p2tech2=false;
			}
			
			if (p1tech3){
				techcycle1[0]=engine.getChar(1).tech().sframe();
				techcycle1[1]=engine.getChar(1).tech().hframe();
				techcycle1[2]=engine.getChar(1).tech().rframe();
				p1tech3=false;
			}
			
			if (p2tech3){
				techcycle2[0]=engine.getChar(2).tech().sframe();
				techcycle2[1]=engine.getChar(2).tech().hframe();
				techcycle2[2]=engine.getChar(2).tech().rframe();
				p2tech3=false;
			}

			try{

				if (engine.getChar(1).faceRight()){
					if (engine.getChar(1).dead()){
						rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/dead.png")))));
						this.engine.getChar(1).charBox().SetLength(123);
						this.engine.getChar(1).charBox().SetHeight(35);
						rec.setX(engine.getChar(1).positionX()-105);
						rec.setY(engine.getChar(1).positionY()+79);
						rec.setHeight(engine.getChar(1).charBox().Height());
						rec.setWidth(engine.getChar(1).charBox().Length());
						rectech1.setStrokeWidth(0);
						
					}
					else{
						if (this.engine.getChar(1).isBlockstunned()){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/blockstunned.png")))));
							this.engine.getChar(1).charBox().SetLength(64);
							this.engine.getChar(1).charBox().SetHeight(103);
							rec.setX(engine.getChar(1).positionX());
	
							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
							rectech1.setStrokeWidth(0);
						}
						else if (this.engine.getChar(1).isHitstunned()){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/hitstunned.png")))));
							this.engine.getChar(1).charBox().SetLength(77);
							this.engine.getChar(1).charBox().SetHeight(100);
							rec.setX(engine.getChar(1).positionX());
	
							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
							rectech1.setStrokeWidth(0);
						}
						else {
							if (this.engine.getChar(1).isTeching()){
								if (techcycle1[0]>0){
									if (tech1=="punch"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching1.png")))));
										this.engine.getChar(1).charBox().SetLength(68);
										this.engine.getChar(1).charBox().SetHeight(100);
										rec.setX(engine.getChar(1).positionX());
									}
									else if (tech1=="kick"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/kick1.png")))));
										this.engine.getChar(1).charBox().SetLength(46);
										this.engine.getChar(1).charBox().SetHeight(107);
										rec.setX(engine.getChar(1).positionX());
									}
									else if (tech1=="special"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/special1.png")))));
										this.engine.getChar(1).charBox().SetLength(50);
										this.engine.getChar(1).charBox().SetHeight(101);
										rec.setX(engine.getChar(1).positionX());
									}
									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									techcycle1[0]--;
									rectech1.setStrokeWidth(0);
								}
								else {
									if (techcycle1[1]>0){
										if (tech1=="punch"){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching2.png")))));
											this.engine.getChar(1).charBox().SetLength(68);
											this.engine.getChar(1).charBox().SetHeight(97);
											rec.setX(engine.getChar(1).positionX());
			
											rec.setHeight(92);
											rec.setWidth(97);
										}
										
										else if (tech1=="kick"){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/kick2.png")))));
											this.engine.getChar(1).charBox().SetLength(47);
											this.engine.getChar(1).charBox().SetHeight(111);
											rec.setX(engine.getChar(1).positionX());
			
											rec.setHeight(91);
											rec.setWidth(111);
										}
										else if (tech1=="special"){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/special2.png")))));
											this.engine.getChar(1).charBox().SetLength(51);
											this.engine.getChar(1).charBox().SetHeight(106);
											rec.setX(engine.getChar(1).positionX());
			
											rec.setHeight(106);
											rec.setWidth(71);
										}
										techcycle1[1]--;
										rectech1.setX(engine.getChar(1).tech().getHitBox().PositionX());
										rectech1.setY(engine.getChar(1).tech().getHitBox().PositionY());
										rectech1.setHeight(engine.getChar(1).tech().getHitBox().Height());
										rectech1.setWidth(engine.getChar(1).tech().getHitBox().Length());
										rectech1.setStroke(Color.GREEN);
										rectech1.setStrokeWidth(2);
									}
									else {
										if (techcycle1[2]>0){
											if (tech1=="punch"){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching3.png")))));
												this.engine.getChar(1).charBox().SetLength(65);
												this.engine.getChar(1).charBox().SetHeight(107);
												rec.setX(engine.getChar(1).positionX());
											}
											else if (tech1=="kick"){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/kick3.png")))));
												this.engine.getChar(1).charBox().SetLength(77);
												this.engine.getChar(1).charBox().SetHeight(103);
												rec.setX(engine.getChar(1).positionX());
											}
											else if (tech1=="special"){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/special3.png")))));
												this.engine.getChar(1).charBox().SetLength(65);
												this.engine.getChar(1).charBox().SetHeight(125);
												rec.setX(engine.getChar(1).positionX());
											}
											rec.setHeight(engine.getChar(1).charBox().Height());
											rec.setWidth(engine.getChar(1).charBox().Length());
											techcycle1[2]--;
											rectech1.setStrokeWidth(0);
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
									rectech1.setStrokeWidth(0);
								}
								
								else {
									
									if (this.engine.getChar(1).getVSpeed()>0){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1.png")))));
										this.engine.getChar(1).charBox().SetLength(41);
										this.engine.getChar(1).charBox().SetHeight(140);
										rec.setX(engine.getChar(1).positionX());
		
										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
										rectech1.setStrokeWidth(0);
									}
									else if (this.engine.getChar(1).getVSpeed()<0){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2.png")))));
										this.engine.getChar(1).charBox().SetLength(42);
										this.engine.getChar(1).charBox().SetHeight(115);	
										rec.setX(engine.getChar(1).positionX());
		
										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
										rectech1.setStrokeWidth(0);
									}
									
									else{
										if (c1==Commande.NEUTRAL){
											wcycle1=0;
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle.png")))));
											this.engine.getChar(1).charBox().SetLength(30);
											this.engine.getChar(1).charBox().SetHeight(114);
											rec.setX(engine.getChar(1).positionX());
		
											rec.setHeight(engine.getChar(1).charBox().Height());
											rec.setWidth(engine.getChar(1).charBox().Length());
											rectech1.setStrokeWidth(0);
											
										}
										if ((c1==Commande.LEFT)||(c1==Commande.RIGHT)){
											if ((wcycle1%12)<6){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1.png")))));
												this.engine.getChar(1).charBox().SetLength(48);
												this.engine.getChar(1).charBox().SetHeight(114);
												rec.setX(engine.getChar(1).positionX());
		
												rec.setHeight(engine.getChar(1).charBox().Height());
												rec.setWidth(engine.getChar(1).charBox().Length());
												rectech1.setStrokeWidth(0);
											}	
											else{
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2.png")))));
												this.engine.getChar(1).charBox().SetLength(48);
												this.engine.getChar(1).charBox().SetHeight(114);
												rec.setX(engine.getChar(1).positionX());
		
												rec.setHeight(engine.getChar(1).charBox().Height());
												rec.setWidth(engine.getChar(1).charBox().Length());
												rectech1.setStrokeWidth(0);
											}
										}
										
										if (c1==Commande.CROUCH){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching.png")))));
											this.engine.getChar(1).charBox().SetLength(61);
											this.engine.getChar(1).charBox().SetHeight(78);
											rec.setX(engine.getChar(1).positionX());
		
											rec.setHeight(engine.getChar(1).charBox().Height());
											rec.setWidth(engine.getChar(1).charBox().Length());
											rectech1.setStrokeWidth(0);
										}
									}
								}
							}
						}
						rec.setY(engine.getChar(1).positionY());
					}
				}
				else {
					if (engine.getChar(1).dead()){
						rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/dead_rev.png")))));
						this.engine.getChar(1).charBox().SetLength(123);
						this.engine.getChar(1).charBox().SetHeight(35);
						rec.setX(engine.getChar(1).positionX());
						rec.setY(engine.getChar(1).positionY()+79);
						rec.setHeight(engine.getChar(1).charBox().Height());
						rec.setWidth(engine.getChar(1).charBox().Length());
						rectech1.setStrokeWidth(0);
					
					}
					else{
						if (this.engine.getChar(1).isBlockstunned()){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/blockstunned_rev.png")))));
							this.engine.getChar(1).charBox().SetLength(64);
							this.engine.getChar(1).charBox().SetHeight(103);
							rec.setX(engine.getChar(1).positionX());
	
							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
							rectech1.setStrokeWidth(0);
						}
						else if (this.engine.getChar(1).isHitstunned()){
							rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/hitstunned_rev.png")))));
							this.engine.getChar(1).charBox().SetLength(77);
							this.engine.getChar(1).charBox().SetHeight(100);
							rec.setX(engine.getChar(1).positionX());
	
							rec.setHeight(engine.getChar(1).charBox().Height());
							rec.setWidth(engine.getChar(1).charBox().Length());
							rectech1.setStrokeWidth(0);
						}
						else {
							if (this.engine.getChar(1).isTeching()){
								if (techcycle1[0]>0){
									if (tech1=="punch"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(68);
										this.engine.getChar(1).charBox().SetHeight(100);
										rec.setX(engine.getChar(1).positionX());
									}
									else if (tech1=="kick"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/kick1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(46);
										this.engine.getChar(1).charBox().SetHeight(107);
										rec.setX(engine.getChar(1).positionX());
									}
									else if (tech1=="special"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/special1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(50);
										this.engine.getChar(1).charBox().SetHeight(101);
										rec.setX(engine.getChar(1).positionX());
									}
									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									techcycle1[0]--;
									rectech1.setStrokeWidth(0);
								}
								else {
									if (techcycle1[1]>0){
										if (tech1=="punch"){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching2_rev.png")))));
											this.engine.getChar(1).charBox().SetLength(68);
											this.engine.getChar(1).charBox().SetHeight(97);
											rec.setX(engine.getChar(1).positionX()-39);
			
											rec.setHeight(92);
											rec.setWidth(97);
										}
										
										else if (tech1=="kick"){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/kick2_rev.png")))));
											this.engine.getChar(1).charBox().SetLength(47);
											this.engine.getChar(1).charBox().SetHeight(111);
											rec.setX(engine.getChar(1).positionX()-44);
			
											rec.setHeight(91);
											rec.setWidth(111);
										}
										else if (tech1=="special"){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/special2_rev.png")))));
											this.engine.getChar(1).charBox().SetLength(51);
											this.engine.getChar(1).charBox().SetHeight(105);
											rec.setX(engine.getChar(1).positionX()-19);
			
											rec.setHeight(106);
											rec.setWidth(71);
										}
										techcycle1[1]--;
										rectech1.setX(engine.getChar(1).tech().getHitBox().PositionX());
										rectech1.setY(engine.getChar(1).tech().getHitBox().PositionY());
										rectech1.setHeight(engine.getChar(1).tech().getHitBox().Height());
										rectech1.setWidth(engine.getChar(1).tech().getHitBox().Length());
										rectech1.setStroke(Color.GREEN);
										rectech1.setStrokeWidth(2);
									}
									else {
										if (techcycle1[2]>0){
											if (tech1=="punch"){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/punching3_rev.png")))));
												this.engine.getChar(1).charBox().SetLength(65);
												this.engine.getChar(1).charBox().SetHeight(107);
												rec.setX(engine.getChar(1).positionX());
											}
											else if (tech1=="kick"){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/kick3_rev.png")))));
												this.engine.getChar(1).charBox().SetLength(77);
												this.engine.getChar(1).charBox().SetHeight(103);
												rec.setX(engine.getChar(1).positionX());
											}
											else if (tech1=="special"){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/special3_rev.png")))));
												this.engine.getChar(1).charBox().SetLength(65);
												this.engine.getChar(1).charBox().SetHeight(125);
												rec.setX(engine.getChar(1).positionX());
											}
											rec.setHeight(engine.getChar(1).charBox().Height());
											rec.setWidth(engine.getChar(1).charBox().Length());
											techcycle1[2]--;
											rectech1.setStrokeWidth(0);
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
									rectech1.setStrokeWidth(0);
								}
								else {
									if (this.engine.getChar(1).getVSpeed()>0){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(41);
										this.engine.getChar(1).charBox().SetHeight(140);
										rec.setX(engine.getChar(1).positionX());
		
										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
										rectech1.setStrokeWidth(0);
									}
									else if (this.engine.getChar(1).getVSpeed()<0){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/jump2_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(42);
										this.engine.getChar(1).charBox().SetHeight(115);
										rec.setX(engine.getChar(1).positionX());
		
										rec.setHeight(engine.getChar(1).charBox().Height());
										rec.setWidth(engine.getChar(1).charBox().Length());
										rectech1.setStrokeWidth(0);
									}
									
									else{
										if (c1==Commande.NEUTRAL){
											wcycle1=0;
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/idle_rev.png")))));
											this.engine.getChar(1).charBox().SetLength(30);
											this.engine.getChar(1).charBox().SetHeight(114);
											rec.setX(engine.getChar(1).positionX());
		
											rec.setHeight(engine.getChar(1).charBox().Height());
											rec.setWidth(engine.getChar(1).charBox().Length());
											rectech1.setStrokeWidth(0);
											
										}
										if ((c1==Commande.LEFT)||(c1==Commande.RIGHT)){
											if ((wcycle1%12)<6){
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk1_rev.png")))));
												this.engine.getChar(1).charBox().SetLength(48);
												this.engine.getChar(1).charBox().SetHeight(114);
												rec.setX(engine.getChar(1).positionX());
		
												rec.setHeight(engine.getChar(1).charBox().Height());
												rec.setWidth(engine.getChar(1).charBox().Length());
												rectech1.setStrokeWidth(0);
											}	
											else{
												rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/walk2_rev.png")))));
												this.engine.getChar(1).charBox().SetLength(48);
												this.engine.getChar(1).charBox().SetHeight(114);
												rec.setX(engine.getChar(1).positionX());
		
												rec.setHeight(engine.getChar(1).charBox().Height());
												rec.setWidth(engine.getChar(1).charBox().Length());
												rectech1.setStrokeWidth(0);
											}
										}
										if (c1==Commande.CROUCH){
											rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/jackie/crouching_rev.png")))));
											this.engine.getChar(1).charBox().SetLength(61);
											this.engine.getChar(1).charBox().SetHeight(78);
											rec.setX(engine.getChar(1).positionX());
		
											rec.setHeight(engine.getChar(1).charBox().Height());
											rec.setWidth(engine.getChar(1).charBox().Length());
											rectech1.setStrokeWidth(0);
										}
									}
								}
							}
						}
						rec.setY(engine.getChar(1).positionY());
					}
				}
				
				if (!engine.getChar(2).faceRight()){
					if (engine.getChar(2).dead()){
						rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/dead_rev.png")))));
						this.engine.getChar(2).charBox().SetLength(104);
						this.engine.getChar(2).charBox().SetHeight(24);
						rec2.setX(engine.getChar(2).positionX());
						rec2.setY(engine.getChar(2).positionY()+75);
						rec2.setHeight(engine.getChar(2).charBox().Height());
						rec2.setWidth(engine.getChar(2).charBox().Length());
						rectech2.setStrokeWidth(0);

					}
					else{
						if (this.engine.getChar(2).isBlockstunned()){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/blockstunned_rev.png")))));
							this.engine.getChar(2).charBox().SetLength(53);
							this.engine.getChar(2).charBox().SetHeight(99);
							rec2.setX(engine.getChar(2).positionX());
	
							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
							rectech2.setStrokeWidth(0);
						}
						else if (this.engine.getChar(2).isHitstunned()){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/hitstunned_rev.png")))));
							this.engine.getChar(2).charBox().SetLength(52);
							this.engine.getChar(2).charBox().SetHeight(75);
							rec2.setX(engine.getChar(2).positionX());
	
							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
							rectech2.setStrokeWidth(0);
						}
						else {
							if (this.engine.getChar(2).isTeching()){
								if (techcycle1[0]>0){
									if (tech1=="punch"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/punch1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(38);
										this.engine.getChar(1).charBox().SetHeight(64);
										rec.setX(engine.getChar(1).positionX());
									}
									else if (tech1=="kick"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/kick1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(67);
										this.engine.getChar(1).charBox().SetHeight(97);
										rec.setX(engine.getChar(1).positionX());
									}
									else if (tech1=="special"){
										rec.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/special1_rev.png")))));
										this.engine.getChar(1).charBox().SetLength(57);
										this.engine.getChar(1).charBox().SetHeight(90);
										rec.setX(engine.getChar(1).positionX());
									}
									rec.setHeight(engine.getChar(1).charBox().Height());
									rec.setWidth(engine.getChar(1).charBox().Length());
									techcycle1[0]--;
									rectech1.setStrokeWidth(0);
								}
								else {
									if (techcycle2[1]>0){
										if (tech2=="punch"){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/punch2_rev.png")))));
											this.engine.getChar(2).charBox().SetLength(33);
											this.engine.getChar(2).charBox().SetHeight(61);
											rec2.setX(engine.getChar(2).positionX()-29);
			
											rec2.setHeight(61);
											rec2.setWidth(67);
										}
										
										else if (tech2=="kick"){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/kick2_rev.png")))));
											this.engine.getChar(2).charBox().SetLength(46);
											this.engine.getChar(2).charBox().SetHeight(90);
											rec2.setX(engine.getChar(2).positionX()-45);
			
											rec2.setHeight(90);
											rec2.setWidth(92);
										}
										else if (tech2=="special"){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/special2_rev.png")))));
											this.engine.getChar(2).charBox().SetLength(43);
											this.engine.getChar(2).charBox().SetHeight(97);
											rec2.setX(engine.getChar(2).positionX()-55);
			
											rec2.setHeight(97);
											rec2.setWidth(74);
										}
										techcycle2[1]--;
										rectech2.setX(engine.getChar(2).tech().getHitBox().PositionX());
										rectech2.setY(engine.getChar(2).tech().getHitBox().PositionY());
										rectech2.setHeight(engine.getChar(2).tech().getHitBox().Height());
										rectech2.setWidth(engine.getChar(2).tech().getHitBox().Length());
										rectech2.setStroke(Color.YELLOW);
										rectech2.setStrokeWidth(2);
									}
									else {
										if (techcycle2[2]>0){
											if (tech2=="punch"){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/punch3_rev.png")))));
												this.engine.getChar(2).charBox().SetLength(38);
												this.engine.getChar(2).charBox().SetHeight(64);
												rec2.setX(engine.getChar(2).positionX());
											}
											else if (tech2=="kick"){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/kick3_rev.png")))));
												this.engine.getChar(2).charBox().SetLength(34);
												this.engine.getChar(2).charBox().SetHeight(98);
												rec2.setX(engine.getChar(2).positionX());
											}
											else if (tech2=="special"){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/special3_rev.png")))));
												this.engine.getChar(2).charBox().SetLength(70);
												this.engine.getChar(2).charBox().SetHeight(91);
												rec2.setX(engine.getChar(2).positionX());
											}
											rec2.setHeight(engine.getChar(2).charBox().Height());
											rec2.setWidth(engine.getChar(2).charBox().Length());
											techcycle2[2]--;
											rectech2.setStrokeWidth(0);
										}
									}
								}
							}
							
							else {
									if (this.engine.getChar(2).isBlocking()){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/blocking_rev.png")))));
										this.engine.getChar(2).charBox().SetLength(64);
										this.engine.getChar(2).charBox().SetHeight(99);
										rec2.setX(engine.getChar(2).positionX());
			
										rec2.setHeight(engine.getChar(2).charBox().Height());
										rec2.setWidth(engine.getChar(2).charBox().Length());
										rectech2.setStrokeWidth(0);
									}
									else {
										if (this.engine.getChar(2).getVSpeed()>0){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/jumping1_rev.png")))));
											this.engine.getChar(2).charBox().SetLength(47);
											this.engine.getChar(2).charBox().SetHeight(76);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(engine.getChar(2).charBox().Height());
											rec2.setWidth(engine.getChar(2).charBox().Length());
											rectech2.setStrokeWidth(0);
										}
										else if (this.engine.getChar(2).getVSpeed()<0){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/jumping2_rev.png")))));
											this.engine.getChar(2).charBox().SetLength(76);
											this.engine.getChar(2).charBox().SetHeight(115);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(engine.getChar(2).charBox().Height());
											rec2.setWidth(engine.getChar(2).charBox().Length());
											rectech2.setStrokeWidth(0);
										}
										
										else{
											if (c2==Commande.NEUTRAL){
												wcycle2=0;
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/idle_rev.png")))));
												this.engine.getChar(2).charBox().SetLength(46);
												this.engine.getChar(2).charBox().SetHeight(99);
												rec2.setX(engine.getChar(2).positionX());
			
												rec2.setHeight(engine.getChar(2).charBox().Height());
												rec2.setWidth(engine.getChar(2).charBox().Length());
												rectech2.setStrokeWidth(0);
											}
											if ((c2==Commande.LEFT)||(c2==Commande.RIGHT)){
												if ((wcycle2%12)<6){
													rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/walk1_rev.png")))));
													this.engine.getChar(2).charBox().SetLength(59);
													this.engine.getChar(2).charBox().SetHeight(99);
													rec2.setX(engine.getChar(2).positionX());
			
													rec2.setHeight(engine.getChar(2).charBox().Height());
													rec2.setWidth(engine.getChar(2).charBox().Length());
													rectech2.setStrokeWidth(0);
												}	
												else{
													rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/walk2_rev.png")))));
													this.engine.getChar(2).charBox().SetLength(58);
													this.engine.getChar(2).charBox().SetHeight(99);
													rec2.setX(engine.getChar(2).positionX());
			
													rec2.setHeight(engine.getChar(2).charBox().Height());
													rec2.setWidth(engine.getChar(2).charBox().Length());
													rectech2.setStrokeWidth(0);
												}
											}
											
											if (c2==Commande.CROUCH){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/crouching_rev.png")))));
												this.engine.getChar(2).charBox().SetLength(38);
												this.engine.getChar(2).charBox().SetHeight(64);
												rec2.setX(engine.getChar(2).positionX());
			
												rec2.setHeight(engine.getChar(2).charBox().Height());
												rec2.setWidth(engine.getChar(2).charBox().Length());
												rectech2.setStrokeWidth(0);
											}
										}
									}
								}
							}
							rec2.setY(engine.getChar(2).positionY());
						}
					}
					
				
				else {
					if (engine.getChar(2).dead()){
						rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/dead.png")))));
						this.engine.getChar(2).charBox().SetLength(104);
						this.engine.getChar(2).charBox().SetHeight(24);
						rec2.setX(engine.getChar(2).positionX()-58);
						rec2.setY(engine.getChar(2).positionY()+75);
						rec2.setHeight(engine.getChar(2).charBox().Height());
						rec2.setWidth(engine.getChar(2).charBox().Length());
						rectech2.setStrokeWidth(0);
					}
					else{
						if (this.engine.getChar(2).isBlockstunned()){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/blockstunned.png")))));
							this.engine.getChar(2).charBox().SetLength(53);
							this.engine.getChar(2).charBox().SetHeight(99);
							rec2.setX(engine.getChar(2).positionX());
	
							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
							rectech2.setStrokeWidth(0);
						}
						else if (this.engine.getChar(2).isHitstunned()){
							rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/hitstunned.png")))));
							this.engine.getChar(2).charBox().SetLength(52);
							this.engine.getChar(2).charBox().SetHeight(75);
							rec2.setX(engine.getChar(2).positionX());
	
							rec2.setHeight(engine.getChar(2).charBox().Height());
							rec2.setWidth(engine.getChar(2).charBox().Length());
							rectech2.setStrokeWidth(0);
						}
						else {
							if (this.engine.getChar(2).isTeching()){
								if (techcycle2[0]>0){
									if (tech2=="punch"){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/punch1.png")))));
										this.engine.getChar(2).charBox().SetLength(38);
										this.engine.getChar(2).charBox().SetHeight(64);
										rec2.setX(engine.getChar(2).positionX());
									}
									else if (tech2=="kick"){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/kick1.png")))));
										this.engine.getChar(2).charBox().SetLength(67);
										this.engine.getChar(2).charBox().SetHeight(97);
										rec2.setX(engine.getChar(2).positionX());
									}
									else if (tech2=="special"){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/special1.png")))));
										this.engine.getChar(2).charBox().SetLength(57);
										this.engine.getChar(2).charBox().SetHeight(90);
										rec2.setX(engine.getChar(2).positionX());
									}
									rec2.setHeight(engine.getChar(2).charBox().Height());
									rec2.setWidth(engine.getChar(2).charBox().Length());
									techcycle2[0]--;
									rectech2.setStrokeWidth(0);
								}
								else {
									if (techcycle2[1]>0){
										if (tech2=="punch"){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/punch2.png")))));
											this.engine.getChar(2).charBox().SetLength(33);
											this.engine.getChar(2).charBox().SetHeight(61);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(61);
											rec2.setWidth(67);
										}
										
										else if (tech2=="kick"){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/kick2.png")))));
											this.engine.getChar(2).charBox().SetLength(46);
											this.engine.getChar(2).charBox().SetHeight(90);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(90);
											rec2.setWidth(92);
										}
										else if (tech2=="special"){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/special2.png")))));
											this.engine.getChar(2).charBox().SetLength(43);
											this.engine.getChar(2).charBox().SetHeight(97);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(97);
											rec2.setWidth(74);
										}
										techcycle2[1]--;
										rectech2.setX(engine.getChar(2).tech().getHitBox().PositionX());
										rectech2.setY(engine.getChar(2).tech().getHitBox().PositionY());
										rectech2.setHeight(engine.getChar(2).tech().getHitBox().Height());
										rectech2.setWidth(engine.getChar(2).tech().getHitBox().Length());
										rectech2.setStroke(Color.YELLOW);
										rectech2.setStrokeWidth(2);
									}
									else {
										if (techcycle2[2]>0){
											if (tech2=="punch"){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/punch3.png")))));
												this.engine.getChar(2).charBox().SetLength(38);
												this.engine.getChar(2).charBox().SetHeight(64);
												rec2.setX(engine.getChar(2).positionX());
											}
											else if (tech2=="kick"){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/kick3.png")))));
												this.engine.getChar(2).charBox().SetLength(34);
												this.engine.getChar(2).charBox().SetHeight(98);
												rec2.setX(engine.getChar(2).positionX());
											}
											else if (tech2=="special"){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/special3.png")))));
												this.engine.getChar(2).charBox().SetLength(70);
												this.engine.getChar(2).charBox().SetHeight(91);
												rec2.setX(engine.getChar(2).positionX());
											}
											rec2.setHeight(engine.getChar(2).charBox().Height());
											rec2.setWidth(engine.getChar(2).charBox().Length());
											techcycle2[2]--;
											rectech2.setStrokeWidth(0);
										}
									}
								}
							}
							
							else {
									if (this.engine.getChar(2).isBlocking()){
										rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/blocking.png")))));
										this.engine.getChar(2).charBox().SetLength(64);
										this.engine.getChar(2).charBox().SetHeight(99);
										rec2.setX(engine.getChar(2).positionX());
			
										rec2.setHeight(engine.getChar(2).charBox().Height());
										rec2.setWidth(engine.getChar(2).charBox().Length());
										rectech2.setStrokeWidth(0);
									}
									else {
										if (this.engine.getChar(2).getVSpeed()>0){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/jumping1.png")))));
											this.engine.getChar(2).charBox().SetLength(47);
											this.engine.getChar(2).charBox().SetHeight(76);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(engine.getChar(2).charBox().Height());
											rec2.setWidth(engine.getChar(2).charBox().Length());
											rectech2.setStrokeWidth(0);
										}
										else if (this.engine.getChar(2).getVSpeed()<0){
											rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/jumping2.png")))));
											this.engine.getChar(2).charBox().SetLength(76);
											this.engine.getChar(2).charBox().SetHeight(115);
											rec2.setX(engine.getChar(2).positionX());
			
											rec2.setHeight(engine.getChar(2).charBox().Height());
											rec2.setWidth(engine.getChar(2).charBox().Length());
											rectech2.setStrokeWidth(0);
										}
										
										else{
											if (c2==Commande.NEUTRAL){
												wcycle2=0;
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/idle.png")))));
												this.engine.getChar(2).charBox().SetLength(46);
												this.engine.getChar(2).charBox().SetHeight(99);
												rec2.setX(engine.getChar(2).positionX());
			
												rec2.setHeight(engine.getChar(2).charBox().Height());
												rec2.setWidth(engine.getChar(2).charBox().Length());
												rectech2.setStrokeWidth(0);
											}
											if ((c2==Commande.LEFT)||(c2==Commande.RIGHT)){
												if ((wcycle2%12)<6){
													rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/walk1.png")))));
													this.engine.getChar(2).charBox().SetLength(59);
													this.engine.getChar(2).charBox().SetHeight(99);
													rec2.setX(engine.getChar(2).positionX());
			
													rec2.setHeight(engine.getChar(2).charBox().Height());
													rec2.setWidth(engine.getChar(2).charBox().Length());
													rectech2.setStrokeWidth(0);
												}	
												else{
													rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/walk2.png")))));
													this.engine.getChar(2).charBox().SetLength(58);
													this.engine.getChar(2).charBox().SetHeight(99);
													rec2.setX(engine.getChar(2).positionX());
			
													rec2.setHeight(engine.getChar(2).charBox().Height());
													rec2.setWidth(engine.getChar(2).charBox().Length());
													rectech2.setStrokeWidth(0);
												}
											}
											
											if (c2==Commande.CROUCH){
												rec2.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/elsa/crouching.png")))));
												this.engine.getChar(2).charBox().SetLength(38);
												this.engine.getChar(2).charBox().SetHeight(64);
												rec2.setX(engine.getChar(2).positionX());
			
												rec2.setHeight(engine.getChar(2).charBox().Height());
												rec2.setWidth(engine.getChar(2).charBox().Length());
												rectech2.setStrokeWidth(0);
											}
										}
									}
								}
							}
							rec2.setY(engine.getChar(2).positionY());
						}
				}
			} catch (Exception e){
				System.out.println("no care");
			}
			


			
			rechit.setX(engine.getChar(1).charBox().PositionX());
			rechit.setY(engine.getChar(1).charBox().PositionY());
			rechit.setHeight(engine.getChar(1).charBox().Height());
			rechit.setWidth(engine.getChar(1).charBox().Length());
	
		
	
			rechit2.setX(engine.getChar(2).charBox().PositionX());
			rechit2.setY(engine.getChar(2).charBox().PositionY());
			rechit2.setHeight(engine.getChar(2).charBox().Height());
			rechit2.setWidth(engine.getChar(2).charBox().Length());
			rechit.setX(engine.getChar(1).charBox().PositionX());
			rechit.setY(engine.getChar(1).charBox().PositionY());
			rechit.setHeight(engine.getChar(1).charBox().Height());
			rechit.setWidth(engine.getChar(1).charBox().Length());
			
			
			lifebox1.setWidth(engine.getChar(1).life());
			
			lifebox2.setX(engine.getWidth()-5-engine.getChar(2).life());
			lifebox2.setWidth(engine.getChar(2).life());
			
	
			try{
				Thread.sleep(50);
			} catch (InterruptedException e){
				System.err.println("Issue with sleep");
			}
		}
	}
}
