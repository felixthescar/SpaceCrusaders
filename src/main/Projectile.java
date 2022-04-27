package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject{
	
	private Handler handler;
	private int dir;
	private int speed = 4;
	
	public Projectile(float x, float y, ID id, int dir, Handler handler) {
		super(x, y, id);
		this.dir = dir;
		this.handler = handler;
		
		
		switch(dir) {
			case 1: velX = 0; velY = -speed; break;	//sus
			case 2: velX = 0; velY = speed; break;	//jos
			case 3: velX = -speed; velY = 0; break;	//stanga
			case 4: velX = speed; velY = 0; break;	//dreapta
			case 5: velX = -speed; velY = -speed; break;//stanga sus
			case 6: velX = speed; velY = -speed; break;	//dreapta sus
			case 7: velX = -speed; velY = speed; break;	//stanga jos
			case 8: velX = speed; velY = speed; break;	//dreapta jos
		}
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if((x < 0 || x > Game.WIDTH) || (y < 0 || y > Game.HEIGHT)) handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRoundRect((int)x, (int)y, 8, 8, 8, 8);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}
	
	public void removeProjectile() {
		handler.removeObject(this);
	}
	
}
