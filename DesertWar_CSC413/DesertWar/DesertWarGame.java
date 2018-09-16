package DesertWar;

import Tank.Tank;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.imageio.ImageIO;

import HealthCare.CarePackageAbstract;
import HealthCare.HealthUp;
import HealthCare.PowerUp;
import SoundEffect.Sound;
import Wall.Wall;
import Core.Explosion;
import Core.GameEvents;
import Core.GameObj;
import BulletSystem.TankBullet;
import SplashScreen.Splash;
import java.net.URL;


@SuppressWarnings("serial")

public class DesertWarGame extends JApplet implements Runnable{
	public static GameEvents gameEvents;
         private static final DesertWarGame tankgame = new DesertWarGame();
	private int w = 900, h = 900;
	private int battleField_X_Size = 1536, battleField_Y_Size = 1536;
	private int p1WindowBoundX, p1WindowBoundY, p2WindowBoundX, p2WindowBoundY, count = 1, gameOverCounter = 50;
        Graphics2D g2, mapGraphics;
	public static Image player1, player2;
	private Image unbreakableWall, breakableWall, weakBullet, strongBullet, life, powerUp, finalScores;
	private Image[] healthBar = new Image[4], explosion = new Image[7], wallExplosion = new Image[6], lifeBar = new Image[3];
	public static Tank p1, p2;
	JPanel p = new JPanel();
	JPanel pLeft = new JPanel();
	boolean gameOver = false;
	String winner, loser;
	
	public InputStream map ;
        public static Random generator = new Random(1234567);
        int i=0;
        private Thread thread;
        private Dimension windowSize;
        private Image background, tankP1, tankP2;
        private BufferedImage bimg, bimg2, P1SplitWindow, P2SplitWindow;
        public ArrayList<Explosion> explosionAnimation = new ArrayList<Explosion>();
        public ArrayList<TankBullet> bullets = new ArrayList<TankBullet>();
        public ArrayList<Wall> unbreakable_wall = new ArrayList<Wall>(), weakwall = new ArrayList<Wall>();
        public ArrayList<CarePackageAbstract> carePackage = new ArrayList<CarePackageAbstract>();
    
	@Override
	public void init(){
		this.setFocusable(true);
		Sound.player("warsoundeffect.wav", true);
		try{
			finalScores = ImageIO.read(this.getClass().getClassLoader().getResource("GameOver.png"));
			background = ImageIO.read(this.getClass().getClassLoader().getResource("Background.png"));
			tankP1 = ImageIO.read(this.getClass().getClassLoader().getResource("Tank_blue_heavy_strip60.png"));
			tankP2 = ImageIO.read(this.getClass().getClassLoader().getResource("Tank_red_heavy_strip60.png"));
			unbreakableWall = ImageIO.read(this.getClass().getClassLoader().getResource("unbreakable_wall.png"));
			breakableWall = ImageIO.read(this.getClass().getClassLoader().getResource("breakable_wall.png"));
			map = this.getClass().getClassLoader().getResourceAsStream("tankmap.txt");
			weakBullet = ImageIO.read(this.getClass().getClassLoader().getResource("Shell_basic_strip60.png"));
			strongBullet = ImageIO.read(this.getClass().getClassLoader().getResource("Shell_heavy_strip60.png"));
			life = ImageIO.read(this.getClass().getClassLoader().getResource("life.png"));
			powerUp = ImageIO.read(this.getClass().getClassLoader().getResource("powerUp.png"));
                        healthBar[3] = ImageIO.read(this.getClass().getClassLoader().getResource("health.png"));
                        healthBar[2] = ImageIO.read(this.getClass().getClassLoader().getResource("health1.png"));
                        healthBar[1] = ImageIO.read(this.getClass().getClassLoader().getResource("health2.png"));
                        healthBar[0] = ImageIO.read(this.getClass().getClassLoader().getResource("health3.png"));
                  
                        lifeBar[2] = ImageIO.read(this.getClass().getClassLoader().getResource("lifeBar1.png"));
                        lifeBar[1] = ImageIO.read(this.getClass().getClassLoader().getResource("lifeBar2.png"));
                        lifeBar[0] = ImageIO.read(this.getClass().getClassLoader().getResource("lifeBar3.png"));
                        explosion[0] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_1.png"));
                        explosion[1] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_2.png"));
                        explosion[2] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_3.png"));
                        explosion[3] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_4.png"));
                        explosion[4] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_5.png"));
                        explosion[5] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_6.png"));
                        explosion[6] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion2_7.png"));
                        wallExplosion[0] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion1_1.png"));
                        wallExplosion[1] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion1_2.png"));
                        wallExplosion[2] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion1_3.png"));
                        wallExplosion[3] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion1_4.png"));
                        wallExplosion[4] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion1_5.png"));
                        wallExplosion[5] = ImageIO.read(this.getClass().getClassLoader().getResource("explosion1_6.png"));
		}catch(Exception e){
			System.err.println(e+"error is resources");
		}
                p1 = new Tank(tankP1, 80,  80 , 6 , KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W,KeyEvent.VK_S, KeyEvent.VK_SPACE);
                p2 = new Tank(tankP2, 1400, 1400, 6, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP,KeyEvent.VK_DOWN, KeyEvent.VK_ENTER);
                gameEvents = new GameEvents();

                gameEvents.addObserver(p1);
                gameEvents.addObserver(p2);

                KeyControl key = new KeyControl();
                addKeyListener(key);
                mapPrinter();
	}
	public ArrayList<TankBullet> getBullets(){
		return bullets;
	}
	public ArrayList<Explosion> getExplosion(){
		return explosionAnimation;
	}
	public ArrayList<Wall> getWall(){
		return unbreakable_wall;
	}
	public static DesertWarGame getTankGame(){
		return tankgame;
	}
	public Image getWeakBulletImg(){
		return weakBullet;
	}
	public Image getStrongBulletImg(){
		return strongBullet;
	}
	public Image[] getHealthBar(){
		return healthBar;
	}
        
