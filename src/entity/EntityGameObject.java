package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Game;
import engine.GameObject;
import engine.Handler;
import engine.ID;
import engine.Vector2;

public class EntityGameObject extends GameObject {
	protected Vector2<Float> velocity;

	public EntityGameObject(float x, float y, ID id) {
		super(x, y, id);
		velocity = new Vector2<Float>(0f, 0f);
		Handler.getHandler().object.add(this);
	}

	public void handleVelocity() {

		x += velocity.getX();
		y += velocity.getY();

		x = Game.clamp(x, 0, Game.WIDTH-10);
		y = Game.clamp(y, 0, Game.HEIGHT-25);
		
		if (this.id == ID.Player) {
			x = Game.clamp(x, 0, Game.WIDTH - 45);
			y = Game.clamp(y, 0, Game.HEIGHT - 70);

		}

	}

	@Override
	public void tick() {
		handleVelocity();
	}

	@Override
	public void render(Graphics g) {

	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	public void changeVelocity(Vector2<Float> change) {
		velocity.setX(velocity.getX() + change.getX());
		velocity.setY(velocity.getY() + change.getY());
	}

	public void setVelocity(Vector2<Float> newVelocity) {
		velocity = newVelocity;
	}

	public Vector2<Float> getVelocity() {
		return velocity;
	}

}
//final version