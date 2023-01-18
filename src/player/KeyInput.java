package player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KeyInput extends KeyAdapter{
	
	//private boolean[] keyDown = new boolean[4];
	private static List<Function<Integer, Void>> keyPressedListeners;
	private static List<Function<Integer, Void>> keyReleasedListeners;
	private static KeyInput instance = null;
	private static boolean[] keys;
	
	private KeyInput() {

		keyPressedListeners = new ArrayList<Function<Integer, Void>>();
		keyReleasedListeners = new ArrayList<Function<Integer, Void>>();
		keys = new boolean[402];
	}

	public static KeyInput getKeyInput(){
		if(instance == null)
			instance = new KeyInput();
		return instance;
	}

	public void addKeyPressedListener(Function<Integer, Void> sub) {
		KeyInput.keyPressedListeners.add(sub);
	}

	public void addKeyReleasedListener(Function<Integer, Void> sub) {
		KeyInput.keyReleasedListeners.add(sub);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(keys[key])
			return;
		keys[key] = true;
		for (Function<Integer, Void> function: keyPressedListeners){
			function.apply(e.getKeyCode());
		}

		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = false;
		for (Function<Integer, Void> function: keyReleasedListeners){
			function.apply(e.getKeyCode());
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
}
//final version