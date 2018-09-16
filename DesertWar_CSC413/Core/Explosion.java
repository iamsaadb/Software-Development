package Core;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observable;

public class Explosion extends GameObj{
	Image[] img;
	int count, i;
	private boolean exploded;

	public Explosion(Image[] img, int x, int y, int speed) {
		super(img, x, y, speed);
		this.img = img;
		count = 0;
		i = 0;
		exploded = false;
	}
        @Override
	public void update(Observable obj, Object arg) {
	}
	public void draw(ImageObserver obs, Graphics2D g) {
		if (i < this.img.length) {
			g.drawImage(this.img[i % this.img.length], x, y, obs);
			if (((count++) % 10) == 0) {
                            i++;
			}
		} else {
			exploded = true;
		}
	}
	public boolean exploded() {
		return exploded;
	}

	
	
}
