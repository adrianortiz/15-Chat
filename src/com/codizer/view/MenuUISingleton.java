package com.codizer.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.codizer.sockets.SocketSingleton;

import core.codizer.pojo.Session;

/**
 * 
 * @author Adrian Ortiz
 *
 */
public final class MenuUISingleton extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -1379892678952635095L;
	public final static  MenuUISingleton menuUI = new MenuUISingleton();
	private Container c = getContentPane();
	
	private JLabel lbServidor;
	private ImageIcon imageServer;
	private JLabel iconServer;
	private JButton btnServidor;
	
	private JLabel lbCliente;
	private ImageIcon imageCliente;
	private JLabel iconCliente;
	private JLabel lbIp;
	private JTextField txtIp;
	private JButton btnCliente;
	
	/**
	 * Contructor privado para MenuUI
	 */
	private MenuUISingleton() {
		
		super("Menú");
        super.setSize(300, 540);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);
		
		controlesUi();
	}
	
	/**
	 * Controles generales de la GUI
	 */
	private void controlesUi() {
		
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		
		lbServidor = new JLabel("SERVIDOR");
		lbServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lbServidor.setOpaque(true);
		lbServidor.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lbServidor.setBounds(0, 0, 300, 52);
		c.add(lbServidor);
		
		imageServer = new ImageIcon("../15-Chat/src/com/codizer/media/icon-server.png");
		iconServer = new JLabel(imageServer);
		iconServer.setBounds(0, 60, 300, 102);
		c.add(iconServer);
		
		btnServidor = new JButton("Como servidor");
		btnServidor.setBounds(10, 170, 280, 38);
		
		lbCliente = new JLabel("CLIENTE");
		lbCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbCliente.setOpaque(true);
		lbCliente.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lbCliente.setBounds(0, 230, 300, 52);
		c.add(lbCliente);
		
		imageCliente = new ImageIcon("../15-Chat/src/com/codizer/media/icon-user.png");
		iconCliente = new JLabel(imageCliente);
		iconCliente.setBounds(0, 296, 300, 102);
		c.add(iconCliente);
		
		lbIp = new JLabel("IP del servidor:");
		lbIp.setBounds(15, 395, 280, 40);
		
		txtIp = new JTextField();
		txtIp.setBounds(10, 430, 280, 40);
		
		btnCliente = new JButton("Como cliente");
		btnCliente.setBounds(10, 470, 280, 38);
		
		btnServidor.addActionListener(this);
		btnCliente.addActionListener(this);
		
		c.add(btnServidor);
		c.add(lbIp);
		c.add(txtIp);
		c.add(btnCliente);
		
	}

	/**
	 * Getter para la insancia de MenuUI
	 * @return menuUI
	 */
	public static MenuUISingleton getInstance() {
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
				txtIp.setText("127.0.0.1");
			} else {
				session.setIp(txtIp.getText());
			}
			
			SocketSingleton socketSingleton = SocketSingleton.getInstance();
	    	ChatUISingleton chatUI = ChatUISingleton.getIsntance();
	    	chatUI.setVisible(true);
		}
		
	}
}
