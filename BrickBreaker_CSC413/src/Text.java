import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
/**
 *
 * @author Saad
 */
public class Text {
	public static int menu_bar_height = 25;
	JLabel start_label = new JLabel("Level "+Levels.current_level+", Click To Start", SwingConstants.CENTER);
	JLabel rewards_label = new JLabel("", SwingConstants.CENTER);
	JLabel lives_text_label = new JLabel("|| Lives: ");
	JLabel lives_label = new JLabel("");
	JLabel level_label = new JLabel("LEVEL "+Levels.current_level, SwingConstants.CENTER);
	Font lfont = new Font("courier", Font.PLAIN, 13);
	Font rwdfont = new Font("courier", Font.BOLD, 17);
	Font lvlfont = new Font("courier", Font.BOLD, 13);
	
    private BrickBreaker game;

	public Text(BrickBreaker game) {
		this.game = game;
		makeStartLabel();
		makeRewardsLabel();
		makeLivesLabel();
		makeLevelLabel();
	}
	
	void makeStartLabel() {
		start_label.setVisible(true);
		start_label.setBounds(0, 155, BrickBreaker.WIDTH, 100);
		start_label.setFont(lfont);
		start_label.setForeground(Color.GREEN);
		game.add(start_label);
	}
	
	void makeRewardsLabel() {
		rewards_label.setVisible(true);
		rewards_label.setBounds(0, 0, BrickBreaker.WIDTH, 100);
		rewards_label.setFont(rwdfont);
		rewards_label.setForeground(Color.CYAN);
		game.add(rewards_label);
	}
	
	void makeLivesLabel() {
		lives_text_label.setVisible(true); lives_label.setVisible(true);
		lives_text_label.setBounds(BrickBreaker.WIDTH-103, -33, BrickBreaker.WIDTH, 100); lives_label.setBounds(BrickBreaker.WIDTH-22, -33, BrickBreaker.WIDTH, 100);
		lives_text_label.setFont(lfont); lives_label.setFont(lfont);
		lives_text_label.setForeground(Color.WHITE); lives_label.setForeground(Color.GREEN);
		game.add(lives_text_label);
		lives_label.setText(""+game.racket.lives);
		game.add(lives_label);
	}
	
	void makeLevelLabel() {
		level_label.setVisible(true);
		level_label.setBounds(0, -33, BrickBreaker.WIDTH, 100);
		level_label.setFont(lvlfont);
		level_label.setForeground(Color.WHITE);
		game.add(level_label);
	}
	
	public void paint(Graphics2D g) {
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 5, BrickBreaker.WIDTH, 2);
	    g.fillRect(0, menu_bar_height, BrickBreaker.WIDTH, 2);
	    makeLivesLabel();
	}
}
