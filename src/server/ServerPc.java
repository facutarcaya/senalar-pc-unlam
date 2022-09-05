package server;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import app.InitConnection;

import com.sun.speech.freetts.VoiceManager;
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
        String palabra = "";

        // placeholder recv loop
        while ( true )
        {
            try
            {
            	
                byte test = this.dataInputStream.readByte();
                char letra = (char) test;
                System.out.println( "byte received: "+test );
                System.out.println( "letra recibida: "+letra );
                
                if(letra=='|') {
                	System.out.println( "palabra: "+palabra );    
                	//Acá deberiamos utilizar TTS para transcribir la palabra a voz
                	//System.getProperty("java.classpath");
                	hablar(palabra);
                	palabra = "";
                }else {
                	palabra = palabra + String.valueOf(letra);
                }
                

                if ( test == 42 ) break;
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                break;
            }
        }

        System.out.println( "server thread stopped" );
    }
    
    public void hablar(String palabra) {
    	System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    	//System.setProperty("mbrola.base", "C:/mbrola/mbrola");
    	vocesI=VoiceManager.getInstance();
    	//voz=vocesI.getVoice("mbrola_us1");
    	voz=vocesI.getVoice("kevin16");
    	voz.allocate();
    	voz.speak(palabra);
    }
}