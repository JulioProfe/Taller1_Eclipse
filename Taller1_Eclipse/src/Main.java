import java.net.InetAddress;

import processing.core.PApplet;

public class Main extends PApplet{
	Logica log;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("java.net.preferIPv4Stack", "true");
		PApplet.main("Main");
	}
	
	@Override
	public void settings() {
		// TODO Auto-generated method stub
		size(1200, 700);
	}
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		log = new Logica(this);
		try {
		System.out.println(InetAddress.getLocalHost());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		background(255);
		log.pintar();
	}
	
	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		log.click();
	}
}