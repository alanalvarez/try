package logic;

import java.io.Serializable;

public class Asteroid implements Serializable{
	private int x = 0;
	private int y = 0;
	private int size = 0;
	
	public Asteroid(int x, int y, int size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}
	public Asteroid() {
		super();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Asteroid [x=" + x + ", y=" + y + ", size=" + size + "]";
	}
}
