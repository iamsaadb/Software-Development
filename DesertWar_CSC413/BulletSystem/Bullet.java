package BulletSystem;

import Core.GameObj;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import Tank.Tank;

public class Bullet extends GameObj{
	int sizeX, sizeY;
	Rectangle bulletBox;
	boolean show;
	int sideSpeed = 0;
	Tank TK;
	
	public Tank getTank(){
		return TK;
	}
	public boolean isShow(){
		return show;
	}
	Bullet(Image img, int x, int y, int speed, int sideSpeed){
		super(img, x, y, speed);
		show = true;
		this.sideSpeed = sideSpeed;
	}
	
	public void draw(ImageObserver obs, Graphics2D g) {
		if(show){
			g.drawImage(img, x, y, obs);
		}
		update();
    }
	
	public void update(){
		
	}

}
