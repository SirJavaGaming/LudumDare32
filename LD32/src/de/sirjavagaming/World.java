package de.sirjavagaming;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.team.Game;
import de.team.GameInterface;
import de.team.GameState;

public class World {
	private GameInterface instance;
	private Room[][] rooms;
	
	public void create() {
		this.rooms = new Room[31][31];
		this.instance = Game.getInstance();
		rooms[0][0] = new Room(this, 0, 0);
		rooms[0][0].create();
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
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			instance.setGameState(GameState.ROOM_SWITCH);
		}
		rooms[0][0].update();
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
			System.out.println(mssincestart + " " + alpha);
			graphics.setColor(1, 1, 1, 1-alpha);
			graphics.draw(ResourceManager.getTexture("black.png"), 0, 0, Game.WIDTH, Game.HEIGHT);
			graphics.setColor(1, 1, 1, 1);
		}
	}
	private boolean alreadyDone = false;
	private void updateRoomChange() {
		if(alreadyDone) return;
		alreadyDone = true;
	}

	public void render() {
		rooms[0][0].render();
		if(instance.getGameState() == GameState.ROOM_SWITCH) {
			updateAnim();
		}
	}
	
}
