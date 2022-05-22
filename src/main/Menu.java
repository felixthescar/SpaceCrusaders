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
	
	// private Game game;
	private HUD hud;
	
	public Menu(Game game, HUD hud) {
		// this.game = game;
		this.hud = hud;
	}
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		//play
		if(mouseOver(mx, my, Game.WIDTH/2 - 120, Game.HEIGHT/4, 200, 64) && Game.gameState == STATE.Menu) {
			Game.gameState = STATE.Game;
			hud.setScore(0);
			hud.setLevel(1);
			Handler.getHandler().addObject(new Player((float)(Game.WIDTH/2-16), (float)(Game.HEIGHT/2-16), ID.Player));
			Handler.getHandler().addObject(new BasicEnemy(r.nextFloat((float)(Game.WIDTH-50)), r.nextFloat((float)(Game.HEIGHT-50)), ID.BasicEnemy));
		
		}
		//quit
		if(mouseOver(mx, my, Game.WIDTH/2 - 120, Game.HEIGHT/4 + 200, 200, 64) && Game.gameState == STATE.Menu) {
			System.exit(1);
		}

		//main menu from death
		if(mouseOver(mx, my, Game.WIDTH/2 - 120, Game.HEIGHT/4 + 100, 200, 64) && Game.gameState == STATE.End) {
			Game.gameState = STATE.Menu;
			for(int i = 0; i < Handler.getHandler().object.size(); i ++) {
				GameObject tempObj = Handler.getHandler().object.get(i);
				if(tempObj.getId() == ID.Player) {
					Handler.getHandler().removeObject(tempObj);
				}
			}
		}

		//help
		if(mouseOver(mx, my, Game.WIDTH/2 - 120, Game.HEIGHT/4 + 100, 200, 64) && Game.gameState == STATE.Menu) {
			Game.gameState = STATE.Help;
		}

		//main menu from help
		if(mouseOver(mx, my, Game.WIDTH/2 - 230, Game.HEIGHT-100, 460, 60) && Game.gameState == STATE.Help) {
			Game.gameState = STATE.Menu;
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
		if(Game.gameState == STATE.Menu) {
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
		} else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Move with W A S D.", 50, 50);
			g.drawString("Back to main menu", Game.WIDTH/2 - 230, Game.HEIGHT-50);
			g.drawRect(Game.WIDTH/2 - 230, Game.HEIGHT-100, 460, 60);
			g.drawString("For more info on the game visit:", 50, 100);
			g.drawString("https://github.com/felixthescar/APD-Project", 50, 150);

			fnt = new Font("arial", 1, 10);
			g.setFont(fnt);
			g.drawString("nota 10? :3", Game.WIDTH - 80, Game.HEIGHT-50);
		} else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Game Over", Game.WIDTH/2 - 155, Game.HEIGHT/4 - 60);


			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt2);
			g.drawString("Main Menu",Game.WIDTH/2 -105, Game.HEIGHT/4 + 143);
			g.drawRect(Game.WIDTH/2 - 120, Game.HEIGHT/4 + 100, 200, 64);
			

			Font fnt3 = new Font("arial", 1, 30);
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore()/100,20, Game.HEIGHT-200 + 145);
		}
	}
}
//final version