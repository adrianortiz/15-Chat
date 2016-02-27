package com.codizer.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.codizer.sockets.SocketSingleton;

import core.codizer.pojo.Session;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class MenuUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -1379892678952635095L;
	public final static  MenuUI menuUI = new MenuUI();
	private Container c = getContentPane();
	
	private JButton btnServidor;
	private JTextField txtIp;
	private JButton btnCliente;
	
	/**
	 * Contructor privado para MenuUI
	 */
	private MenuUI() {
		
		super.setTitle("Menú");
		super.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setSize(300, 400);
		
		controlesUi();
	}
	
	/**
	 * Controles generales de la GUI
	 */
	private void controlesUi() {
		
		c.setLayout(null);
		
		btnServidor = new JButton("Como servidor");
		btnServidor.setBounds(10, 70, 280, 38);
		
		txtIp = new JTextField();
		txtIp.setBounds(10, 170, 280, 40);
		
		btnCliente = new JButton("Como cliente");
		btnCliente.setBounds(10, 210, 280, 38);
		
		btnServidor.addActionListener(this);
		btnCliente.addActionListener(this);
		
		c.add(btnServidor);
		c.add(txtIp);
		c.add(btnCliente);
		
	}

	/**
	 * Getter para la insancia de MenuUI
	 * @return menuUI
	 */
	public static MenuUI getInstance() {
		return menuUI;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnServidor) {
	    	ServidorUISingleton servidorUI = ServidorUISingleton.getInstance();
	    	servidorUI.setVisible(true);
		}
		
		if (e.getSource() == btnCliente) {
			
			Session session = Session.getIsntace();
			String ip = txtIp.getText();
			
			if (ip.equals("")) {
				session.setIp("127.0.0.1");
			} else {
				session.setIp(txtIp.getText());
			}
			
			SocketSingleton socketSingleton = SocketSingleton.getInstance();
	    	ChatUISingleton chatUI = ChatUISingleton.getIsntance();
	    	chatUI.setVisible(true);
	    	MenuUI.menuUI.setVisible(false);
		}
		
	}
}
