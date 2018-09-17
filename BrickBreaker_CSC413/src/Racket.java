import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 *
 * @author Saad
 */
public class Racket {
    
	public static final int Y = 40;
	public static int WIDTH = 30;
	public static int HEIGHT = 10;
	public static int SIDE_WIDTH = 20;
	public static Color racketMainColor = Color.WHITE;
	public static Color racketSideColor = Color.GRAY;
	public int lives = 3;
	public static int defaultCoordX= 197;
	public int x = defaultCoordX;
	public int moveSpeed = 10;

	private BrickBreaker game;

	public Racket(BrickBreaker game) {
		this.game = game;
	}


	void move(int direction) {
		// left & right arrows
		int width_margin = 5;
		if (direction == ListenersHandler.LEFT) {
			if (x > width_margin) {
				x -= moveSpeed;
			}
		}
		else if (direction == ListenersHandler.RIGHT) {
			if (x < game.getWidth()-(WIDTH+width_margin)) {
				x += moveSpeed;
			}
		}

	}
	
	public static void looseLive(BrickBreaker game) {
		game.racket.lives--;
		BrickBreaker.start = true;
		game.ball.xa = 0;
		game.ball.ya = 0;
		game.ball.x = Ball.default_x;
		game.ball.y = Ball.default_y;
		game.racket.x = Racket.defaultCoordX;
		game.text.lives_label.setText(""+game.racket.lives);
		game.text.start_label.setText("Lives: "+game.racket.lives);
		game.speed = BrickBreaker.defaultSpeed;
		game.rewards.stopAllRewards();
	}

	public void paint(Graphics2D g) {
		// Left side
		g.setColor(racketSideColor);
		g.fillRoundRect(x-(SIDE_WIDTH-3), game.getHeight()-Y, SIDE_WIDTH, HEIGHT, 10, 10);
		// Right side
		g.setColor(racketSideColor);
		g.fillRoundRect(x+(WIDTH-3), game.getHeight()-Y, SIDE_WIDTH, HEIGHT, 10, 10);
		// Middle
		g.setColor(racketMainColor);
		g.fillRect(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}
	
	public int getTopY() {
		return game.getHeight() - Y - HEIGHT;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}
	
	public Rectangle getLeftBounds() {
		return new Rectangle(x-20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
	
	public Rectangle getRightBounds() {
		return new Rectangle(x+20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
}




