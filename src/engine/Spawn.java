package engine;

import java.util.Random;

import entity.BasicEnemy;
import entity.ZigZagEnemy;
import ui.HUD;

public class Spawn {
	private HUD hud;
	private Random r = new Random();
	int positionOfSpawning;
	
	int i;
	public Spawn(HUD hud) {
		this.hud = hud;
	}
	
	public void tick() {
		if(Game.frames >= 400) { //~4s
			Game.frames = 0;
			hud.setLevel(hud.getLevel() + 1);
			for(i = 0; i < hud.getLevel(); i ++) {
				positionOfSpawning = r.nextInt(2);
				if(positionOfSpawning==1) {
					Handler.getHandler().addObject(new BasicEnemy(10, r.nextInt(Game.HEIGHT), ID.BasicEnemy));
				} else {
					Handler.getHandler().addObject(new BasicEnemy(Game.WIDTH-10, r.nextInt(Game.HEIGHT), ID.BasicEnemy));
				}
				
			}
			Handler.getHandler().addObject(new ZigZagEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.ZigZagEnemy));
		}
	}
}
//final version