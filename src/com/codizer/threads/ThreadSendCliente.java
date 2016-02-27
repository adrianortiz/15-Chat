package com.codizer.threads;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import com.codizer.view.ChatUISingleton;

/**
 * 
 * @author Adrian
 *
 */
public class ThreadSendCliente implements Runnable {
	
	private final ChatUISingleton chatUI;
    private ObjectOutputStream salida;
    private String mensaje;
    private Socket conexion; 
   
    /**
     * Contructor general para ThreadSendCliente
     * Recibe como parametro una conexion y chatUI
     * @param conexion Socket
     * @param chatUI ChatUISingleton
     */
    public ThreadSendCliente(Socket conexion, final ChatUISingleton chatUI){
        this.conexion = conexion;
        this.chatUI = chatUI;
        
        chatUI.txtMsg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mensaje = event.getActionCommand();
                enviarDatos(mensaje);
                chatUI.txtMsg.setText("");
            }
        });
    } 
    
   /**
    * Setter para mesajes de un cliente
    * @param mensaje
    */
   private void enviarDatos(String mensaje){
      try {
         salida.writeObject("\n  Usuario:\n  " + mensaje);
         salida.flush(); //flush salida a cliente
         chatUI.mostrarMensaje("\n  Usuario:\n  " + mensaje);
      } catch (IOException ioException){ 
         chatUI.mostrarMensaje("\n  Error escribiendo Mensaje");
      } 
      
   }

   	/**
   	 * Enviar mensajes y agregarlos al chat
   	 * @param mensaje
   	 */
    public void mostrarMensaje(String mensaje) {
        chatUI.txtContentMsgs.append(mensaje);
    } 
   
    /**
     * Ejecutar Hilo
     */
    public void run() {
         try {
            salida = new ObjectOutputStream(conexion.getOutputStream());
            salida.flush(); 
        } catch (SocketException ex) {
        } catch (IOException ioException) {
          ioException.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }
}
