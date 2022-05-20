package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends EntityGameObject{
	private GameObject player;
	boolean alive = true;
	private float speed;
	Vector2<Float> dir;

	public BasicEnemy(float x, float y, ID id) {
		super(x, y, id);
		speed = 10;
		for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
			if(Handler.getHandler().object.get(i).getId() == ID.Player) player = Handler.getHandler().object.get(i);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if(alive) {
			/*x += velX;
			y += velY;
			diffX = x - player.getX() -8;
			diffY = y - player.getY() -8;
			distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY()) * (y - player.getY()));
			
			velX = (float) (-1.0/distance * diffX);
			velY = (float) (-1.0/distance * diffY);*/
			dir = new Vector2<Float>(0f, 0f);
			float distance = (float) Math.sqrt((x + player.getX())*(x + player.getX()) + (y + player.getY()) * (y + player.getY()));
			float x = player.getX() - this.x;
			float y = player.getY() - this.y;
			//float u = (float) Math.pow(x/(x*x + y*y), (1/2));
			//float v = (float) Math.pow(y/(x*x + y*y), (1/2));
			Vector2<Float> vel = new Vector2<Float>(x / 1000 * speed, y / 1000 * speed);
			super.setVelocity(vel);
		} else {
			Handler.getHandler().removeObject(this);
		}
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
			GameObject tempObject = Handler.getHandler().object.get(i);
			if(tempObject.getId() == ID.Projectile) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					Handler.getHandler().removeObject(tempObject);
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
