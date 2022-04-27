package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.Game.STATE;

public class Menu extends MouseAdapter{
	private int mx, my;
	private Random r = new Random();
	
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		//play
		if(mouseOver(mx, my, Game.WIDTH/2 - 120, Game.HEIGHT/4, 200, 64) && game.gameState == STATE.Menu) {
			game.gameState = STATE.Game;
			handler.addObject(new Player((float)(Game.WIDTH/2-16), (float)(Game.HEIGHT/2-16), ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextFloat((float)(Game.WIDTH-50)), r.nextFloat((float)(Game.HEIGHT-50)), ID.BasicEnemy, handler));
		
		}
		//quit
		if(mouseOver(mx, my, Game.WIDTH/2 - 120, Game.HEIGHT/4 + 200, 200, 64) && game.gameState == STATE.Menu) {
			System.exit(1);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 35);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Menu", Game.WIDTH/2 - 85, Game.HEIGHT/4 - 60);
			
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2 - 120, Game.HEIGHT/4, 200, 64);
			g.drawString("Play",Game.WIDTH/2 - 55, Game.HEIGHT/4 + 45);
			
			g.drawRect(Game.WIDTH/2 - 120, Game.HEIGHT/4 + 100, 200, 64);
			g.drawString("Help",Game.WIDTH/2 - 55, Game.HEIGHT/4 + 145);

			g.drawRect(Game.WIDTH/2 - 120, Game.HEIGHT/4 + 200, 200, 64);
			g.drawString("Quit",Game.WIDTH/2 - 55, Game.HEIGHT/4 + 245);
		} else if(game.gameState == STATE.Help) {
			
		} else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Game Over", Game.WIDTH/2 - 85, Game.HEIGHT/4 - 60);
			
			g.drawRect(Game.WIDTH/2 - 120, Game.HEIGHT/4 + 100, 200, 64);
			g.drawString("You lost with a score of: " + hud.getScore(),Game.WIDTH/2 - 55, Game.HEIGHT/4 + 145);
		}
		
	}
}