	public Image[] getExplosionImg(){
		return explosion;
	}
	public Image[] getWallExplosionImg(){
		return wallExplosion;
	}
	
        
        
	@Override
        public void start() {
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }
	
	@Override
	public void run() {
		Thread me = Thread.currentThread();
		while (thread == me) {
			repaint();
			try {
				thread.sleep(36);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
                //create buffered image
		bimg = (BufferedImage) createImage(battleField_X_Size, battleField_Y_Size);
		g2 = bimg.createGraphics();
                // If the game is over, bimg2 instead of bimg and display scores
		if (!gameOver) {
			drawDemo();
			g.drawImage(bimg2, 0, 0, this);
                       
		} else { 			
			g2.drawImage(finalScores, windowSize.width/2-(finalScores.getWidth(null)/2), 0, this);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 40));
			g2.drawString(winner, (windowSize.width/2)-300, windowSize.height/2+50);
			g2.drawString(loser, (windowSize.width/2)-225, windowSize.height/2+100);
			g.drawImage(bimg, 0, 0, this);
		}
	}
        
	public void drawDemo() {
                //thickness of separator from each side
                int splitSeparator = 2;
		Random rand =new Random();
                //random int to keep healthUp and PowerUp PNG in the battleField
                int rand1 = rand.nextInt(900-100+1)%1536;
                int rand2 = rand.nextInt(800-150+1)%1536;
		drawBackGroundWithTileImage();
                
                //show powerUp and healthUp in random places, once every 1500 count
                
                //test count % 5 == 0 and check if the dispersion of pUp and HP is good 
		if (count % 500 == 0) {
			carePackage.add(new PowerUp(powerUp, rand1 , rand2 , 0));
			//carePackage.add(new HealthUp(life, rand2 ,  rand1 , 0));
		}
                if (!carePackage.isEmpty()) {
			for (int i = 0; i <= carePackage.size() - 1; i++) {
				if (!carePackage.get(i).isShow()) {
					carePackage.remove(i--); 
                                        carePackage.get(i).update();

				} else {
					carePackage.get(i).update();
					carePackage.get(i).draw(this, g2);
				}
			}
		}
                
		//draw wall
		if (!unbreakable_wall.isEmpty()) {
			for (int i = 0; i <= unbreakable_wall.size()-1; i++) {
				unbreakable_wall.get(i).draw(this, g2);
			}
		}
                
                //draw bullets 
		if (!bullets.isEmpty()) {
			for (int i = 0; i <= bullets.size() - 1; i++) {
				bullets.get(i).draw(this, g2);
				if (!bullets.get(i).isShow()) {
					bullets.remove(i--);
				}
			}
		}
                
                //draw tank1
		p1.draw(this, g2);
		p1.updateMove();
                
                //draw tank2
		p2.draw(this, g2);
		p2.updateMove();
                
                //draw explosion 
		if (!explosionAnimation.isEmpty()) {
			for (int i = 0; i <= explosionAnimation.size() - 1; i++) {
				if (explosionAnimation.get(i).exploded()) {
					explosionAnimation.remove(i--);
				} else {
					explosionAnimation.get(i).draw(this, g2);
				}
			}
		}
		bimg2 = (BufferedImage) createImage(windowSize.width, windowSize.height);
		g2 = bimg2.createGraphics();
		
		//That calculate the movement of the camera then print according line to and image, then draw the image
		playerViewBoundChecker();
		P1SplitWindow = bimg.getSubimage(p1WindowBoundX, p1WindowBoundY, windowSize.width/2 - splitSeparator , windowSize.height);
		P2SplitWindow = bimg.getSubimage(p2WindowBoundX, p2WindowBoundY, windowSize.width/2, windowSize.height);
		
		g2.drawImage(P1SplitWindow, 0, 0, this);

		if (p1.getLife() != 0) {
			if (p1.getHealth() != 0) {
			g2.drawImage(healthBar[p1.getHealth() - 1], 0,0, this);
                        
			}
                        g2.drawImage(lifeBar[p1.getLife() - 1], 0,30, this);
		}

		g2.drawImage(P2SplitWindow, windowSize.width / 2 + splitSeparator, 0, this);
                
                if (p2.getLife() != 0) {
			if (p2.getHealth() != 0) {
			g2.drawImage(healthBar[p2.getHealth() - 1],windowSize.width - 120, windowSize.height - 30, this);
			}
                        g2.drawImage(lifeBar[p2.getLife() - 1],windowSize.width - 120, windowSize.height - 60, this);
		}
		Image miniMap = bimg.getScaledInstance(200, 200, Image.SCALE_FAST);
		g2.drawImage(miniMap, windowSize.width / 2 - 100, windowSize.height/8 - 105, 200, 200, this);
		if(p1.isLose()){
			if((--gameOverCounter)==0){
				gameOver = true;
				winner = "Winner: Player 2  *** Score:" + p2.getScore();
				loser = "        Player 1  *** Score:" + p1.getScore();
			}
		}else if(p2.isLose()){
			if((--gameOverCounter)==0){
				gameOver = true;
				winner = "Winner: Player 1  *** Score:" + p1.getScore();
				loser = "        Player 2  *** Score:" + p2.getScore();
			}
		}
		count++;
	}
        
