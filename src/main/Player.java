package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.function.Function;
import java.awt.event.KeyEvent;

public class Player extends EntityGameObject{
	
	Projectile projectile;
	
	private int timer = 0;
	float playerSpeed = 3f;
	boolean[] keys;

	public Player(float x, float y, ID id) {
		super(x, y, id);
		keys = new boolean[]{ false, false, false, false};
		//
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
			if(!keys[0] && !keys[1]) vel.setY(0f);
			if(!keys[2] && !keys[3]) vel.setX(0f);
			super.setVelocity(vel);
			return null;
		};

		KeyInput.getKeyInput().addKeyPressedListener(moveInputPressed);
		KeyInput.getKeyInput().addKeyReleasedListener(moveInputReleased);
	}

	// for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
		// 	GameObject tempObject = Handler.getHandler().object.get(i);
			
		// 	// if(tempObject.getId() == ID.Player) {
		// 	// 	//key events
		// 	// 	if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); keyDown[0] = true;}
		// 	// 	if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[1] = true;}
		// 	// 	if(key == KeyEvent.VK_S) {tempObject.setVelY(5); keyDown[2] = true; }
		// 	// 	if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[3] = true; }
		// 	// }
		// }

		// for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
		// 	GameObject tempObject =  Handler.getHandler().object.get(i);
			
		// 	if(tempObject.getId() == ID.Player) {
		// 		//key events
		// 		if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
		// 		if(key == KeyEvent.VK_A) keyDown[1] = false;//tempObject.setVelX(0);
		// 		if(key == KeyEvent.VK_S) keyDown[2] = false;//tempObject.setVelY(0);
		// 		if(key == KeyEvent.VK_D) keyDown[3] = false;//tempObject.setVelX(0);
				
		// 		//vertical movement
		// 		//w and s
		// 		if(!keyDown[0] && !keyDown[2]) tempObject.setVelY(0);
		// 		//horizontal movement
		// 		//a and d
		// 		if(!keyDown[1] && !keyDown[3]) tempObject.setVelX(0);
		// 	}
		// }
	@Override
	public void tick() {
		super.tick();
		/*x += velX;
		y += velY;
		
		x = Game.clamp((int)x,  0,  Game.WIDTH-50);
		y = Game.clamp((int)y,  0,  Game.HEIGHT-73);*/
		collision();
		timer ++;
		if(timer%100000 == 0) attack();
	}
	
	private void collision() {
		for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
			GameObject tempObject = Handler.getHandler().object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) {
				//collision
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
	public void attack() {
		for(int i = 1; i <= 8; i ++) {
			Handler.getHandler().addObject(new Projectile((float)x+12, (float)y+12, ID.Projectile, i));
		}
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 2));
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 3));
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 4));
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 5));
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 6));
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 7));
		// handler.addObject(new Projectile((float)x, (float)y, ID.Projectile, 8));
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,  (int)y,  32,  32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
