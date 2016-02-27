import com.codizer.sockets.ServerSocketSingleton;
import com.codizer.view.MenuUISingleton;

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
		MenuUISingleton menuUI = MenuUISingleton.getInstance();
		menuUI.setVisible(true);
		ServerSocketSingleton socketServidor = ServerSocketSingleton.getInstance();
	}
}