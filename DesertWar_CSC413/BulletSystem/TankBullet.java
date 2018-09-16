package BulletSystem;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import Tank.Tank;
import SoundEffect.Sound;
import DesertWar.DesertWarGame;
import Wall.Wall;

public class TankBullet extends Bullet {
	private int bulletDMG, angle;
	public TankBullet(Image img, int speed, Tank tank, int sideSpeed, int damage) {
		super(img, tank.x+((tank.getWidth()/2)-(img.getWidth(null)/120))-5, tank.y+(img.getHeight(null)/2)-5, speed, sideSpeed);
		bulletDMG = damage;
		sizeX = img.getWidth(null)/60;
		sizeY = img.getHeight(null);
		TK = tank;
		angle = TK.getAngle();
		show = true;
	}
	public void destroyBullet(){
		show = false;
	}
	@Override
	public void update(){
                //solved the bullet angle (on stack)
		x+=speed*Math.cos(Math.toRadians(6*angle));
		y-=speed*Math.sin(Math.toRadians(6*angle));

		if( show && DesertWarGame.p1.collision(x, y, sizeX, sizeY) && TK != DesertWarGame.p1 && !DesertWarGame.p1.isRespawning()) {
                show = false;
        	DesertWarGame.p1.enemyBulletDmg(bulletDMG);
        	DesertWarGame.p2.setScore(30);
        }else if( show && DesertWarGame.p2.collision(x, y, sizeX, sizeY) && TK != DesertWarGame.p2 && !DesertWarGame.p2.isRespawning() ) {
            show = false;
        	DesertWarGame.p2.enemyBulletDmg(bulletDMG);
        	DesertWarGame.p1.setScore(30);
        }
        else{
        	for(int i = 0; i < DesertWarGame.getTankGame().getWall().size()-1; i++){
				Wall tempWall = DesertWarGame.getTankGame().getWall().get(i);
        		if(tempWall.getWallRectangle().intersects(x, y, width/60, height)&&tempWall.isRespawning()){
        			if(tempWall.isDestroyAble()){
        				tempWall.breakWall();
    		    		addExplosion(DesertWarGame.getTankGame().getWallExplosionImg(),tempWall.x,tempWall.y);
    		    		Sound.player("snd_explosion1.wav", false);
        			}
		    		show=false;
        		}
        	}
       }
		if(!show){
    		addExplosion(DesertWarGame.getTankGame().getWallExplosionImg(),x,y);
    		Sound.player("snd_explosion1.wav", false);
		}
	}
	
	@Override
	public void draw(ImageObserver obs, Graphics2D g) {
        g.drawImage(img, x, y, x+(img.getWidth(null)/60), y+img.getHeight(null), angle*24, 0, angle*24+24, img.getHeight(null), obs);
        update();
	}
	
}
