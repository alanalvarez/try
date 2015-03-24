package logic;

public class AsteroidThread extends Thread{
	
	private Asteroid asteroid=null;

	public AsteroidThread(Asteroid asteroid) {
		super();
		this.asteroid = asteroid;
	}
	@Override
	public void run() {
		int xIncrement = 1;
		int yIncrement = 1;
		
		while(true){
			try {
				Thread.sleep(25); //velocidad del asteroide
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//rebote en paredes
			if (asteroid.getX() < 0)
				xIncrement = 1;
			if (asteroid.getX() > 450)
				xIncrement = -1;
			if (asteroid.getY() < 0)
				yIncrement = 1;
			if (asteroid.getY() > 450)
				yIncrement = -1;
			
			asteroid.setX(asteroid.getX()+ xIncrement);
			asteroid.setY(asteroid.getY()+ yIncrement);
		}
	}
}
