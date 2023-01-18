package player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import javax.imageio.ImageIO;

import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.Vector2;
import entity.EntityGameObject;
import entity.Projectile;
import ui.HUD;

public class Player extends EntityGameObject{
	
	Projectile projectile;
	private int timer = 0;
	float playerSpeed = 1.5f;
	boolean[] keys;
	private BufferedImage player;
	

	public Player(float x, float y, ID id) {
		
		super(x, y, id);
		
		try {
			player = ImageIO.read(new File("src/resources/spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		keys = new boolean[]{ false, false, false, false};
		//movement
		Function<Integer, Void> moveInputPressed = key -> {
			Vector2<Float> vel = new Vector2<Float>(0f, 0f);
			if(key == KeyEvent.VK_W){ 
				vel.setY(-playerSpeed);
				if(keys[1])
					vel.setY(-playerSpeed * 2);
				keys[0] = true; 
			} 
			if(key == KeyEvent.VK_S){ 
				vel.setY(playerSpeed);  
				if(keys[0])
					vel.setY(playerSpeed * 2);
				keys[1] = true; 
			}
			if(key == KeyEvent.VK_A){ 
				vel.setX(-playerSpeed); 
				if(keys[1])
					vel.setX(-playerSpeed * 2);
				keys[2] = true; 
			} 
			if(key == KeyEvent.VK_D){ 
				vel.setX(playerSpeed);  
				if(keys[1])
					vel.setX(playerSpeed * 2);
				keys[3] = true; 
			}
			super.changeVelocity(vel);
			vel = super.getVelocity();
			if(vel.getX() > playerSpeed){
				vel.setX(playerSpeed);
			}
			if(vel.getX() < -playerSpeed){
				vel.setX(-playerSpeed);
			}
			if(vel.getY() > playerSpeed){
				vel.setY(playerSpeed);
			}
			if(vel.getY() < -playerSpeed){
				vel.setY(-playerSpeed);
			}
			super.setVelocity(vel);
			return null;
		};

		Function<Integer, Void> moveInputReleased = key -> {
			Vector2<Float> vel = new Vector2<Float>(super.getVelocity().getX(), super.getVelocity().getY());
			if(key == KeyEvent.VK_W){ 
				vel.setY(playerSpeed);
				if(keys[1])
					vel.setY(playerSpeed);
				keys[0] = false; 
			} 
			if(key == KeyEvent.VK_S){ 
				vel.setY(-playerSpeed);  
				if(keys[0])
					vel.setY(-playerSpeed);
				keys[1] = false; 
			}
			if(key == KeyEvent.VK_A){ 
				vel.setX(playerSpeed); 
				if(keys[3])
					vel.setX(playerSpeed);
				keys[2] = false; 
			} 
			if(key == KeyEvent.VK_D){ 
				vel.setX(-playerSpeed);  
				if(keys[2])
					vel.setX(-playerSpeed);
				keys[3] = false; 
			}
			if(key == KeyEvent.VK_SPACE) {
				attack();
			}
			if(!keys[0] && !keys[1]) vel.setY(0f);
			if(!keys[2] && !keys[3]) vel.setX(0f);
			super.setVelocity(vel);
			return null;
		};

		KeyInput.getKeyInput().addKeyPressedListener(moveInputPressed);
		KeyInput.getKeyInput().addKeyReleasedListener(moveInputReleased);
	}
	
	@Override
	public void tick() {
		super.tick();
		collision();
		timer ++;
		//if(timer%300 == 0) attack();
	}
	
	private void collision() {
		for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
			GameObject tempObject = Handler.getHandler().object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= .3;
				}
			}
			if(tempObject.getId() == ID.ZigZagEnemy) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= .3;
				}
			}
		}
	}
	
	public void attack() {
		for(int i = 1; i <= 8; i ++) {
			Handler.getHandler().addObject(new Projectile((float)x+50, (float)y+56, ID.Projectile, i));
		}
	}
	

	public void render(Graphics g) {
		g.drawImage(player, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 60, 80);
	}
}
//final version