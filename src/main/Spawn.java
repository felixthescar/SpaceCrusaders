package main;

import java.util.Random;

public class Spawn {
	private HUD hud;
	private Random r = new Random();
	
	private int enemies = 2;
	int i;
	public Spawn(HUD hud) {
		this.hud = hud;
	}
	
	public void tick() {
		
		if(Game.frames >= 500) { //~5s
			Game.frames = 0;
			hud.setLevel(hud.getLevel() + 1);
			for(i = 0; i < enemies; i ++) {
				Handler.getHandler().addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
			}
			enemies++;
			
		}
	}
}
