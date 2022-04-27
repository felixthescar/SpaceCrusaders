package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	Projectile projectile;
	
	private int timer = 0;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x,  0,  Game.WIDTH-50);
		y = Game.clamp((int)y,  0,  Game.HEIGHT-73);
		
		collision();
		timer ++;
		if(timer%100 == 0) attack();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i ++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
	public void attack() {
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 1, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 2, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 3, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 4, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 5, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 6, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 7, handler));
		handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 8, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,  (int)y,  32,  32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	
}
