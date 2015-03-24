package logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private int puerto;
	private ServerSocket myServerSocket;
	private Cosmos myObjectCosmos;
	
	public Server(int puerto) {
		super();
		this.puerto = puerto;
	}
	public void start(){
		try{
			myServerSocket=new ServerSocket(puerto);
			System.out.println("Escuchando puerto:"+ puerto);
			while(true){
				Socket mySocket= myServerSocket.accept();
				System.out.println("Conexion! ");
				InputStream myInputStream= mySocket.getInputStream();
				
				ObjectInputStream myObjectInputStream = null;
				Object myInputObject=null;
				
				try {
					myObjectInputStream=new ObjectInputStream(myInputStream);
					myInputObject=myObjectInputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				buildCosmos(myInputObject);
				
				try {
					OutputStream myOutputStream= mySocket.getOutputStream();
					ObjectOutputStream myObjectOutputStream=new ObjectOutputStream(myOutputStream);
					myObjectOutputStream.writeObject(myObjectCosmos);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
					
				mySocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void buildCosmos(Object myObject){
		if(myObject instanceof Cosmos){
			myObjectCosmos=(Cosmos) myObject;
			System.out.println(myObjectCosmos);
		}
		if(myObject instanceof Asteroid){
			myObjectCosmos.getAsteroids().add((Asteroid) myObject);
			System.out.println(myObjectCosmos);
		}
	}
}
