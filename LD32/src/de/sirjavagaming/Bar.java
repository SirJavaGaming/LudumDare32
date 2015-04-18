package de.sirjavagaming;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.team.Game;
import de.xXLuuukXx.Player;

public class Bar {
	
	public void render() {
		SpriteBatch graphics = Game.getInstance().getGraphics();
		Player p = Game.getInstance().getPlayer();
		
		int hd = 0;
		
		int x = p.getLifes();
		
		int offset = 0;
		
		for(int i = 1; i < x; i+=2) {
			graphics.draw(ResourceManager.getTexture("heart.png"), 40 + offset, 800, 40, 40);
			offset += 40;
			hd++;
		}
		
		if ((x & 1) == 1) {
			graphics.draw(ResourceManager.getTexture("heart_h.png"), 40 + offset, 800, 40, 40);
			offset += 40;
			hd++;
		}
		
		for(int i = hd; i < 5; i++) {
			graphics.draw(ResourceManager.getTexture("heart_d.png"), 40 + offset, 800, 40, 40);
			offset += 40;
		}
		
//		graphics.draw
	}
}
