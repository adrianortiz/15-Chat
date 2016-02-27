package com.codizer.threads;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.codizer.view.ChatUISingleton;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class ThreadReceivesCliente implements Runnable {

	private final ChatUISingleton chatUi;
    private String mensaje; 
    private ObjectInputStream entrada;
    private Socket cliente;
 
    
   /**
    * Constructor general privado para ThreadReceivesCliente
    * Recibe como parametro cliente y chatUi
    * @param cliente Socket
    * @param chatUi ChatUISingleton
    */
     
    public ThreadReceivesCliente(Socket cliente, ChatUISingleton chatUi){
       this.cliente = cliente;
       this.chatUi = chatUi;
    }  

   /**
    * Mostrar mensajes y agregarlos a la vista
    * @param mensaje
    */
    public void mostrarMensaje(String mensaje) {
        chatUi.txtContentMsgs.append(mensaje);
    } 
   
    /**
     * Ejecutar Hilo
     */
    public void run() {
    	
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadReceivesCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //procesa los mensajes enviados desde el servidor
        //leer el mensaje y mostrarlo 
        do {
            try {
                mensaje = (String) entrada.readObject();
                chatUi.mostrarMensaje(mensaje);
                
            } catch (SocketException ex) {
            } catch (EOFException eofException) {
                chatUi.mostrarMensaje("Fin de la conexion");
                break;
            } catch (IOException ex) {
                Logger.getLogger(ThreadReceivesCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException classNotFoundException) {
                chatUi.mostrarMensaje("Desconocido");
            }       

        } while (!mensaje.equals("exit"));

        // Cerrar entrada Stream y cliente Socket
        try {
            entrada.close();
            cliente.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        chatUi.mostrarMensaje("Fin de la conexion");
        
        System.exit(0);
    }
}
