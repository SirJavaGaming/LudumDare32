package de.sirjavagaming;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.worldobjects.CollidableWorldObject;
import de.sirjavagaming.worldobjects.DemoCollider;
import de.sirjavagaming.worldobjects.WorldObject;
import de.team.Game;
import de.xXLuuukXx.Player;

public class Room {
	private World world;
	private int x;
	private int y;
	private double difficulty;
	private ArrayList<WorldObject> worldObjects;
	
	public Room(World world, int x, int y) {
		this.x = x;
		this.y = y;
		this.world = world;
		calcDifficulty();
		calcDoors();
	}
	
	public void create() {
		worldObjects = new ArrayList<WorldObject>();
		worldObjects.add(new DemoCollider(100, 100));
	}
	
	public void update() {
		
	}
	
	public void render() {
		SpriteBatch graphics = Game.getInstance().getGraphics();
		graphics.draw(new TextureRegion(ResourceManager.getTexture("room/defaultroom.png"), 1280, 720),0, 0);
		graphics.draw(ResourceManager.getTexture("Tower.png"), 500, 500, 70, 70);

		if(TOP_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor.png"), 512, 464, 256, 256);
		if(LEFT_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor_rot.png"), 0, 232, 256, 256);
		if(BOTTOM_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor.png"), 512, 256, 256, -256);
		if(RIGHT_DOOR) graphics.draw(ResourceManager.getTexture("room/testdoor_rot.png"), 1280, 232, -256, 256);
		
		for(WorldObject object : worldObjects) {
			object.update();
			object.render(graphics);
		}
	}
	
	public int getMaxMovement(Player p, Direction d) {
//		Rectangle absolutePlayerBox = new Rectangle(p.getX() + p.getCollisionBox().getX(), p.getY() + p.getCollisionBox().getY(), p.getX() + p.getCollisionBox().getWidth(), p.getY() + p.getCollisionBox().getHeight());
//		switch (d) {
//		case DOWN:
//			for(WorldObject o : worldObjects) {
//				int maxMove = p.getMovementspeed();
//				if(o instanceof CollidableWorldObject) {
//					CollidableWorldObject obj = (CollidableWorldObject)o;
//					int x = (int) (p.getY() - (obj.getCollisionBox().getY() + obj.getCollisionBox().getHeight()));
//					if(x > 0 && x < p.getMovementspeed()) {
//						if(x < maxMove) {
//							maxMove = x;
//						}
//					}
//				}
//				return maxMove;
//			}
//			break;
//			p.getCollisionBox().
//		case UP:
//			for(WorldObject o : worldObjects) {
//				int maxMove = p.getMovementspeed();
//				if(o instanceof CollidableWorldObject) {
//					CollidableWorldObject obj = (CollidableWorldObject)o;
//					System.out.println(p.getX() + " " + p.getY() + " " + obj.getX() + " " + obj.getY());
//					int x = (int) (obj.getCollisionBox().getY() - (p.getCollisionBox().getY() + p.getCollisionBox().getHeight()));
//					if(x > 0 && x < p.getMovementspeed()) {
//						if(x < maxMove) {
//							maxMove = x;
//						}
//					}
//				}
//				return maxMove;
//			}
//			break;
//		case RIGHT:
//			for(WorldObject o : worldObjects) {
//				int maxMove = p.getMovementspeed();
//				if(o instanceof CollidableWorldObject) {
//					CollidableWorldObject obj = (CollidableWorldObject)o;
//					int x = (int) (obj.getCollisionBox().getX() - (p.getCollisionBox().getX() + p.getCollisionBox().getWidth()));
//					if(x > 0 && x < p.getMovementspeed()) {
//						if(x < maxMove) {
//							maxMove = x;
//						}
//					}
//				}
//				return maxMove;
//			}
//			break;
//		case LEFT:
//			for(WorldObject o : worldObjects) {
//				int maxMove = p.getMovementspeed();
//				if(o instanceof CollidableWorldObject) {
//					CollidableWorldObject obj = (CollidableWorldObject)o;
//					int x = (int) (obj.getCollisionBox().getX() - (p.getCollisionBox().getX() + p.getCollisionBox().getWidth()));
//					if(x > 0 && x < p.getMovementspeed()) {
//						if(x < maxMove) {
//							maxMove = x;
//						}
//					}
//				}
//				return maxMove;
//			}
//			break;
//		default:
//			break;
//		}
		
		int maxMovement = p.getMovementspeed();
		for(WorldObject o : worldObjects) {
			if(o instanceof CollidableWorldObject) {
				CollidableWorldObject obj = (CollidableWorldObject)o;
				for(int i = p.getMovementspeed(); i >= 0; i--) {
					Rectangle newPlayerBox = p.getCollisionBox();
					switch (d) {
					case UP:
						newPlayerBox.setY(newPlayerBox.getY() + i);
						break;
					case DOWN:
						newPlayerBox.setY(newPlayerBox.getY() - i);
						break;
					case LEFT:
						newPlayerBox.setX(newPlayerBox.getX() - i);
						break;
					case RIGHT:
						newPlayerBox.setX(newPlayerBox.getX() + i);
						break;
						
					}
					if(obj.getCollisionBox().overlaps(newPlayerBox)) {
						if(i == 0) return 0;
						if(maxMovement > i) maxMovement = i;
					} else {
						break;
					}
				}
			}
		}
		
		return maxMovement-1;
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
