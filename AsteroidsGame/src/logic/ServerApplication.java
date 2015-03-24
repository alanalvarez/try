package logic;

public class ServerApplication {

	public static void main(String[] args) {
		
		Server myServer = new Server(5000);
		myServer.start();
	}
}