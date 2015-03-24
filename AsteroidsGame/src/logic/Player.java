package logic;

import java.io.Serializable;

public class Player implements Serializable{
	private int x = 0;
	private int y = 0;
	private int bulletx=0;
	private int bullety=0;
	private boolean clo=false;
	
	public Player(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Player [x=" + x + ", y=" + y + "]";
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
	public void moveLeft(){
		x-=5;
	}
	public void moveRigth(){
		x+=5;
	}
	public void moveUp(){
		y-=5;
	}
	public void moveDown(){
		y+=5;
	}
	public int getBulletx() {
		return bulletx;
	}
	public void setBulletx(int bulletx) {
		this.bulletx = bulletx;
	}
	public int getBullety() {
		return bullety;
	}
	public void setBullety(int bullety) {
		this.bullety = bullety;
	}
	public boolean isClo() {
		return clo;
	}
	public void setClo(boolean clo) {
		this.clo = clo;
	}
}