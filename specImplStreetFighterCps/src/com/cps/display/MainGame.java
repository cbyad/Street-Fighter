package com.cps.display;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.cps.services.character.CharacterImpl;
import com.cps.services.engine.Engine;
import com.cps.services.engine.EngineImpl;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.hitbox.HitboxImpl;
import com.cps.services.player.PlayerImpl;
import com.cps.services.engine.Commande;
import com.cps.services.fightChar.FightCharImpl;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainGame extends Application {
	private EngineImpl engine;
	private int hauteur;
	private int largeur;
	private int espacement;
	private realTimeDealer rt;

	public static void main(String[] args) { 
		launch(args); 
	}


	@Override public void start(Stage stage) throws Exception {
		this.largeur=768;
		this.hauteur=384;
		this.espacement=100;
		engine=new EngineImpl();
		PlayerImpl player1=new PlayerImpl();
		PlayerImpl player2=new PlayerImpl();
		FightCharImpl char1=new FightCharImpl();
		FightCharImpl char2=new FightCharImpl();
		HitboxImpl hit1 = new HitboxImpl(); 
		HitboxImpl hit2 = new HitboxImpl();

		player1.init();
		player2.init();
	

		engine.init(this.hauteur, this.largeur, this.espacement, player1, player2);

		InputStream is = Files.newInputStream(Paths.get("res/arenes/airport.gif"));
		InputStream ryu = Files.newInputStream(Paths.get("res/sprites/jackie/idle.png"));
		InputStream guile = Files.newInputStream(Paths.get("res/sprites/jackie/idle.png"));

		hit1.init(largeur/2-espacement/2, hauteur-130, 114, 30);
		hit2.init(largeur/2+espacement/2, hauteur-130, 114, 30);
		char1.init(100, 8, true, engine, hit1, null);
		char2.init(100, 8, false, engine, hit2, null);
		player1.setChar(char1);
		player2.setChar(char2);

		Image img = new Image(is);
		Image imgRyu = new Image(ryu);
		Image imgGuile = new Image(guile);
		guile.close(); ryu.close(); is.close();
		ImageView arene =  new ImageView(img);

		Group group = new Group();

		TestHitboxView rec = new TestHitboxView(player1.getChar().charBox().PositionX(), player1.getChar().charBox().PositionY(),player1.getChar().charBox().Length(), player1.getChar().charBox().Height() ,null,imgRyu);
		TestHitboxView rec2 = new TestHitboxView(player2.getChar().charBox().PositionX(), player2.getChar().charBox().PositionY(),player2.getChar().charBox().Length(), player2.getChar().charBox().Height() ,null,imgGuile);
		TestHitboxView rechit = new TestHitboxView(player1.getChar().charBox().PositionX(), player1.getChar().charBox().PositionY(),player1.getChar().charBox().Length(), player1.getChar().charBox().Height() ,Color.RED,null);
		TestHitboxView rechit2 = new TestHitboxView(player2.getChar().charBox().PositionX(), player2.getChar().charBox().PositionY(),player2.getChar().charBox().Length(), player2.getChar().charBox().Height() ,Color.BLUE,null);
		
		group.getChildren().addAll(arene,rec,rec2,rechit,rechit2);

		Scene scene = new Scene(group, hauteur,largeur);
		
		this.rt=new realTimeDealer(rec, rec2, rechit, rechit2, scene, engine);
		rt.start();
		
		moveRecOnKeyPress(scene);

		stage.setScene(scene);
		stage.setTitle("Destroy the bastards");
		stage.setWidth(largeur);
		stage.setHeight(hauteur);
		stage.setResizable(false);
		stage.show();
	}

	private void moveRecOnKeyPress(Scene scene) {

		scene.setOnKeyPressed(event->{

			switch (event.getCode()) {
			case RIGHT: {
				rt.p1right=true;
			}
			break ;	

			case LEFT: {
				rt.p1left=true;
			} 
			break ;
			
			case DOWN: {
				rt.p1crouch=true;
			} 
			break ;
			
			case UP: {
				rt.p1jump=true;
			}
			break;
			
			case NUMPAD1: {
				rt.p1block=true;
			}
			break;
			
			case NUMPAD2: {
				rt.p1tech1=true;
			}
			break;
			
			case Q: {
				rt.p2left=true;
			}
			break ;	

			case D: {
				rt.p2right=true;
			} 
			break ;
			
			case S: {
				rt.p2crouch=true;
			} 
			break ;
			
			case Z: {
				rt.p2jump=true;
			}
			break;
			
			case H: {
				rt.p2block=true;
			}
			break;
			
			case J: {
				rt.p2tech1=true;
			}
			break;
			
			default:
				break;
			}
			
			
				
	
		
	});
		scene.setOnKeyReleased(event->{

			switch (event.getCode()) {
			case RIGHT: {
				rt.p1right=false;
			}
			break ;	

			case LEFT: {
				rt.p1left=false;
			} 
			break ;
			
			case DOWN: {
				rt.p1crouch=false;
			} 
			break ;

			case UP: {
				rt.p1jump=false;
			}
			break;
			
			case NUMPAD1: {
				rt.p1block=false;
			}
			break;
			
			case NUMPAD2: {
				rt.p1tech1=false;
			}
			break;
			
			case Q: {
				rt.p2left=false;
			}
			break ;	

			case D: {
				rt.p2right=false;
			} 
			break ;
			
			case S: {
				rt.p2crouch=false;
			} 
			break ;
			
			case Z: {
				rt.p2jump=false;
			}
			break;
			
			case H: {
				rt.p2block=false;
			}
			break;
			
			case J: {
				rt.p2tech1=false;
			}
			break;
			
			default:
				break;
			}
			
			
				
	
		
	});
		
	}
}