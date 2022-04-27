package main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	private int enemies = 2;
	int i;
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep ++;
		
		if(scoreKeep >= 500) { //~15s
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			for(i = 0; i < enemies; i ++) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			enemies++;
			
		}
	}
}
