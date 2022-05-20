package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static int frames;
	public static final int WIDTH = 1366, HEIGHT = WIDTH / 16 * 9;
	
	Window window;
	private Thread thread;
	private boolean running = false;
	
	private HUD hud;
	private Spawn spawner;
	private Menu menu;

	public static double fps = 0;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End
	}
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		hud = new HUD();
		menu = new Menu(this, hud);

		this.addMouseListener(menu);
		this.addKeyListener(KeyInput.getKeyInput());
		
		window = new Window(WIDTH, HEIGHT, "Multi-ThreadedGame", this);
		
		spawner = new Spawn(hud);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println(thread.getName());
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double ammountOfTicks = 60.0;
		double ns = 1000000000 / ammountOfTicks;
		double ups = 0;
		double delta = 0;
		long timer = System.currentTimeMillis();
		this.createBufferStrategy(3);
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			
			return;
		}
		//int frames = 0;
		Thread t = new Thread(() -> {
			while(running){
				
				
				
				Graphics g = bs.getDrawGraphics();
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0,  0,  WIDTH, HEIGHT);
				
				
				Handler.getHandler().render(g);
				
				if(gameState == STATE.Game) {
					hud.render(g);
				} else if(gameState == STATE.Menu) {
					menu.render(g);
				}
				
				g.dispose();
				bs.show();
				Game.fps++;
				
			}
		});
		
		System.out.println(t.getName());
		//
		boolean renderStarted = false;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
				ups ++;
			}
			if(!renderStarted)
			{
				renderStarted = true;
				t.start();
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				window.changeTitle("FPS: " + Game.fps + "; UPS: " + ups);
				ups = 0;
				Game.fps = 0;
			}
		}
		t.interrupt();
		stop();
	}
	
	private void tick() {
		frames++;
		Handler.getHandler().tick();
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				gameState = STATE.End;
			}
		} else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
		
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
