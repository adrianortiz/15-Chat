import com.codizer.sockets.ServerSocketSingleton;
import com.codizer.view.MenuUI;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class Main {
	
	/**
	 * Aplicación de Chat con Sockets y Threads
	 * @param args
	 */
	public static void main(String[] args) {
		MenuUI menuUI = MenuUI.getInstance();
		menuUI.setVisible(true);
		ServerSocketSingleton socketServidor = ServerSocketSingleton.getInstance();
	}
}