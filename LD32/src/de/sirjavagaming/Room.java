package de.sirjavagaming;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.team.Game;

public class Room {
	private World world;
	private int x;
	private int y;
	private double difficulty;
	
	public Room(World world, int x, int y) {
		this.x = x;
		this.y = y;
		this.world = world;
		calcDifficulty();
		calcDoors();
	}
	public void update() {
		
	}
	
	public void render() {
		SpriteBatch graphics = Game.getInstance().getGraphics();
		graphics.draw(new TextureRegion(ResourceManager.getTexture("room/testroom.png"), 1280, 720),0, 0); 

		if(TOP_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor.png"), 512, 464, 256, 256);
		if(LEFT_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor_rot.png"), 0, 232, 256, 256);
		if(BOTTOM_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor.png"), 512, 256, 256, -256);
		if(RIGHT_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor_rot.png"), 1280, 232, -256, 256);
	}
	
	
	private void calcDifficulty() {
		double distanceToCenter = Math.sqrt(Math.pow(Math.abs(x-15),2) + Math.pow(Math.abs(y-15),2));
		double parab = Math.pow(distanceToCenter, 2);
		double diff = parab / 50 + 1;
		this.difficulty = (double)(((int)(diff*100))/100.0);
	}
	
	public double getDifficulty() {
		return difficulty;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	private void calcDoors() {
		Random rand = new Random();
		if(y == 0) {
			TOP_DOOR = false;
		} else {
			Room r = world.getRoom(x, y - 1);
			if(r != null) {
				TOP_DOOR = r.BOTTOM_DOOR;
			} else {
				TOP_DOOR = rand.nextBoolean();
			}
		}
		if(y == 30) {
			BOTTOM_DOOR = false;
		} else {
			Room r = world.getRoom(x, y + 1);
			if(r != null) {
				BOTTOM_DOOR = r.TOP_DOOR;
			} else {
				BOTTOM_DOOR = rand.nextBoolean();
			}
		}
		if(x == 0) {
			LEFT_DOOR = false;
		} else {
			Room r = world.getRoom(x - 1, y);
			if(r != null) {
				LEFT_DOOR = r.RIGHT_DOOR;
			} else {
				LEFT_DOOR = rand.nextBoolean();
			}
		}
		if(x == 30) {
			RIGHT_DOOR = false;
		} else {
			Room r = world.getRoom(x + 1, y);
			if(r != null) {
				RIGHT_DOOR = r.LEFT_DOOR;
			} else {
				RIGHT_DOOR = rand.nextBoolean();
			}
		}
	}
	private boolean TOP_DOOR;
	private boolean RIGHT_DOOR;
	private boolean BOTTOM_DOOR;
	private boolean LEFT_DOOR;
}
