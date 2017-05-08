package com.cps.display;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.cps.services.character.CharacterImpl;
import com.cps.services.engine.Engine;
import com.cps.services.engine.EngineContract;
import com.cps.services.engine.EngineImpl;
import com.cps.services.hitbox.Hitbox;
import com.cps.services.hitbox.HitboxContract;
import com.cps.services.hitbox.HitboxImpl;
import com.cps.services.player.PlayerImpl;
import com.cps.services.tech.TechImpl;
import com.cps.services.engine.Commande;
import com.cps.services.fightChar.FightCharContract;
import com.cps.services.fightChar.FightCharImpl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
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

		hit1.init(largeur/2-espacement/2, hauteur-114, 114, 30);
		hit2.init(largeur/2+espacement/2, hauteur-99, 99, 30);
		TechImpl t=new TechImpl();
		t.init(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		char1.init(150, 8, true, engine, hit1, t);
		char2.init(70, 12, false, engine, hit2, t);
		
		char1.setName("jackie");
		char2.setName("elsa");
		player1.setChar(char1);
		player2.setChar(char2);

		Image img = new Image(is);
		Image imgRyu = new Image(ryu);
		Image imgGuile = new Image(guile);
		guile.close(); ryu.close(); is.close();
		ImageView arene =  new ImageView(img);

		Group group = new Group();
		AssetDisplay rec = new AssetDisplay(player1.getChar().charBox().PositionX(), player1.getChar().charBox().PositionY(),player1.getChar().charBox().Length(), player1.getChar().charBox().Height() ,null,imgRyu);
		AssetDisplay rec2 = new AssetDisplay(player2.getChar().charBox().PositionX(), player2.getChar().charBox().PositionY(),player2.getChar().charBox().Length(), player2.getChar().charBox().Height() ,null,imgGuile);
		AssetDisplay rechit = new AssetDisplay(player1.getChar().charBox().PositionX(), player1.getChar().charBox().PositionY(),player1.getChar().charBox().Length(), player1.getChar().charBox().Height() ,Color.RED,null);
		AssetDisplay rechit2 = new AssetDisplay(player2.getChar().charBox().PositionX(), player2.getChar().charBox().PositionY(),player2.getChar().charBox().Length(), player2.getChar().charBox().Height() ,Color.BLUE,null);

		AssetDisplay rectech1 = new AssetDisplay(player1.getChar().charBox().PositionX(), player1.getChar().charBox().PositionY(),player1.getChar().charBox().Length(), player1.getChar().charBox().Height() ,null,null);
		AssetDisplay rectech2 = new AssetDisplay(player2.getChar().charBox().PositionX(), player2.getChar().charBox().PositionY(),player2.getChar().charBox().Length(), player2.getChar().charBox().Height() ,null,null);

		AssetDisplay lifebox1= new AssetDisplay(5,5,100, 20,null,new Image(Files.newInputStream(Paths.get("res/sprites/lifebox.png"))));
		AssetDisplay lifebox2= new AssetDisplay(largeur-105,5,100, 20,null,new Image(Files.newInputStream(Paths.get("res/sprites/lifebox.png"))));
		Label jackie = new Label("Jackie");jackie.setTextFill(Color.WHITE);
		Label elsa = new Label("Elsa",lifebox2);elsa.setTextFill(Color.WHITE);
		jackie.setTranslateX(5);jackie.setTranslateY(5);
		elsa.setTranslateX(largeur-105);elsa.setTranslateY(5);
		
		group.getChildren().addAll(arene,rec,rec2,rechit,rechit2, rectech1, rectech2, lifebox1, lifebox2,jackie,elsa);

		Scene scene = new Scene(group, hauteur,largeur);
		
		this.rt=new realTimeDealer(rec, rec2, rechit, rechit2, rectech1, rectech2, lifebox1, lifebox2, scene, engine);
	
		//rec, rec2, echit, rechit2, rectech1, rectech2, lifebox1, lifebox2,  scene, engine
		moveRecOnKeyPress(scene);
		
//		contrats
//		EngineContract c = new EngineContract(engine);
//		c.init(hauteur, largeur, espacement, engine.getPlayer(1), engine.getPlayer(2));
//		FightCharContract c = new FightCharContract(char1);
//		c.checkInvariant();
		
		Media sound = new Media(new File("res/music/got.mp3").toURI().toString());
		MediaPlayer mp = new MediaPlayer(sound);
		mp.play();
		
		
		stage.setScene(scene);
		stage.setTitle("Destroy the bastards");
		stage.setWidth(largeur);
		stage.setHeight(hauteur);
		//stage.setResizable(false);
		stage.show();
		stage.setOnCloseRequest(e->Platform.exit());
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
			
			case NUMPAD3: {
				rt.p1tech2=true;
			}
			break;
			
			case NUMPAD5: {
				rt.p1tech3=true;
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
			
			case K: {
				rt.p2tech2=true;
			}
			break;
			
			case I: {
				rt.p2tech3=true;
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
			
			case NUMPAD3: {
				rt.p1tech2=false;
			}
			break;
			
			case NUMPAD5:{
				rt.p1tech3=false;
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
			
			case K: {
				rt.p2tech2=false;
			}
			break;
			
			case I: {
				rt.p2tech3=false;
			}
			break;
			
			default:
				break;
			}
			

	});
		
	}
}