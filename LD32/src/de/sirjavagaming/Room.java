package de.sirjavagaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import de.sirjavagaming.worldobjects.CollidableWorldObject;
import de.sirjavagaming.worldobjects.InvisBorder;
import de.sirjavagaming.worldobjects.WorldObject;
import de.team.Game;
import de.xXLuuukXx.Player;
import de.xXLuuukXx.enemy.Enemy;
import de.xXLuuukXx.enemy.Projectile;
import de.xXLuuukXx.enemy.Tower;

public class Room {
	private World world;
	private int x;
	private int y;
	private double difficulty;
	private List<WorldObject> worldObjects;
	private List<Projectile> projectiles;
	private Array<Integer> zahlen = new Array<Integer>();
	private boolean exists;
	
	private boolean doorsOpen = false;
	
	public Room(World world, int x, int y) {
		this.x = x;
		this.y = y;
		this.world = world;
		calcDifficulty();
		calcDoors();
	}
	
	public void create() {		
		worldObjects = new ArrayList<WorldObject>();
		addWorldObject(new InvisBorder(new Rectangle(0, 0, 1280, 60))); //Down-Border
		addWorldObject(new InvisBorder(new Rectangle(0, 0, 100, 900))); //Left-Border
		addWorldObject(new InvisBorder(new Rectangle(1280-90, 100, 100, 900))); //Right-Border
		addWorldObject(new InvisBorder(new Rectangle(0, 900-20-180, 1280, 100))); //Top-Border
		
		Random rand = new Random();
		int x = rand.nextInt(4)+1;
		System.out.println("T�rme: " + x);
		
		for(int z = 1; z<=x; z++) {
		
			exists = true;
		 			
			while(exists) {
			
				int towers = rand.nextInt(8)+1;
				
				if(zahlen.contains(towers, false)) {
					
				} else {
				
					if(towers == 1) {
						addWorldObject(new Tower(200 ,180));
						zahlen.add(1);
					}
				
					if(towers == 2) {
						addWorldObject(new Tower(450 ,180));
						zahlen.add(2);
					}
		
					if(towers == 3) {
						addWorldObject(new Tower(700 ,180));
						zahlen.add(3);
					}
		
					if(towers == 4) {
						addWorldObject(new Tower(950 ,180));
						zahlen.add(4);
					}
			
					if(towers == 5) {
						addWorldObject(new Tower(200 ,460));
						zahlen.add(5);
					}
		
					if(towers == 6) {
						addWorldObject(new Tower(450 ,460));
						zahlen.add(6);
					}
				
					if(towers == 7) {
						addWorldObject(new Tower(700 ,460));
						zahlen.add(7);
					}
		
					if(towers == 8) {
						addWorldObject(new Tower(950 ,460));
						zahlen.add(8);
					
					}
				
				exists = false;
				
				System.out.println("Pl�tze: " + towers);
				
				}
			}
			
			
		
		}
		
		System.out.println(zahlen);
		zahlen.clear();
		
		projectiles = new ArrayList<Projectile>();
	}
	
	boolean b = false;
	public void update() {		
		if(Keyboard.isKeyDown(Keyboard.KEY_I)) {
			if(!b)
			doorsOpen=!doorsOpen;
			b = true;
		} else {
			b = false;
		}
	}
	
	public void render() {
		SpriteBatch graphics = Game.getInstance().getGraphics();
		graphics.draw(new TextureRegion(ResourceManager.getTexture("room/defaultroom3.png"), Game.WIDTH, Game.HEIGHT), 0 - 15 , 0, Game.WIDTH + 15, Game.HEIGHT);
		
		String s = doorsOpen ? "door_open" : "door_closed";
		
		if(TOP_DOOR) graphics.draw(ResourceManager.getTexture("room/" + s + ".png"), 560, 622, 256, 100);
		if(LEFT_DOOR) graphics.draw(ResourceManager.getTexture("room/" + s + "_rot.png"), 0, 190, 171, 230);
		if(BOTTOM_DOOR) graphics.draw(ResourceManager.getTexture("room/" + s + ".png"), 560, 99, 256, -99);
		if(RIGHT_DOOR) graphics.draw(ResourceManager.getTexture("room/" + s + "_rot.png"), 1280, 190, -156, 230);

		List<WorldObject> removeo = new ArrayList<WorldObject>();
		List<WorldObject> toAdd = new ArrayList<WorldObject>();
		doorsOpen = true;
		for(WorldObject object : worldObjects) {
			if(object instanceof Enemy) {
				Enemy e = ((Enemy)object);
				if(e.getLifes() <= 0) {
					removeo.add(e);
					toAdd.add(e.getReplacementForDead());
				} else {
					doorsOpen = false;
				}
			}
			object.update();
			object.render(graphics);
		}
		
		for(WorldObject o : toAdd) {
			addWorldObject(o);
		}
		worldObjects.removeAll(removeo);
		
		Player player = Game.getInstance().getPlayer();
		List<Projectile> remove = new ArrayList<Projectile>();
		for(Projectile p : projectiles) {
			if(p.getOwner() != player) {
			if(p.hit(player.getCollisionBox())) {
				player.damage(1);
				remove.add(p);
			}
			}
			for(WorldObject w : worldObjects) {
				if(w == p.getOwner()) continue;
				if(w instanceof CollidableWorldObject) {
					if(p.hit(((CollidableWorldObject)w).getCollisionBox())) {
						if(w instanceof Enemy) {
							((Enemy)w).damage(1);
						}
						remove.add(p);
					}
				}
			}
			p.update();
			p.render(graphics);
		}
		projectiles.removeAll(remove);
		
	}
	
	public void addWorldObject(WorldObject obj) {
		worldObjects.add(obj);
		obj.create();
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
	
	public boolean isDoorsOpen() {
		return doorsOpen;
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
	boolean TOP_DOOR;
	boolean RIGHT_DOOR;
	boolean BOTTOM_DOOR;
	boolean LEFT_DOOR;

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
	}
}
