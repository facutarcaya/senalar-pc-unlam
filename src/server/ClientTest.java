package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client;
		PrintWriter printwriter;
		
		client = new Socket("192.168.0.115", 12345);  //connect to server
        printwriter = new PrintWriter(client.getOutputStream(),true);
        printwriter.write("Hola|");  //write the message to output stream

        printwriter.flush();
        printwriter.close();
        client.close();  
	}
}
