package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	private Handler handler;
	private GameObject player;
	boolean alive = true;
	int i;
	float diffX, diffY, distance;

	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(i = 0; i < handler.object.size(); i ++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
	}

	public void tick() {
		if(alive) {
			x += velX;
			y += velY;	
			diffX = x - player.getX() -8;
			diffY = y - player.getY() -8;
			distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY()) * (y - player.getY()));
			
			velX = (float) (-1.0/distance * diffX);
			velY = (float) (-1.0/distance * diffY);
		} else {
			handler.removeObject(this);
		}
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i ++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Projectile) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					alive = false;
				}
			}
		}
	}

	public void render(Graphics g) {
		if(alive) {
			g.setColor(Color.red);
			g.fillRoundRect((int)x, (int)y, 16, 16, 16, 16);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect((int)x, (int)y, 16, 16, 16, 16);
		}
	}

	public Rectangle getBounds() {
		if(alive) {
			return new Rectangle((int)x, (int)y, 16, 16);
		} else {
			return new Rectangle(-100, -100, 16, 16);
		}
	}

}
