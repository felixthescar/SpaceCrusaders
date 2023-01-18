package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.Vector2;

public class BasicEnemy extends EntityGameObject {
	private GameObject player;
	boolean alive = true;
	private float speed;
	Vector2<Float> dir;
	private BufferedImage bsEnemy;

	public BasicEnemy(float x, float y, ID id) {
		super(x, y, id);
		try {
			bsEnemy = ImageIO.read(new File("src/resources/basic_enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		speed = 5;
		for (int i = 0; i < Handler.getHandler().object.size(); i++) {
			if (Handler.getHandler().object.get(i).getId() == ID.Player)
				player = Handler.getHandler().object.get(i);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (alive) {
			dir = new Vector2<Float>(0f, 0f);
			float x = player.getX() - this.x;
			float y = player.getY() - this.y;
			float hyp = (float) Math.sqrt(x * x + y * y);
			x /= hyp;
			y /= hyp;
			Vector2<Float> vel = new Vector2<Float>(x * speed / 5.5f, y * speed / 5.5f);
			super.setVelocity(vel);
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
			g.drawImage(bsEnemy, (int)x, (int)y, null);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect((int) x, (int) y, 16, 16, 16, 16);
		}
	}

	public Rectangle getBounds() {
		if (alive) {
			return new Rectangle((int) x, (int) y, 75, 55);
		} else {
			return new Rectangle(-100, -100, 16, 16);
		}
	}
}
//final version