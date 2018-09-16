package HealthCare;

import java.awt.Image;

import DesertWar.DesertWarGame;

public class HealthUp extends CarePackageAbstract{

	public HealthUp(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
        }
	public void update() {
		y=y+speed;
		if(DesertWarGame.p1.collision(x, y, width, height)){
			DesertWarGame.p1.healthUp();;
			System.out.println("Health BOOSTED");
			show = false;
		}
		else if(DesertWarGame.p2.collision(x, y, width, height)){
			DesertWarGame.p2.healthUp();
                        System.out.println("Health BOOSTED");
			show = false;
		}

	}

}
