package com.codizer.threads;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import com.codizer.view.ServidorUISingleton;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public class ThreadSend implements Runnable {
	
	private final ServidorUISingleton servidorUI; 
    private ObjectOutputStream salida;
    private String mensaje;
    private Socket conexion; 
   
    /**
     * Contructor general para ThreadSend
     * Recibe como parametro una conexion y servidorUI
     * @param conexion Socket
     * @param servidorUI ChatUISingleton
     */
    public ThreadSend(Socket conexion, final ServidorUISingleton servidorUI){
        this.conexion = conexion;
        this.servidorUI = servidorUI;
        
        servidorUI.txtMgs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mensaje = event.getActionCommand();
                enviarDatos(mensaje);
                servidorUI.txtMgs.setText("");
            }
        });
    } 
    
    /**
     * Setter para mesajes de un cliente
     * @param mensaje
     */
   private void enviarDatos(String mensaje){
      try {
         salida.writeObject("\n  Administrador:\n  " + mensaje);
         salida.flush(); //flush salida a cliente
         servidorUI.mostrarMensaje("\n  Administrador:\n  " + mensaje);
      } catch (IOException ioException){ 
         servidorUI.mostrarEstado("Desconecado", Color.red);
      }
      
   }

   /**
  	 * Enviar mensajes y agregarlos al chat
  	 * @param mensaje
  	 */
    public void mostrarMensaje(String mensaje) {
        servidorUI.txtContainerMsgs.append(mensaje);
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
