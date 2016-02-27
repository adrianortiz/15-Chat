package com.codizer.threads;

import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.codizer.view.ServidorUISingleton;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class ThreadReceives implements Runnable {
	
	private final ServidorUISingleton servidorUI;
    private String mensaje; 
    private ObjectInputStream entrada;
    private Socket cliente;
     
    /**
     * Contructor general privado para ThreadReceives
     * Recibe parametros de cliente y servidorUI
     * @param cliente Socket
     * @param servidorUI ServidorUISingleton
     */
    public ThreadReceives(Socket cliente, ServidorUISingleton servidorUI) {
       this.cliente = cliente;
       this.servidorUI = servidorUI;
    }  

    /**
     * Mostrar y enviar mensajes a la GUI de chat
     * @param mensaje
     */
    public void mostrarMensaje(String mensaje) {
        servidorUI.txtContainerMsgs.append(mensaje);
    } 
   
    /**
     * Ejecuta Hilo
     */
    public void run() {
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadReceives.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //procesa los mensajes enviados desde el servidor
        //leer el mensaje y mostrarlo 
        do {
            try {
                mensaje = (String) entrada.readObject();
                servidorUI.mostrarMensaje(mensaje);
            } catch (SocketException ex) {
            	
            } catch (EOFException eofException) {
                servidorUI.mostrarEstado("Fin de la conexion", Color.red);
                break;
            } catch (IOException ex) {
                Logger.getLogger(ThreadReceives.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException classNotFoundException) {
                servidorUI.mostrarMensaje("Objeto desconocido");
            }

        } while ( !mensaje.equals("exit") );

        // Cerrar entrada Stream y cliente Socket
        try {
            entrada.close();
            cliente.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        servidorUI.mostrarEstado("Fin de la conexion", Color.red);
        System.exit(0);
    }
}
