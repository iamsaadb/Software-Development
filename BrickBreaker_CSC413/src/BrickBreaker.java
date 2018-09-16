import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author Saad
 * 
 * credits to Edu4Java
 */


public class BrickBreaker extends JPanel{
    public static final int WIDTH = 410;
    public static final int HEIGHT = 450;
    public static long counter = 0; 
    public static int defaultSpeed = 6;
    public int speed = defaultSpeed;
    public static boolean start = true;
    public static boolean pause = false;
    public static int oldBallXCoord = 0;
    public static int oldBallYCoord = 0;
    
    
    public BrickBreaker(){
        setVisible(true);
        setLayout(null);
        setBackground(Color.BLACK);
    }
    
    Ball ball = new Ball(this);
    Racket racket = new Racket(this);
    Bricks brick = new Bricks(this);
    Rewards rewards = new Rewards(this);
    ListenersHandler listeners = new ListenersHandler(this);
    Text text = new Text(this);
    Levels levels = new Levels(this);
    
    private void move() {
	ball.move();
    }
    @Override
    public void paint(Graphics g) {
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	ball.paint(g2d);
	racket.paint(g2d);
	brick.paint(g2d);
	rewards.paint(g2d);
	text.paint(g2d);
    }
    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
	System.exit(ABORT);
    }
    
    public static void startGame(BrickBreaker game) {

	if (start) {
            int xdireccion = (int) Math.floor(Math.random()*2+1);
            game.ball.ya = -1;
            if (xdireccion == 1) {
                    game.ball.xa = 1;
            }
            else if (xdireccion == 2) {
                    game.ball.xa = -1;
            }
            start = false;
            game.text.start_label.setText("");
            game.text.level_label.setText("LEVEL "+Levels.current_level);
        } 
        else {
		if (!pause) {
                    // Pause the game
                    oldBallXCoord = game.ball.xa;
		    oldBallYCoord = game.ball.ya;
		    game.ball.ya = 0;
		    game.ball.xa = 0;
		    game.text.start_label.setText("Game Paused. Click to Resume!");
		    game.text.start_label.setForeground(Color.RED);
		    pause = true;
		}
		else {
		    // Resume the game
		    game.ball.xa = oldBallXCoord;
	            game.ball.ya = oldBallYCoord;
		    game.text.start_label.setText("");
		    game.text.start_label.setForeground(Color.GREEN);
		    pause = false;
			}
		}
	}
    public static void main(String[] args) throws InterruptedException {
        Splash splashScreen = new Splash();
        splashScreen.setVisible(true);
       // Sound.playMusic("arcade.wav");
        try {
        for (int i = 0 ; i < 101 ; i++){
            
            Thread.sleep(50);
            splashScreen.label.setText(Integer.toString(i)+" %");
            if (i==12){Thread.sleep(200);}
            if (i==40){Thread.sleep(120);}
            if (i==100){
                Thread.sleep(800);
                splashScreen.dispose();
        }
        }
       
        } catch (Exception e){
        }
        
	JFrame frame = new JFrame("BrickBreaker");
	BrickBreaker game = new BrickBreaker();
	frame.getContentPane().add(game);
	frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable( false );
	Sound.playMusic("arcade.wav");
	
	while (true) {
                

		System.out.println(pause);
		if (!pause && !start) {
			game.move();
			game.repaint();
			counter++;
			if (counter%100 == 0) {
				if ((counter/100)%15 == 0) {
                                    for (int i = 0; i < game.brick.bricks.size(); i++) {
                                        game.brick.bricks.get(i).y += 10;
					}
				}
				game.rewards.paintReward();
			}
			Thread.sleep(game.speed);
		}
	}
    }
    
}
