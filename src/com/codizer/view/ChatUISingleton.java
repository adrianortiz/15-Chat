package com.codizer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class ChatUISingleton extends JFrame {
	
	private static final long serialVersionUID = -1392201927039102068L;
	private static final ChatUISingleton principalChat = new ChatUISingleton();
	
	public JTextField txtMsg;
    public JTextArea txtContentMsgs;
    public JLabel lbChatTitle;
    
    private Container c = getContentPane();
    
    /**
     * Contructo privado para ChatUISingleton
     */
    private ChatUISingleton(){
    	
        super("Chat - User");
        super.setSize(320, 540);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        super.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				txtMsg.requestFocus();
			}
		});
 
        controllersUI();
    }
    
    /**
     * Controles generales para ChatUISingleton
     */
    private void controllersUI() {
    	
    	c.setLayout(null);
    	
    	lbChatTitle = new JLabel("Chat");
        lbChatTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbChatTitle.setOpaque(true);
		lbChatTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lbChatTitle.setBounds(0, 0, 320, 52);
		c.add(lbChatTitle);
        
        txtContentMsgs = new JTextArea();
        txtContentMsgs.setEditable(false);
        txtContentMsgs.setBounds(0, 52, 320, 420);
        c.add(txtContentMsgs);
        
        txtMsg = new JTextField();
        txtMsg.setEditable(false);
        txtMsg.setBounds(5, 477, 310, 35);
        c.add(txtMsg);
        
        setVisible(true);
	}

    /**
     * Agregar mensajes a txtContentMsgs
     * @param mensaje
     */
	public void mostrarMensaje(String mensaje) {
        txtContentMsgs.append(mensaje + "\n");
    } 
	
	/**
	 * Habilitar txtMsg para enviar mensajes
	 * @param editable
	 */
    public void habilitarTexto(boolean editable) {
        txtMsg.setEditable(editable);
    }
    
    /**
     * Asignar estado de la session de chat
     * @param mensaje
     * @param color
     */
    public void mostrarEstado(String mensaje, Color color) {
    	lbChatTitle.setText(mensaje);
    	lbChatTitle.setBackground(color);
    }
    
    /**
     * Getter para obtener la instancia de ChatUISingleton
     * @return principalChat
     */
    public static ChatUISingleton getIsntance() {
    	return principalChat;
    }
}
