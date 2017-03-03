import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica implements Observer {
	private Personaje per;
	private PApplet app;
	private Enemigo enemigo;
	private int puntaje, cambio;
	private Comunicacion com;
	private PImage ob;
	private PFont fuente;
	private Ip ips;
	private Tiempo time;
	private ArrayList<Enemigo> enemigos = new ArrayList<>();
	private ArrayList<Objeto> bananas = new ArrayList<>();
	private PImage inicio, mapa, jugar, jugarDos;

	public Logica(PApplet app) {
		// TODO Auto-generated constructor stub
		this.app = app;
		com = new Comunicacion();
		com.addObserver(this);
		new Thread(com).start();

		ips = new Ip();
		ips.addObserver(this);
		new Thread(ips).start();

		per = new Personaje(app, 70, 80);
		time = new Tiempo();

		time.start();
		inicio = app.loadImage("inicio.png");
		mapa = app.loadImage("mapa.png");
		jugar = app.loadImage("jugar.png");
		jugarDos = app.loadImage("jugarDos.png");
		fuente = app.createFont("Marchand de Venise.ttf", 60);

		puntaje = 0;
		cambio = 0;
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));
		bananas.add(new Objeto(app, (int) app.random(150, 1000), (int) app.random(150, 650)));

		ob = app.loadImage("banana.png");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			String aja = (String) arg;
			System.out.println(aja);
			if (aja.contains("up")) {
				per.arriba();
				// per.pintarPersonaje();
			}
			if (aja.contains("down")) {
				per.abajo();
				// per.pintarPersonaje();
			}
			if (aja.contains("right")) {
				per.derecha();
				// per.pintarPerfil();
			}
			if (aja.contains("left")) {
				per.izq();
				// per.pintarPerfil();
			}
		}
		if (arg instanceof String) {
			String yupi = (String) arg;
			if (yupi.contains(".")) {
				enemigos.add(new Enemigo(app, (int) app.random(120, 1000), (int) app.random(50, 650)));
			}
		}
	}

	public void pintar() {
		switch (cambio) {
		case 0:
			app.image(inicio, 0, 0);
			app.image(jugar, 0, 0);
			if (app.mouseX > 480 && app.mouseX < 775 && app.mouseY > 545 && app.mouseY < 660 && cambio == 0) {
				app.image(jugarDos, 0, 0);
				;
			}
			break;

		case 1:
			app.textFont(fuente);
			time.setCorre(true);
			app.image(mapa, 0, 0);
			per.pintarPersonaje();
			for (int i = 0; i < enemigos.size(); i++) {
				enemigos.get(i).pintarEnemigo(app);
			}

			for (int i = 0; i < bananas.size(); i++) {
				bananas.get(i).pintarObjeto(app);
			}
			for (int i = 0; i < bananas.size(); i++) {
				Objeto objeto = (Objeto) bananas.get(i);
				if (objeto instanceof Objeto && per instanceof Personaje) {
					if (app.dist(objeto.getPosX(), objeto.getPosY(), per.getPosX(), per.getPosY()) < 70) {
						bananas.remove(i);
						puntaje += 100;
					}
				}
			}
			for (int i = 0; i < enemigos.size(); i++) {
				Enemigo ene = (Enemigo) enemigos.get(i);
				if (ene instanceof Enemigo && per instanceof Personaje) {
					if (app.dist(ene.getPosX(), ene.getPosY(), per.getPosX(), per.getPosY()) < 50) {
						per.setPosX(70);
						per.setPosY(80);
						if (puntaje != 0)
							puntaje -= 50;
					}
				}
			}

			int sec = time.getSec();
			app.fill(255);
			app.textSize(50);
			app.text(sec, app.width / 2 - 25, 78);
			// enemigo.pintarEnemigo(app);

			app.fill(255);
			app.textSize(60);
			app.text(puntaje, 1090, 660);

			app.imageMode(app.CENTER);
			ob.resize(42, 25);
			app.image(ob, 1152, 650);
			app.imageMode(app.CORNER);
			break;

		case 2:
			fin();
		}
	}

	private void fin() {
		int sec = time.getSec();

		if (sec > 60 && cambio == 1) {
			cambio = 2;
			time.setCorre(false);
			app.fill(0);
			app.rect(0, 0, app.width, app.height);
			app.fill(255);
			app.textSize(70);
			app.text("FIN DEL JUEGO", app.width / 2, app.height / 2);

			app.fill(255);
			app.textSize(70);
			app.text(puntaje, app.width, app.height + 80);
		}
	}

	public void click() {
		if (app.mouseX > 480 && app.mouseX < 775 && app.mouseY > 545 && app.mouseY < 660 && cambio == 0) {
			cambio = 1;
		}
	}
}