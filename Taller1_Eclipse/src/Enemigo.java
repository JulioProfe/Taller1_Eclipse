import processing.core.PApplet;
import processing.core.PImage;

public class Enemigo {
	private int posX, posY, dir, s;
	private PApplet app;
	private PImage aguila;
	
	public Enemigo(PApplet app, int posX, int posY) {
		// TODO Auto-generated constructor stub
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		s = 40;
		aguila = app.loadImage("enemigo.png");
	}
	
	public void pintarEnemigo(PApplet app){
		app.imageMode(app.CENTER);
		app.image(aguila, posX, posY);
		app.imageMode(app.CORNER);
		moverEnemigo(app);
	}
	
	public void moverEnemigo(PApplet app){
		dir = (int) app.random(0, 4);
		s --;
		if (s == 0) {
			s = 40;
		}
		if (s == 30) {
			posX += dir;
			posY += dir;
		} else if(s == 10){
			posX -= dir;
			posY -= dir;
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	
}