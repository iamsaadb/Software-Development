package HealthCare;

import java.awt.Image;

import DesertWar.DesertWarGame;

public class PowerUp extends CarePackageAbstract{
	public PowerUp(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
	}
	
	public void update() {
		y=y+speed;
		if(DesertWarGame.p1.collision(x, y, width, height)){
			DesertWarGame.p1.oneUp();
			DesertWarGame.p1.setScore(100);
			System.out.println("1p UP");
			show = false;
		}
		else if(DesertWarGame.p2.collision(x, y, width, height)){
			DesertWarGame.p2.oneUp();
			DesertWarGame.p2.setScore(100);
			show = false;
		}
	}
}