	public void drawBackGroundWithTileImage() {
		int TileWidth = background.getWidth(this);
		int TileHeight = background.getHeight(this);
		int NumberX = 6; 
		int NumberY = 6; 
		for (int i = 0; i < NumberY; i++) {
			for (int j = 0; j < NumberX; j++) {
				g2.drawImage(background, j * TileWidth, i * TileHeight,
						TileWidth, TileHeight, this);
			}
		}
	}
    public void playerViewBoundChecker(){
    	if((p1WindowBoundX= (p1.getTankCenterX()-(windowSize.width/4))) < 0){
        	p1WindowBoundX = 0;
        }else if(p1WindowBoundX>=battleField_X_Size-(windowSize.width/2)){
        	p1WindowBoundX = battleField_X_Size-(windowSize.width/2);
        }

        if((p1WindowBoundY = (p1.getTankCenterY()-(windowSize.height/2))) < 0){
        	p1WindowBoundY = 0;
        }else if(p1WindowBoundY>=(battleField_Y_Size-(windowSize.height))){
        	p1WindowBoundY = battleField_Y_Size-(windowSize.height);
        }
        
     	if((p2WindowBoundX= (p2.getTankCenterX()-(windowSize.width/4))) < 0){
        	p2WindowBoundX = 0;
        }else if(p2WindowBoundX>=battleField_X_Size-(windowSize.width/2)){
        	p2WindowBoundX = battleField_X_Size-(windowSize.width/2);
        }

        if((p2WindowBoundY = (p2.getTankCenterY()-(windowSize.height/2))) < 0){
        	p2WindowBoundY = 0;
        }else if(p2WindowBoundY>=(battleField_Y_Size-(windowSize.height))){
        	p2WindowBoundY = battleField_Y_Size-(windowSize.height);
        }
    }
    
    public void mapPrinter(){
    	BufferedReader line = new BufferedReader(new InputStreamReader(map));
    	String number;
    	int position = 0;
        //read from the file and populateMap
    	try{
    		while((number = line.readLine())!=null){
    			for(int i = 0; i < number.length(); i++){
    				if(number.charAt(i) == '1'){
    					unbreakable_wall.add(new Wall(unbreakableWall, (position % 48)*32, position/48*32, false));
    				}else if(number.charAt(i) == '2'){
    					unbreakable_wall.add(new Wall(breakableWall, (position % 48)*32, position/48*32, true));
    				}
    				 position++;
    			}
    		}
    	}catch(Exception e){
    		System.err.println("MapPrinter"+ e);
    	}
    }
    
	public class KeyControl extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			gameEvents.setValue(e);
		}
		public void keyPressed(KeyEvent e) {
			gameEvents.setValue(e);
		}
	}
	
    public static void main(String argv[]) {
        Splash splashScreen = new Splash();
        splashScreen.setVisible(true);
        try {
        Sound.player("openingSoundTrimmed.wav", false);
        for (int i = 0 ; i < 101 ; i++){
            
            Thread.sleep(110);
            splashScreen.lbl.setText(Integer.toString(i)+" %");
             if (i==100){
                Thread.sleep(1000);
                splashScreen.dispose();
        }
        }
       
        } catch (Exception e){
        }
        tankgame.init();
        JFrame f = new JFrame("DESERT WAR");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", tankgame);
        f.pack();
        f.setSize(new Dimension(tankgame.w, tankgame.h));
        f.setResizable(false);
        f.setVisible(true);
        tankgame.windowSize = f.getContentPane().getSize(); 
        System.out.println(tankgame.windowSize.height +""+tankgame.windowSize.width);
    	tankgame.start();
    	
    }

}
