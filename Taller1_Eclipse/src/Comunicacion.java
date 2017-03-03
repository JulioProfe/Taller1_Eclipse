import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {

	private final int Puerto = 5000;
	private DatagramSocket ds;
	private String shi = "";

	public Comunicacion() {
		// TODO Auto-generated constructor stub
		try {
			ds = new DatagramSocket(Puerto);
			System.out.println("ABRIÓ");
		} catch (SocketException e) {
			// TODO: handle exception
		}
	}

	
	@Override
	public void run() {
		while (true) {
			DatagramPacket paquete = recibir();
			if (paquete != null) {
				String mensaje = new String(paquete.getData(), 0, paquete.getLength());
				shi = mensaje;
				System.out.println("RECIBIDO: " + shi);
				setChanged();
				notifyObservers(shi);
				clearChanged();
			} else
				System.out.println("NO RECIBIDO");
		}
	}

	public String comando() {
		return this.shi;
	}

	private DatagramPacket recibir() {
		try {
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			ds.receive(packet);
			return packet;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}