package main;

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
		// keyDown[0] = false;
		// keyDown[1] = false;
		// keyDown[2] = false;
		// keyDown[3] = false;
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
		// for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
		// 	GameObject tempObject = Handler.getHandler().object.get(i);
			
		// 	// if(tempObject.getId() == ID.Player) {
		// 	// 	//key events
		// 	// 	if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyDown[0] = true;}
		// 	// 	if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[1] = true;}
		// 	// 	if(key == KeyEvent.VK_S) {tempObject.setVelY(5); keyDown[2] = true; }
		// 	// 	if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[3] = true; }
		// 	// }
		// }
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
		// for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
		// 	GameObject tempObject =  Handler.getHandler().object.get(i);
			
		// 	if(tempObject.getId() == ID.Player) {
		// 		//key events
		// 		if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
		// 		if(key == KeyEvent.VK_A) keyDown[1] = false;//tempObject.setVelX(0);
		// 		if(key == KeyEvent.VK_S) keyDown[2] = false;//tempObject.setVelY(0);
		// 		if(key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setVelX(0);
				
		// 		//vertical movement
		// 		//w and s
		// 		if(!keyDown[0] && !keyDown[2]) tempObject.setVelY(0);
		// 		//horizontal movement
		// 		//a and d
		// 		if(!keyDown[1] && !keyDown[3]) tempObject.setVelX(0);
		// 	}
		// }
	}
	
}
