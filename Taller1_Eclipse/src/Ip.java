import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;

public class Ip extends Observable implements Runnable {

	public Ip() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			InetAddress ipHost = InetAddress.getLocalHost();
			String host = ipHost.getHostAddress();
			String[] redes =  host.split("\\.");
			host = redes[0] + "." + redes[1] + "." + redes[2];
			int timeout = 200;
			for (int i = 1; i < 255; i++) {
				String buscar = host + "." + i;
				if (InetAddress.getByName(buscar).isReachable(timeout)) {
					setChanged();
					notifyObservers(buscar);
					clearChanged();
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
