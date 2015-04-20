package de.sirjavagaming;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.team.Game;
import de.team.GameInterface;
import de.team.GameState;
import de.xXLuuukXx.Player;

public class World {
	private GameInterface instance;
	private Room[][] rooms;
	
	private Room currentRoom;
	
	public void create() {
		this.rooms = new Room[31][31];
		this.instance = Game.getInstance();
		rooms[16][16] = new Room(this, 16, 16);
		currentRoom = rooms[16][16];
		rooms[16][16].create();
	}
	
	public Room[][] getRooms() {
		return rooms;
	}
	
	public Room getRoom(int x, int y) {
		if(x < 0 || x > 30 || y < 0 || y > 30) {
			return null;
		}
		return rooms[x][y];
	}

	public void update() {
		currentRoom.update();
		
		Rectangle botDoor = new Rectangle(580, 60, 100, 10);
		Rectangle topDoor = new Rectangle(580, 650, 100, 30);
		Rectangle leftDoor = new Rectangle(110,315,10,85);
		Rectangle rightDoor = new Rectangle(1170,320,10,80);
		
		Rectangle p = Game.getInstance().getPlayer().getCollisionBox();
		
		if(currentRoom.isDoorsOpen() && (p.overlaps(rightDoor) || p.overlaps(leftDoor) || p.overlaps(topDoor) || p.overlaps(botDoor))) {
			calcRoomSwitch();
		}
		
	}
	
	private void calcRoomSwitch() {
		Rectangle r = Game.getInstance().getPlayer().getCollisionBox();

		Rectangle botDoor = new Rectangle(580, 60, 100, 10);
		Rectangle topDoor = new Rectangle(580, 650, 100, 30);
		Rectangle leftDoor = new Rectangle(110,315,10,85);
		Rectangle rightDoor = new Rectangle(1170,320,10,80);

		if(r.overlaps(botDoor) && currentRoom.BOTTOM_DOOR) {
			Game.getInstance().setGameState(GameState.ROOM_SWITCH);
		} else if(r.overlaps(topDoor) && currentRoom.TOP_DOOR) {
			Game.getInstance().setGameState(GameState.ROOM_SWITCH);
		} else if(r.overlaps(rightDoor) && currentRoom.RIGHT_DOOR) {
			Game.getInstance().setGameState(GameState.ROOM_SWITCH);
		} else if(r.overlaps(leftDoor) && currentRoom.LEFT_DOOR) {
			Game.getInstance().setGameState(GameState.ROOM_SWITCH);
		}
		
	}

	private long animationStart = 0;
	private void updateAnim() {
		if(animationStart == 0) {
			animationStart = System.currentTimeMillis();
		}
		SpriteBatch graphics = instance.getGraphics();
		long mssincestart = System.currentTimeMillis() - animationStart;
		if(mssincestart > 490) {
			updateRoomChange();
		}
		if(mssincestart > 1000) {
			animationStart = 0;
			alreadyDone = false;
			instance.setGameState(GameState.PLAYING);
		} else {
			// -(x-500)²/250000
			float alpha = (float) (Math.pow(-(mssincestart-500), 2) / 250000);
			graphics.setColor(1, 1, 1, 1-alpha);
			graphics.draw(ResourceManager.getTexture("black.png"), 0, 0, Game.WIDTH, Game.HEIGHT);
			graphics.setColor(1, 1, 1, 1);
		}
	}
	private boolean alreadyDone = false;
	private void updateRoomChange() {
		if(alreadyDone) return;
		alreadyDone = true;
	
		Player p = Game.getInstance().getPlayer();
		
		Rectangle r = p.getCollisionBox();

		Rectangle botDoor = new Rectangle(580, 60, 100, 10);
		Rectangle topDoor = new Rectangle(580, 650, 100, 30);
		Rectangle leftDoor = new Rectangle(110,315,10,85);
		Rectangle rightDoor = new Rectangle(1170,320,10,80);

		if(r.overlaps(botDoor)) {
			p.setY(560);
			int nx = currentRoom.getX();
			int ny = currentRoom.getY() + 1;
			if(rooms[nx][ny] == null) {
				Room rn = new Room(this, nx, ny); 
				rn.create();
				rooms[nx][ny] = rn;
			}
			currentRoom = rooms[nx][ny];
		} else if(r.overlaps(topDoor)) {
			p.setY(100);
			int nx = currentRoom.getX();
			int ny = currentRoom.getY() - 1;
			if(rooms[nx][ny] == null) {
				Room rn = new Room(this, nx, ny); 
				rn.create();
				rooms[nx][ny] = rn;
			}
			currentRoom = rooms[nx][ny];
		} else if(r.overlaps(rightDoor)) {
			p.setX(120);
			int nx = currentRoom.getX() + 1;
			int ny = currentRoom.getY();
			if(rooms[nx][ny] == null) {
				Room rn = new Room(this, nx, ny); 
				rn.create();
				rooms[nx][ny] = rn;
			}
			currentRoom = rooms[nx][ny];
		} else if(r.overlaps(leftDoor)) {
			p.setX(1080);
			int nx = currentRoom.getX() - 1;
			int ny = currentRoom.getY();
			if(rooms[nx][ny] == null) {
				Room rn = new Room(this, nx, ny); 
				rn.create();
				rooms[nx][ny] = rn;
			}
			currentRoom = rooms[nx][ny];
		}
		
	}

	public void render() {
		currentRoom.render();
		if(instance.getGameState() == GameState.ROOM_SWITCH) {
			updateAnim();
		}
	}
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
}
