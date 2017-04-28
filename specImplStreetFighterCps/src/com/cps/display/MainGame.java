package com.cps.display;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import javafx.event.EventHandler;

public class MainGame extends Application {

	private static final int   KEYBOARD_MOVEMENT_DELTA = 15;

	public static void main(String[] args) { 
		launch(args); 
	}


	@Override public void start(Stage stage) throws Exception {

		InputStream is = Files.newInputStream(Paths.get("res/arenes/2.png"));
		InputStream ryu = Files.newInputStream(Paths.get("res/sprites/ryu.gif"));

		Image img = new Image(is);
		Image imgRyu = new Image(ryu);
		ryu.close(); is.close();
		ImageView arene =  new ImageView(img);

		double h = img.getHeight() , w =img.getWidth();
		double hRyu= imgRyu.getHeight() , wRyu = imgRyu.getWidth();

		Group group = new Group();

		TestHitboxView rec = new TestHitboxView(5, (int)(h-hRyu),
				(int)wRyu,(int)hRyu ,Color.RED,imgRyu);

		group.getChildren().addAll(arene,rec);

		Scene scene = new Scene(group, w,h);
		moveRecOnKeyPress(scene, rec);

		stage.setScene(scene);
		stage.setTitle("Street Fighter");
		stage.setWidth(w);
		stage.setHeight(h);
		stage.show();
	}

	private void moveRecOnKeyPress(Scene scene, TestHitboxView rec) {
		scene.setOnKeyPressed(event->{
			
			switch (event.getCode()) {
			case RIGHT: rec.setX(rec.getX()+KEYBOARD_MOVEMENT_DELTA); break;
			case LEFT:  rec.setX(rec.getX()-KEYBOARD_MOVEMENT_DELTA); break;
			default : break ;
			}
		});
		
	}
}
