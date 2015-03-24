package logic;

public class PlayerThread extends Thread{
	
	Player player = null;

	public PlayerThread(Player player) {
		super();
		this.player = player;
	}
	@Override
	public void run() {
		int i=0;
		
		while(true){	
			player.setBulletx(player.getX());
			player.setBullety(player.getY());
			while(!player.isClo());
			while(player.isClo()){
				try {
					Thread.sleep(10); //velocidad 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				player.setBullety(player.getBullety() - 1);
				i++;
				if(i==300){
					player.setClo(false);
					i=0;
				}
			}		
		}
	}
}
