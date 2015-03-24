package logic;

import java.io.Serializable;
import java.util.Vector;

public class Cosmos implements Serializable{
	private Vector<Asteroid> asteroids = new Vector<Asteroid>();
	private Player myPlayer;
	
	public Player getMyPlayer() {
		return myPlayer;
	}
	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}
	public Cosmos() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vector<Asteroid> getAsteroids() {
		return asteroids;
	}
	public void setAsteroids(Vector<Asteroid> asteroids) {
		this.asteroids = asteroids;
	}
	@Override
	public String toString() {
		return "Cosmos [asteroids=" + asteroids + ", myPlayer=" + myPlayer
				+ "]";
	}
}
