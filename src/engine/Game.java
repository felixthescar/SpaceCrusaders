package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import player.KeyInput;
import ui.HUD;
import ui.Menu;
import ui.Window;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static int frames;
	public static final int WIDTH = 1600, HEIGHT = WIDTH / 16 * 9;
	
	Window window;
	private Thread thread;
	private boolean running = false;
	
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private BufferedImage bfImg;
    Clip clip;
    AudioInputStream sound;
	

	public static double fps = 0;
	
	public static enum STATE {
		Menu,
		Help,
		Game,
		End
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		hud = new HUD();
		menu = new Menu(this, hud);
		
        try {
            File file = new File("src/resources/beground.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		try {
			bfImg = ImageIO.read(new File("src/resources/galaxy.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addMouseListener(menu);
		this.addKeyListener(KeyInput.getKeyInput());
		
		window = new Window(WIDTH, HEIGHT, "Multi-ThreadedGame", this);
		
		spawner = new Spawn(hud);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println("This is "+thread.getName());
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

				g.drawImage(bfImg, -100, -120, null);

				Handler.getHandler().render(g);
				
				if(gameState == STATE.Game) {
					hud.render(g);
				} else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help) {
					menu.render(g);
				}
				
				g.dispose();
				bs.show();
				Game.fps++;
				
			}
		});
		
		System.out.println(t.getName());
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
				Handler.getHandler().clearEnemys();
				gameState = STATE.End;
				Handler.getHandler().clearPlayer();
			}
		} 
		if(gameState == STATE.Menu || gameState == STATE.End) {
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
//final version