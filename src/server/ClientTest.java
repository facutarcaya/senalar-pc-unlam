package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket client;
		PrintWriter printwriter;
		
		client = new Socket("192.168.56.1", 12345);  //connect to server
        printwriter = new PrintWriter(client.getOutputStream(),true);
        printwriter.write("Hola|");  //write the message to output stream
        Thread.sleep(2000);
        printwriter.write("Como estas?|"); 
        printwriter.write("Todo bien por suerte|"); 
        //printwriter.write("Como anda la familia?|"); 
        //printwriter.write("Tres tristes tigres tragan trigo en un trigal|"); 
        
        printwriter.flush();
        printwriter.close();
        client.close();  
	}
}
