package engine;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x, y;
	protected ID id;
	
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public ID getId() {
		return id;
	}
}
//final version