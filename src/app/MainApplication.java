package app;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import server.ServerPc;
import utils.SenalarUtils;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 350, 561);
		frame.getContentPane().add(panel);
		
		JLabel senalarIconLbl = new JLabel("");
		senalarIconLbl.setBounds(76, 20, 200, 200);
		Image logoSeñalarImage = new ImageIcon(MainApplication.class.getResource("/images/se\u00F1alar-logo.png")).getImage();
		panel.setLayout(null);
		
		senalarIconLbl.setIcon(new ImageIcon(SenalarUtils.getScaledImage(logoSeñalarImage, 200, 200)));
		panel.add(senalarIconLbl);
		
		JLabel senalarTitleLbl = new JLabel("Se\u00F1alAR");
		senalarTitleLbl.setForeground(new Color(224, 255, 255));
		senalarTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		senalarTitleLbl.setFont(new Font("Raleway ExtraBold", Font.PLAIN, 45));
		senalarTitleLbl.setBounds(76, 231, 200, 45);
		panel.add(senalarTitleLbl);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InitConnection.main(null);
				frame.dispose();
			}
		});
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(10, 480, 330, 70);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel iniciarLbl = new JLabel("INICIAR CONEXI\u00D3N");
		iniciarLbl.setForeground(new Color(224, 255, 255));
		iniciarLbl.setHorizontalAlignment(SwingConstants.CENTER);
		iniciarLbl.setFont(new Font("Raleway Black", Font.PLAIN, 30));
		iniciarLbl.setBounds(10, 11, 310, 48);
		panel_1.add(iniciarLbl);
		
		JLabel comoUsarLbl = new JLabel("\u00BFC\u00F3mo usar?");
		comoUsarLbl.setFont(new Font("Raleway Black", Font.PLAIN, 40));
		comoUsarLbl.setHorizontalAlignment(SwingConstants.CENTER);
		comoUsarLbl.setBounds(360, 11, 314, 59);
		frame.getContentPane().add(comoUsarLbl);
		
		JLabel instruction2Lbl = new JLabel("<html>2. Abrir Se\u00F1alAR en el celular</html>");
		instruction2Lbl.setFont(new Font("Raleway SemiBold", Font.BOLD, 15));
		instruction2Lbl.setBounds(360, 167, 314, 59);
		frame.getContentPane().add(instruction2Lbl);
		
		JLabel instruction1Lbl = new JLabel("<html>1. Conectar el celular a la misma red WiFi que la PC</html>");
		instruction1Lbl.setFont(new Font("Raleway SemiBold", Font.BOLD, 15));
		instruction1Lbl.setBounds(360, 81, 314, 59);
		frame.getContentPane().add(instruction1Lbl);
		
		JLabel instruction3Lbl = new JLabel("<html>3. Iniciar la conexi\u00F3n en esta aplicaci\u00F3n de PC</html>");
		instruction3Lbl.setFont(new Font("Raleway SemiBold", Font.BOLD, 15));
		instruction3Lbl.setBounds(360, 262, 314, 59);
		frame.getContentPane().add(instruction3Lbl);
		
		JLabel instruction4Lbl = new JLabel("<html>4. En esta ventana se mostrar\u00E1 un c\u00F3digo que se deber\u00E1 ingresar en Se\u00F1alAR</html>");
		instruction4Lbl.setFont(new Font("Raleway SemiBold", Font.BOLD, 15));
		instruction4Lbl.setBounds(360, 370, 314, 59);
		frame.getContentPane().add(instruction4Lbl);
		
		JLabel instruction5Lbl = new JLabel("<html>5. Las se\u00F1as traducidas se empezar\u00E1n a transmitir del celular a trav\u00E9s del micr\u00F3fono de esta PC</html>");
		instruction5Lbl.setFont(new Font("Raleway SemiBold", Font.BOLD, 15));
		instruction5Lbl.setBounds(360, 476, 314, 59);
		frame.getContentPane().add(instruction5Lbl);
	}
}
