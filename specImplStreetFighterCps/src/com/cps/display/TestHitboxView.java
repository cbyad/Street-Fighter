package com.cps.display;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class TestHitboxView extends Rectangle {
	
	private int x ,y ,h ,w ;
	
	public TestHitboxView(int x ,int y , int w ,int h,Paint color, Image sprite) {
		super(x,y,w,h);
		this.setStroke(color); // mettre en commentaire pour ne pas voir contour du rectangle
		this.setStrokeWidth(2);
		this.setFill(new ImagePattern(sprite));
		this.x = x;this.y=y ;this.w =w ;this.h=h;
	}


	

}
