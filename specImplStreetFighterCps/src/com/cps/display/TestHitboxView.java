package com.cps.display;

import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class TestHitboxView extends Rectangle {
	
	public int x ,y ,h ,w ;
	
	public TestHitboxView(int x ,int y , int w ,int h,Paint color, Image sprite) {
		super(x,y,w,h);
		if (color!=null){
			this.setStroke(color); // mettre en commentaire pour ne pas voir contour du rectangle
			this.setStrokeWidth(2);
		}
		
		if (sprite!=null){
			this.setFill(new ImagePattern(sprite));
		}
		else {
			try{
				this.setFill(new ImagePattern(new Image(Files.newInputStream(Paths.get("res/sprites/transparent.png")))));
			} catch (Exception e){
				System.err.println("SALUT");
			}
		}
		
		this.x = x;this.y=y ;this.w =w ;this.h=h;
	}


	

}
