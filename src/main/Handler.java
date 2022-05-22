package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	public LinkedList<GameObject> object = null;
	private static Handler instance = null;

	private Handler(){
		object = new LinkedList<GameObject>();
	}

	public static Handler getHandler(){
		if(instance == null)
			instance = new Handler();
		return instance;
	}

	public void tick() {
		for(int i = 0; i < object.size(); i ++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i ++) {
			try {
				GameObject tempObject = object.get(i);
				tempObject.render(g);
			} catch(Exception e) {

			}
		}
	}
	
	public void addObject(GameObject object) {
		Handler.getHandler().object.add(object);
	}
	
	public void removeObject(GameObject object) {
		Handler.getHandler().object.remove(object);
	}

	public void clearEnemys() {
		for(int i  = 0; i < object.size(); i ++) {
			GameObject temp = object.get(i);

			if(temp.getId() == ID.Player) {
				object.clear();
				if(Game.gameState != Game.STATE.End) {
					addObject(new Player((int)temp.getX(), (int)temp.getY(), ID.Player));
				}
			}
		}
	}

	public void clearPlayer() {
		for(int i  = 0; i < object.size(); i ++) {
			GameObject temp = object.get(i);

			if(temp.getId() == ID.Player) {
				removeObject(temp);
			}
		}
	}
}
//final version