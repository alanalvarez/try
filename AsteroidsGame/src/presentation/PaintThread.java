package presentation;

import javax.swing.JPanel;
import logic.Cosmos;

public class PaintThread extends Thread{
	private JPanel panel = null;
	private Cosmos myCosmos = null;
	private boolean ciclo=true;
	private int[] xPoints;
	private int[] yPoints;

	public PaintThread(JPanel panel, Cosmos myCosmos) {
		super();
		this.panel = panel;
		this.myCosmos = myCosmos;
	}
	@Override
	public void run() {
		int x = 0,xp=0,x2 = 0,x3 = 0,x4 = 0;
		int y = 0,yp=0,y2 = 0,y3 = 0,y4 = 0;
		int size = 0;
		boolean dead=false;

		while (ciclo) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			panel.getGraphics().clearRect(0, 0, 500, 500);
			xp=myCosmos.getMyPlayer().getX();
			yp=myCosmos.getMyPlayer().getY();
			
			x2=xp+10;
			x3=xp;
			x4=xp-10;
			y2=yp+20;
			y3=yp+15;
			y4=yp+20;	

		
			int xPoints[] = {xp, x2, x3, x4};
			int yPoints[] = {yp, y2, y3, y4};
			
			this.setxPoints(xPoints);
			this.setyPoints(yPoints);
			
			for (int i = 0; i < myCosmos.getAsteroids().size(); i++) {
				x = myCosmos.getAsteroids().get(i).getX();
				y = myCosmos.getAsteroids().get(i).getY();
				size = myCosmos.getAsteroids().get(i).getSize();
				
				panel.getGraphics().drawOval(x, y, size, size);
				
				//muerte de nave
				if((xp >= x && xp <= x+size) && ((yp >= y && yp <= y+size)||(yp+15 >= y && yp+15 <= y+size))){
					dead=true;
					i=myCosmos.getAsteroids().size();
				}
				if((xp+10 >= x && xp+10 <= x+size) && (yp+20 >= y && yp+20 <= y+size)){
					dead=true;
					i=myCosmos.getAsteroids().size();
				}
				if((xp-10 >= x && xp-10 <= x+size) && (yp+20 >= y && yp+20 <= y+size)){
					dead=true;
					i=myCosmos.getAsteroids().size();
				}
			}
			if(dead){
				this.Kill(xp,yp);
			}else{
				this.paintPlayer();
			}
			
			if(true){
				
			}
		}
	}

	public void Kill(int xp,int yp){
		for(int i=0;i<=20;i+=5){
			panel.getGraphics().drawLine(xp+i, yp-5+i, xp+5-i, yp+20-i);
		}
		for(int i=0;i<=20;i+=5){
			panel.getGraphics().drawLine(xp-i, yp-5+i, xp+5+i, yp+20-i);
		}
		ciclo=false;
	}
	public void paintPlayer(){
		panel.getGraphics().drawPolygon(this.getxPoints(), this.getyPoints(), 4);
		panel.getGraphics().drawOval(myCosmos.getMyPlayer().getBulletx(), myCosmos.getMyPlayer().getBullety(), 3, 3);
	}
	
	public int[] getxPoints() {
		return xPoints;
	}
	
	public void setxPoints(int[] xPoints) {
		this.xPoints = xPoints;
	}
	public int[] getyPoints() {
		return yPoints;
	}
	public void setyPoints(int[] yPoints) {
		this.yPoints = yPoints;
	}
}
