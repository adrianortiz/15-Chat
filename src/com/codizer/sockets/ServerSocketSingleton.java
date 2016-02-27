package com.codizer.sockets;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.codizer.threads.ThreadReceives;
import com.codizer.threads.ThreadSend;
import com.codizer.view.ChatUISingleton;
import com.codizer.view.ServidorUISingleton;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class ServerSocketSingleton {
	
	public static final ServerSocketSingleton socketServidor = new ServerSocketSingleton();
	private ServidorUISingleton servidorUI = ServidorUISingleton.getInstance();
	private ServerSocket servidor;
	private Socket conexion; //Conectarse al cliente
    
    // Hilo
	private ExecutorService executor = Executors.newCachedThreadPool();
    
	/**
	 * Contructor general final para ServerSocketSingleton
	 * Servidor para el chat
	 */
    private ServerSocketSingleton() {
    	
    	try {
            servidor = new ServerSocket(11111, 100); 
            servidorUI.mostrarEstado("Buscando Usuario...", Color.orange);

            while (true) {
                try {
                    conexion = servidor.accept();
                    servidorUI.mostrarEstado("Servidor - " + conexion.getInetAddress() + " (Conectado)", Color.decode("#7BED6D"));
                    servidorUI.habilitarTexto(true);

                    executor.execute(new ThreadReceives(conexion, servidorUI));
                    executor.execute(new ThreadSend(conexion, servidorUI));
                    
                } catch (IOException ex) {
                    Logger.getLogger(ChatUISingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ChatUISingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        executor.shutdown();
    }
    
    /**
     * Getter para obtener la instancia de ServerSocketSingleton
     * @return socketServidor ServerSocketSingleton
     */
    public static ServerSocketSingleton getInstance() {
    	return socketServidor;
    }
        
}
