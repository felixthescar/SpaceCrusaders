package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.Vector2;

public class ZigZagEnemy extends EntityGameObject {
	Random rand = new Random();
	boolean alive = true;
	private float speed = 2f;
	float xdir = rand.nextFloat(2);
	float ydir = rand.nextFloat(2);
	private BufferedImage asteroid;

	public ZigZagEnemy(float x, float y, ID id) {
		super(x, y, id);
		try {
			asteroid = ImageIO.read(new File("src/resources/asteroid.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (alive) {
			if (this.x <= 1 || this.x >= Game.WIDTH - 105) {
				xdir = -xdir;
			}
			if (this.y <= 1 || this.y >= Game.HEIGHT - 110) {
				ydir = -ydir;
			}
			
			super.setVelocity(new Vector2<Float>(speed * xdir, speed * ydir));
		} else {
			Handler.getHandler().removeObject(this);
		}
		collision();
	}

	private void collision() {
		for (int i = 0; i < Handler.getHandler().object.size(); i++) {
			GameObject tempObject = Handler.getHandler().object.get(i);
			if (tempObject.getId() == ID.Projectile) {
				// collision
				if (getBounds().intersects(tempObject.getBounds())) {
					Handler.getHandler().removeObject(tempObject);
					alive = false;
				}
			}
		}
	}

	public void render(Graphics g) {
		if (alive) {
			g.drawImage(asteroid, (int)x, (int)y, null);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect((int) x, (int) y, 16, 16, 16, 16);
		}
	}

	public Rectangle getBounds() {
		if (alive) {
			return new Rectangle((int) x, (int) y, 80, 80);
		} else {
			return new Rectangle(-100, -100, 16, 16);
		}
	}
}
//final version