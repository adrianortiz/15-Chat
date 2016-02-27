package com.codizer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class ServidorUISingleton extends JFrame {

	private static final long serialVersionUID = 1531073045078813303L;
	private static final ServidorUISingleton servidorUi = new ServidorUISingleton();
	
	public JLabel lbChatTitle;
    public JTextField txtMgs;
    public JTextArea txtContainerMsgs;
    
    /**
     * Contructor privado
     */
    private ServidorUISingleton(){
    	
        super("Chat - Administrador");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        super.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				txtMgs.requestFocus();
			}
		});
        
        controllers();
    }
    
    /**
     * Controles de la GUI para ServidorUISingleton
     */
    private void controllers() {
    	
    	lbChatTitle = new JLabel("Administrador");
        lbChatTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbChatTitle.setOpaque(true);
		lbChatTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		add(lbChatTitle, BorderLayout.NORTH);
		
		txtContainerMsgs = new JTextArea();
        txtContainerMsgs.setEditable(false);
        add(new JScrollPane(txtContainerMsgs), BorderLayout.CENTER);
        
        txtMgs = new JTextField();
        txtMgs.setEditable(false);
        add(txtMgs, BorderLayout.SOUTH);
        
        setSize(300, 520);
        // setVisible(true);
	}


    /**
     * Agregar mensajes al contenedor
     * de mensajes
     * 
     * @param mensaje
     */
	public void mostrarMensaje(String mensaje) {
        txtContainerMsgs.append(mensaje + "\n");
    } 
    
	/**
	 * Habilitar campo de texto para enviar mensajes
	 * @param editable
	 */
    public void habilitarTexto(boolean editable) {
        txtMgs.setEditable(editable);
        txtMgs.requestFocus();
    }
    
    /**
     * Mostrar estado de la session del chat
     * @param mensaje
     * @param color
     */
    public void mostrarEstado(String mensaje, Color color) {
    	lbChatTitle.setText(mensaje);
    	lbChatTitle.setBackground(color);
    }
 
    /**
     * Getter para obtener ServidorUISingleton
     * @return ServidorUISingleton
     */
    public static ServidorUISingleton getInstance() {
    	return servidorUi;
    }
    
}
