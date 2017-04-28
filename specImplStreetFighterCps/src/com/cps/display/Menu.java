package com.cps.display;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

public class Menu extends VBox {
	
	
	public Menu() {
		
		Label play = new Label("Jouer");
		
		Button btn1 = new Button("Joueur 1 VS Joueur 2");
		
		Button btn2 = new Button("Joueur  VS IA ");
		
		this.getChildren().addAll(play,btn1,btn2);
		
	}

	
	public static void main(String[] args) {
	}
}
