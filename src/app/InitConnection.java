package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ServerPc;
import utils.SenalarUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class InitConnection extends JFrame {

	private JPanel contentPane;
	private JPanel waitingConnectionPanel;
	private JPanel connectionEstablishedPanel;
	
	private ServerPc serverPc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitConnection frame = new InitConnection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void startServer() {
		this.serverPc = new ServerPc(this);
	}

	/**
	 * Create the frame.
	 */
	public InitConnection() {
		this.startServer();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		waitingConnectionPanel = new JPanel();
		waitingConnectionPanel.setBackground(new Color(100, 149, 237));
		waitingConnectionPanel.setBounds(0, 0, 684, 561);
		contentPane.add(waitingConnectionPanel);
		waitingConnectionPanel.setLayout(null);
		
		JLabel wifiLogoLbl = new JLabel("");
		Image wifiLogoImage = new ImageIcon(InitConnection.class.getResource("/images/wifilogo.png")).getImage();
		wifiLogoLbl.setIcon(new ImageIcon(SenalarUtils.getScaledImage(wifiLogoImage, 250, 250)));
		wifiLogoLbl.setBounds(217, 44, 250, 250);
		waitingConnectionPanel.add(wifiLogoLbl);
		
		JLabel waitingConnLbl = new JLabel("Esperando Conexi\u00F3n...");
		waitingConnLbl.setFont(new Font("Raleway Medium", Font.BOLD, 30));
		waitingConnLbl.setHorizontalAlignment(SwingConstants.CENTER);
		waitingConnLbl.setBounds(10, 494, 664, 36);
		waitingConnectionPanel.add(waitingConnLbl);
		
		JLabel instruction1Lbl = new JLabel("Ingrese a la app Se\u00F1alAR");
		instruction1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		instruction1Lbl.setFont(new Font("Raleway Medium", Font.BOLD, 25));
		instruction1Lbl.setBounds(10, 286, 664, 36);
		waitingConnectionPanel.add(instruction1Lbl);
		
		JLabel instruction2Lbl = new JLabel("En \"Conectar a PC\" ingrese el siguiente c\u00F3digo:");
		instruction2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		instruction2Lbl.setFont(new Font("Raleway Medium", Font.BOLD, 25));
		instruction2Lbl.setBounds(10, 333, 664, 36);
		waitingConnectionPanel.add(instruction2Lbl);
		
		JLabel ipAddressLbl = new JLabel("Dirección IP");
		ipAddressLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ipAddressLbl.setFont(new Font("Raleway Black", Font.BOLD, 30));
		ipAddressLbl.setBounds(10, 380, 664, 36);
		waitingConnectionPanel.add(ipAddressLbl);
		try {
			ipAddressLbl.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connectionEstablishedPanel = new JPanel();
		connectionEstablishedPanel.setBackground(new Color(100, 149, 237));
		connectionEstablishedPanel.setBounds(0, 0, 684, 561);
		contentPane.add(connectionEstablishedPanel);
		connectionEstablishedPanel.setLayout(null);
		
		JLabel establishedConn = new JLabel("\u00A1Conexi\u00F3n Establecida!");
		establishedConn.setHorizontalAlignment(SwingConstants.CENTER);
		establishedConn.setFont(new Font("Raleway Medium", Font.BOLD, 30));
		establishedConn.setBounds(10, 333, 664, 36);
		connectionEstablishedPanel.add(establishedConn);
		
		JLabel successLogoLbl = new JLabel("");
		Image successLogoImage = new ImageIcon(InitConnection.class.getResource("/images/success.png")).getImage();
		successLogoLbl.setIcon(new ImageIcon(SenalarUtils.getScaledImage(successLogoImage, 250, 250)));
		successLogoLbl.setBounds(217, 44, 250, 250);
		connectionEstablishedPanel.add(successLogoLbl);
		connectionEstablishedPanel.setVisible(false);
	}
	
	public void establishConnection() {
		waitingConnectionPanel.setVisible(false);
		connectionEstablishedPanel.setVisible(true);
	}
}
