package HealthCare;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import Core.GameObj;

public abstract class CarePackageAbstract extends GameObj{
	boolean show = false;
	public CarePackageAbstract(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		show = true;
	}
        public boolean isShow(){
		return show;
	}
	public void update() {
	}	
	public void draw(ImageObserver obs, Graphics2D g) {
		if(show)
			g.drawImage(img, x, y, obs);
    }
}
