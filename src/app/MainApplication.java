package app;

import java.awt.EventQueue;

import javax.swing.JFrame;

import server.ServerPc;

public class MainApplication {

	private JFrame frame;
	
	private ServerPc serverPc;

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
	
	private void startServer() {
		this.serverPc = new ServerPc();
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		this.startServer();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
