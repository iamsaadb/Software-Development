import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author Saad
 * 
 */
public class Bricks {
public ArrayList <Brick> bricks = new ArrayList();
public ArrayList<ArrayList<Brick>> brickLines = new ArrayList();
protected BrickBreaker game;

public Bricks(BrickBreaker game) {
    this.game = game;
}

public static class Brick {
    public static int width =15 ;
    public static int height = 10 ;
    int x = 15 ; int y = 50;
    int hits = 1;
    String typeReward="";
    int numReward = (int) Math.floor(Math.random()*hits+1);
    Color color = Color.BLACK;

    public int getUpY(){
        return y-height;
    }

    public Rectangle getBounds(){
        return new Rectangle (x,y,width,height);
    }

    public boolean hasReward(){
        return (typeReward != "" && numReward == hits);
    }      
}

void hitUpdate( int brick){
    int timeHit = bricks.get(brick).hits;

    switch (timeHit){
        case 1 : bricks.get(brick).color = Color.CYAN;
        break;
        case 2 : bricks.get(brick).color = Color.BLUE;
        break;
        case 3 : bricks.get(brick).color = Color.LIGHT_GRAY;
        break;
        case 4 : bricks.get(brick).color = Color.GRAY;
        break; 
        case 5 : bricks.get(brick).color = Color.DARK_GRAY;
        break;
    }
    bricks.get(brick).hits = bricks.get(brick).hits - 1 ;
}
    @SuppressWarnings("static-access")
	public void paint(Graphics2D g) {
		for (int i = 0; i < game.brick.brickLines.size(); i++) {
			for (int j = 0; j < game.brick.brickLines.get(i).size(); j++) {
				g.setColor(game.brick.brickLines.get(i).get(j).color);
				g.fillRect(game.brick.brickLines.get(i).get(j).x, 
                                           game.brick.brickLines.get(i).get(j).y,
                                           game.brick.brickLines.get(i).get(j).width, 
                                           game.brick.brickLines.get(i).get(j).height);
			}
		}
	}

}



