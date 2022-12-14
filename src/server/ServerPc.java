package server;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.sound.midi.Synthesizer;

import app.InitConnection;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import com.sun.speech.freetts.VoiceManager;
import com.darkprograms.speech.synthesiser.SynthesiserV2;
import com.sun.speech.freetts.Voice;

public class ServerPc implements Runnable
{
    private Thread thread;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private InitConnection application;
    
    VoiceManager vocesI;
    Voice voz;

    SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
   
    
    public ServerPc(InitConnection application)
    {
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
        this.application = application;
    }

    @Override
    public void run()
    {
        // create a server socket
        try
        {
            this.serverSocket = new ServerSocket( 12345 );
        }
        catch ( IOException e )
        {
            System.out.println( "failed to start server socket" );
            e.printStackTrace();
        }

        // wait for a connection
        System.out.println( "waiting for connections..." );
        try
        {
            this.socket = serverSocket.accept();
        }
        catch ( IOException e )
        {
            System.out.println( "failed to accept" );
            e.printStackTrace();
        }
        System.out.println( "client connected" );
        this.application.establishConnection();

        // create input and output streams
        try
        {
            this.dataInputStream = new DataInputStream( new BufferedInputStream( this.socket.getInputStream() ) );
            this.dataOutputStream = new DataOutputStream( new BufferedOutputStream( this.socket.getOutputStream() ) );
        }
        catch ( IOException e )
        {
            System.out.println( "failed to create streams" );
            e.printStackTrace();
        }

        // send some test data
        try
        {
            this.dataOutputStream.writeInt( 123 );
            this.dataOutputStream.flush();
        }
        catch ( IOException e )
        {
            System.out.println( "failed to send" );
            e.printStackTrace();
        }
        String word = "";
        String language = "";
        int n=0;
        // placeholder recv loop
        while ( true )
        {
        	
            try
            {
            	
                byte test = this.dataInputStream.readByte();
                char character = (char) (test & 0xFF);
               
                System.out.println( "byte received: "+test );
                //System.out.println( "character received: "+character );
                
                //byte [20] bytes;
                if(character=='|') {
                	//System.getProperty("java.classpath");
                	// use of TTS to translate the word
                	
                	//String word_decoded = new String(word.getBytes(),"UTF-8"); // Charset with which bytes were encoded
                	byte[] bytes = word.getBytes(StandardCharsets.ISO_8859_1);
                	String wordUtf8 = new String(bytes, StandardCharsets.UTF_8);
                	System.out.println( "word received: "+ wordUtf8 );
                	
                	this.application.writeWord(wordUtf8);
                	
                	if (wordUtf8.length() > 1) {
                		speak(language, wordUtf8.toLowerCase());
                	} else {
                		speak(language, wordUtf8);
                	}
                	word = "";
                	
                } else if(character=='/') { 
                	language = word;
                	word = "";
                } else {
                	word = word + String.valueOf(character);
                }
                

                if ( test == 42 ) break;
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                break;
            }
            n++;
        }

        System.out.println( "server thread stopped" );
    }
    
	public void speak(String language, String text) {
		System.out.println(text);
		
		//Create a new Thread because JLayer is running on the current Thread and will make the application to lag
		Thread thread = new Thread(() -> {
			try {
				
				//Create a JLayer instance
				synthesizer.setLanguage(language);
				AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
				player.play();
				
				System.out.println("Successfully got back synthesizer data");
				
			} catch (IOException | JavaLayerException e) {
				
				e.printStackTrace(); //Print the exception ( we want to know , not hide below our finger , like many developers do...)
				
			}
		});
		
		//We don't want the application to terminate before this Thread terminates
		thread.setDaemon(false);
		
		//Start the Thread
		thread.start();
		
	}
	

   
}