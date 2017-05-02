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
		CharacterImpl char1=new CharacterImpl();
		CharacterImpl char2=new CharacterImpl();
		HitboxImpl hit1 = new HitboxImpl(); 
		HitboxImpl hit2 = new HitboxImpl();

		player1.init();
		player2.init();
	

		engine.init(this.hauteur, this.largeur, this.espacement, player1, player2);

		InputStream is = Files.newInputStream(Paths.get("res/arenes/airport.gif"));
		InputStream ryu = Files.newInputStream(Paths.get("res/sprites/ryu.gif"));
		InputStream guile = Files.newInputStream(Paths.get("res/sprites/guile.gif"));

		hit1.init(largeur/2-espacement/2, hauteur-130, 100, 40);
		hit2.init(largeur/2+espacement/2, hauteur-130, 100, 40);
		char1.init(100, 15, true, engine, hit1);
		char2.init(100, 15, false, engine, hit2);
		player1.setChar(char1);
		player2.setChar(char2);

		Image img = new Image(is);
		Image imgRyu = new Image(ryu);
		Image imgGuile = new Image(guile);
		guile.close(); ryu.close(); is.close();
		ImageView arene =  new ImageView(img);

		Group group = new Group();

		TestHitboxView rec = new TestHitboxView(player1.getChar().charBox().PositionX(), player1.getChar().charBox().PositionY(),player1.getChar().charBox().Length(), player1.getChar().charBox().Height() ,Color.RED,imgRyu);
		TestHitboxView rec2 = new TestHitboxView(player2.getChar().charBox().PositionX(), player2.getChar().charBox().PositionY(),player2.getChar().charBox().Length(), player2.getChar().charBox().Height() ,Color.BLUE,imgGuile);

		group.getChildren().addAll(arene,rec,rec2);

		Scene scene = new Scene(group, hauteur,largeur);
		moveRecOnKeyPress(scene, rec, rec2);

		stage.setScene(scene);
		stage.setTitle("Destroy the bastards");
		stage.setWidth(largeur);
		stage.setHeight(hauteur);
		stage.setResizable(false);
		stage.show();
	}

	private void moveRecOnKeyPress(Scene scene, TestHitboxView rec, TestHitboxView rec2) {

		scene.setOnKeyPressed(event->{

			switch (event.getCode()) {
			case RIGHT: {
				engine.step(Commande.RIGHT, Commande.NEUTRAL);
			}
			break ;	

			case LEFT: {
				engine.step(Commande.LEFT, Commande.NEUTRAL);
			} 
			break ;
			
			case DOWN: {
				engine.step(Commande.CROUCH, Commande.NEUTRAL);
			} 
			break ;

			case Q: {
				engine.step(Commande.NEUTRAL, Commande.LEFT);
			}
			break ;	

			case D: {
				engine.step(Commande.NEUTRAL, Commande.RIGHT);
			} 
			break ;
			
			case S: {
				engine.step(Commande.NEUTRAL, Commande.CROUCH);
			} 
			break ;

			default:
				break;
			}

			rec.setX(engine.getChar(1).positionX());
			rec.setY(engine.getChar(1).positionY());
			rec2.setX(engine.getChar(2).positionX());
			rec2.setY(engine.getChar(2).positionY());
			//System.out.println("x: "+engine.getChar(1).positionX()+" y: "+engine.getChar(1).positionY());
		});

	}
}
