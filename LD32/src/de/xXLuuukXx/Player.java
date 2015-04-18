package de.xXLuuukXx;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.ResourceManager;
import de.team.Game;
import de.team.GameInterface;
import de.xXLuuukXx.enemy.Tower;


public class Player {
	
	private GameInterface instance;
	private Rectangle collisionBox;
	private int x;
	private int y;
	private int movementspeed = 8;
	
	public void create() {
		
		this.instance = Game.getInstance();
		collisionBox = new Rectangle(2,2,16,16);
		x = 500;
		y = 500;
		
		
	}
	
	public void render() {
		
	SpriteBatch graphics = instance.getGraphics();	
	graphics.draw(ResourceManager.getTexture("icon.png"), x, y, 80, 100);	
	
	
	
	}
	
	public void update() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			y += movementspeed;
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			x -= movementspeed;
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			y -= movementspeed;
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			x += movementspeed;
			
		}
		
		
	}
	
	public int getMovementspeed() {
		return movementspeed;
	}
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setMovementspeed(int movementspeed) {
		this.movementspeed = movementspeed;
	}
	
	
	
	

}
