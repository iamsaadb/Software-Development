package Core;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

import DesertWar.DesertWarGame;


public abstract class GameObj implements Observer{ 
	public int x, y, speed, height, width, objLoop = 0;
	Rectangle boundBox ;
	protected boolean boom;
	protected Image[] imgs = new Image[3];
	protected Image img;

	public GameObj(Image[] img, int x, int y, int speed){
		this.imgs = img;
		this.x = x;
		this.y = y;
		height = img[objLoop].getHeight(null);
		width = img[objLoop].getWidth(null);

		this.speed = speed;
	}

	public GameObj(Image img, int x, int y, int speed){
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		height = img.getHeight(null);
		width = img.getWidth(null);
	}
	 public boolean collision(int x, int y, int w, int h) {
	        boundBox = new Rectangle(this.x, this.y, this.width, this.height);
	        Rectangle otherBBox = new Rectangle (x,y,w,h);
	        if(this.boundBox.intersects(otherBBox)&&!boom) { 
	            return true;
	        }
	        return false;
	 }
	 public int getX(){
	    	return x;
	 }
	public int getY() {
		return y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	public void addExplosion(Image[] boomPlanes, int a, int b) {
		DesertWarGame.getTankGame().getExplosion().add(new Explosion(boomPlanes, a, b, 0));
	}

	public void update(Observable obj, Object arg) {

	}
	
	public void draw(ImageObserver obs, Graphics2D g){
		
	}
}
