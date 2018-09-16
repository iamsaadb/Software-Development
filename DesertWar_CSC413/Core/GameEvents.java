package Core;

import java.awt.event.KeyEvent;
import java.util.Observable;

public class GameEvents extends Observable {
	public int keyInp;
	public Object event;

	public void setValue(KeyEvent e) {
		keyInp = 1; // let's assume this means key input.
		// Should use CONSTANT value for this when you program
		event = e;
		setChanged();
		// trigger notification
		notifyObservers(this);
	}

	public void setValue(String msg) {
		keyInp = 2;
		event = msg;
		setChanged();
		// trigger notification
		notifyObservers(this);
	}
}
