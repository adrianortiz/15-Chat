package com.codizer.sockets;

import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.codizer.threads.ThreadReceivesCliente;
import com.codizer.threads.ThreadSendCliente;
import com.codizer.view.ChatUISingleton;

import core.codizer.pojo.Session;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class SocketSingleton {
	
	private ChatUISingleton chatUI = ChatUISingleton.getIsntance();
	private Session session = Session.getIsntace();
	public static final SocketSingleton socket = new SocketSingleton();
	private ExecutorService executor = Executors.newCachedThreadPool();
	private Socket cliente;
	
	/**
	 * Contructor general final para SocketSingleton
	 * Cliente que conecta con un servidor para chat
	 */
	private SocketSingleton() {
		
        try {
        	cliente = new Socket(InetAddress.getByName(session.getIp()), 11111);
    		chatUI.mostrarEstado("Buscando Servidor ...", Color.orange);
        		
        	chatUI.mostrarEstado("Conectado al servidor - " + cliente.getInetAddress().getHostName(), Color.decode("#7BED6D"));
        	chatUI.habilitarTexto(true);
        		
        	executor.execute(new ThreadReceivesCliente(cliente, chatUI));
        	executor.execute(new ThreadSendCliente(cliente, chatUI));
	        	
		} catch (IOException e) {
			e.printStackTrace();
			chatUI.mostrarEstado("Servidor no disponible ...", Color.red);
		}
		
        executor.shutdown();
	}
	
	/**
	 * Getter para obtener la instancia de SocketSingleton
	 * @return SocketSingleton
	 */
	public static SocketSingleton getInstance() {
		return socket;
	}
}
