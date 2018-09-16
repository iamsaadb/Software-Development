package Tank;

import Core.GameObj;
import Core.GameEvents;
import SoundEffect.Sound;
import BulletSystem.TankBullet;
import DesertWar.DesertWarGame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Observable;

public class Tank extends GameObj  {
	private int left , right, up, down, shootkey, angle = 0, shootCoolDown = 0, shootRate;
	private boolean moveLeft, moveRight, moveUp, moveDown;
	protected boolean powerUp;
        protected int coolDown = 0, score = 0, health = 4, life = 3,targetPointX, targetPointY;

	public Tank(Image img, int x, int y, int speed, int left, int right, int up, int down, int shotkey) {
    	super( img, x, y, speed);
    	powerUp = false;
    	this.width = img.getWidth(null)/60;
    	this.left = left;
    	this.right = right;
    	this.up = up;
    	this.down = down;
        
    	shootkey = shotkey;
    	moveLeft= false;
    	moveRight = false;
    	moveUp = false;
    	moveDown= false;
    	boom = false;
        
    	shootRate= 50;
        
        targetPointX = x;
        targetPointY = y;
    }
        

     
    //Getters and setters 
    public void setBoom(boolean x){
    	boom = x;
    }
    public void setScore(int s){
    	score += s;
    }
    public void setHP(int hp){
    	health=hp;
    }
      public int getLife(){
    	return life;
    }
    public int getHP(){
      return health;
    }
    public int getScore(){
    	return score;
    }

    public void oneUp(){
    	this.powerUp = true;
    	shootCoolDown = 75;
    }
    public void healthUp(){
    	this.health = 5;
    }
       public boolean isLose(){
    	if(life<=0 ){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public boolean isRespawning(){
    	return (coolDown != 0 );
    }
       public void enemyBulletDmg(int dmg){
	   health-= dmg;
    }
    public void draw(ImageObserver obs, Graphics2D g) {
    	shootCoolDown--;
        //Death Explosion - Explosion2
        if(health <= 0){
                Sound.player("snd_explosion2.wav", false);
        	boom = true;
        }
        if(health>0 && coolDown==0 && life>0){
        	boom = false;
        	g.drawImage(img, x, y, x+(img.getWidth(null)/60), y+img.getHeight(null), angle*64, 0, angle*64+64, img.getHeight(null), obs);
        	if(!DesertWarGame.p1.isRespawning()&&!DesertWarGame.p2.isRespawning()){
                   //collision after effect, tank 1 pushes tank 2 and vice versa
        		if (DesertWarGame.p1.collision(DesertWarGame.p2.x, DesertWarGame.p2.y, DesertWarGame.p2.width, DesertWarGame.p2.height)){
        			if(DesertWarGame.p1.x>x){
        				DesertWarGame.p1.x+=speed*2;
        				DesertWarGame.p2.x-=speed*2;
        			}else if(DesertWarGame.p1.x<(this.x)){
        				DesertWarGame.p1.x-=speed*2;
        				DesertWarGame.p2.x+=speed*2;
        			}if(DesertWarGame.p1.y>this.y){
        				DesertWarGame.p1.y+=speed*2;
        				DesertWarGame.p2.y-=speed*2;
        			}else if(DesertWarGame.p1.y<this.y){
        				DesertWarGame.p1.y-=speed*2;
        				DesertWarGame.p2.y+=speed*2;
        			}
        		}
        	}
        }else if(boom==true&&coolDown==0&&life>0){
    		addExplosion(DesertWarGame.getTankGame().getExplosionImg(),x,y);
    		coolDown = 180;
    		powerUp = false;
    		if(--life>=0) health = 4;
    		boom = false;
    		x=targetPointX;
    		y=targetPointY;
        }else{
        	coolDown--;
        }
    }
   
    public void shoot(Tank p){
		if(powerUp){
	    	DesertWarGame.getTankGame().getBullets().add(new TankBullet(DesertWarGame.getTankGame().getStrongBulletImg(), speed*4, this, 0, 2));

		}else{
	    	DesertWarGame.getTankGame().getBullets().add(new TankBullet(DesertWarGame.getTankGame().getWeakBulletImg(), speed*2, this, 0, 1));
		}
    
    }
	public void update(Observable obj, Object arg) {
		GameEvents ge = (GameEvents) arg;
		if (ge.keyInp == 1 && !boom && coolDown==0) {
			KeyEvent e = (KeyEvent) ge.event;
			// smooth animation.
			if (e.getKeyCode() == left) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveLeft = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveLeft = true;
				}
			}
			if (e.getKeyCode() == right) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveRight = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveRight = true;
				}
			}
			if (e.getKeyCode() == up) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveUp = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveUp = true;
				}
			}
			if (e.getKeyCode() == down) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveDown = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveDown = true;
				}
			}
			if (e.getKeyCode() == shootkey&&shootCoolDown<=0) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					shootCoolDown = shootRate;
					System.out.println("Boom");
					shoot(this);
				}
			}

		} 
	}
        
        //Tank getters
	public int getHealth(){
		return health;
	}
	public int getAngle(){
		return angle;
	}
        //center coordinates Tank
	public int getTankCenterX(){
		return x+((img.getWidth(null)/60)/2);
	}
	public int getTankCenterY(){
		return y+(img.getHeight(null)/2);
	}
	
	
    public void updateMove(){
		if (moveLeft == true) {
			angle += 1;
		}
		if (moveRight == true) {
			angle -= 1;
		}
		if (moveUp == true ) {
			x+=speed*Math.cos(Math.toRadians(6*angle));
			y-=speed*Math.sin(Math.toRadians(6*angle));
		}
		if (moveDown == true) {
			x-=speed*Math.cos(Math.toRadians(6*angle));
			y+=speed*Math.sin(Math.toRadians(6*angle));
		}
                if (coolDown>0) {
			moveLeft = false;
			moveRight = false;
			moveUp = false;
			moveDown = false;
		}
		if(angle == -1){
                    angle = 59;
                }
		else if (angle == 60){
                    angle = 0;
                }
		
    }
}
