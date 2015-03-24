package presentation;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private String host;
	private int puerto;
	Object myInputObject;
	
	public Client(String host, int puerto) {
		super();
		this.host = host;
		this.puerto = puerto;
	}
	
	public void send(Object myOutputObject){
		Socket mySocket=null;
		try {
			mySocket= new Socket(host,puerto);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			OutputStream myOutputStream= mySocket.getOutputStream();
			ObjectOutputStream myObjectOutputStream= new ObjectOutputStream(myOutputStream);
			myObjectOutputStream.writeObject(myOutputObject);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			InputStream myInputStream = mySocket.getInputStream();
			ObjectInputStream myObjectInputStream= new ObjectInputStream(myInputStream);
			setMyInputObject( myObjectInputStream.readObject());
			System.out.println(getMyInputObject());
		} catch (ClassNotFoundException | IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			mySocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Object getMyInputObject() {
		return myInputObject;
	}

	public void setMyInputObject(Object myInputObject) {
		this.myInputObject = myInputObject;
	}
	
}
