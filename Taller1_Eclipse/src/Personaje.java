import processing.core.PApplet;
import processing.core.PImage;

public class Personaje {
	private int posX, posY;
	private PImage mono, monoPerfil;
	private PApplet app;
	
	public Personaje(PApplet app, int posX, int posY) {
		// TODO Auto-generated constructor stub
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		
		mono = app.loadImage("personaje.png");
		monoPerfil = app.loadImage("perfil.png");
	}
	
	public void pintarPersonaje(){
		app.imageMode(app.CENTER);
		app.image(mono, posX, posY);
		app.imageMode(app.CORNER);
	}
	
	public void pintarPerfil(){
		app.imageMode(app.CENTER);
		app.image(monoPerfil, posY, posY);
		app.imageMode(app.CORNER);
	}
	
	public void arriba(){
		if (posY <= app.height +10) {
			posY -= 10;
		}
	}
	public void abajo(){
		if (posY <= app.height +10) {
			posY += 10;
		}
	}
	public void derecha(){
		if (posX <= app.width +10) {
			posX += 10;
		}
	}
	public void izq(){
		if (posX <= app.width +10) {
			posX -= 10;
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}