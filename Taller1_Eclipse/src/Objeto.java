import processing.core.PApplet;
import processing.core.PImage;

public class Objeto {
	private int posX, posY;
	private PApplet app;
	private PImage bananos;
	
	public Objeto(PApplet app, int posX, int posY) {
		// TODO Auto-generated constructor stub
		bananos = app.loadImage("banana.png");
		this.app = app;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void pintarObjeto(PApplet app){
		app.imageMode(app.CENTER);
		app.image(bananos, posX, posY);
		app.imageMode(app.CORNER);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
}