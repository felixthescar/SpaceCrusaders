package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Game;
import engine.Handler;
import engine.ID;
import engine.Vector2;

public class Projectile extends EntityGameObject{
	
	private float speed = 4;
	
	public Projectile(float x, float y, ID id, int dir) {
		super(x, y, id);
		Vector2<Float> vel = new Vector2<Float>(0f, 0f);
		
		switch(dir) {
			case 1: {vel.setX(-speed); vel.setY(-speed);break;} //stanga sus 
			case 2: {vel.setX(0f); vel.setY(-speed);break;} //sus			
			case 3: {vel.setX(speed);vel.setY(-speed);break;} //dreapta sus
			case 4: {vel.setX(speed); vel.setY(0f);break;} //dreapta
			case 5: {vel.setX(speed); vel.setY(speed); break;} //dreapta jos
			case 6: {vel.setX(0f); vel.setY(speed);break;} //jos
			case 7: {vel.setX(-speed); vel.setY(speed);break;} //stanga jos
			case 8: {vel.setX(-speed); vel.setY(0f);break;} //stanga 
		}
		super.setVelocity(vel);
	}

	@Override
	public void tick() {
		super.tick();
		if ((this.x <= 0 || this.x >= Game.WIDTH- 10) || (this.y <= 0 || this.y >= Game.HEIGHT - 10)) {
			Handler.getHandler().removeObject(this);
		}
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
}
//final version