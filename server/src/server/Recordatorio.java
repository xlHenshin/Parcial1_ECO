package server;

import processing.core.PApplet;

public class Recordatorio{
	
	private String color, texto;
	private int posX, posY;
	private PApplet app;

	public Recordatorio(String color, int posX, int posY, String texto, PApplet app) {
		this.color=color;
		this.posX=posX;
		this.posY=posY;
		this.texto=texto;
		this.app=app;
	}
	
	public void pintarDatos() {
		
		app.fill(255,255,255);
		app.rect(posX, posY +25, 140, 50);
		
		if (color.equals("verde")) {
			
			app.fill(0,255,0);
		}else if (color.equals("amarillo")) {
			
			app.fill(255,255,0);
		}else if (color.equals("rojo")) {
			
			app.fill(255,0,0);
		}else {
			
			app.fill(0);
		}
		app.ellipse(posX, posY, 25, 25);
		
		app.fill(0);
		app.text(texto, posX, posY + 30);
	}
}
