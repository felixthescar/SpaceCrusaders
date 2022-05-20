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
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		Handler.getHandler().object.add(object);
	}
	
	public void removeObject(GameObject object) {
		Handler.getHandler().object.remove(object);
		System.out.println("sters");
	}
}
