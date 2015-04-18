package de.xXLuuukXx;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.Direction;
import de.sirjavagaming.ResourceManager;
import de.team.Game;
import de.team.GameInterface;

public class Player {

	private GameInterface instance;
	private Rectangle collisionBox;
	private int x;
	private int y;
	private int movementspeed = 8;
	private int lifes;

	public void create() {
		this.collisionBox = new Rectangle(0, 0, 80, 100);
		this.instance = Game.getInstance();
		this.lifes = 10;
		x = 500;
		y = 500;

	}

	public void render() {

		SpriteBatch graphics = instance.getGraphics();
		graphics.draw(ResourceManager.getTexture("icon.png"), x, y, 80, 100);

	}

	public void update() {

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			y += instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.UP);

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			x -= instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.LEFT);

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			y -= instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.DOWN);

		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			x += instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.RIGHT);

		}

	}
	
	public int getMovementspeed() {
		return movementspeed;
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x + collisionBox.getX(), y + collisionBox.getY(), collisionBox.getWidth() - collisionBox.getX(), collisionBox.getHeight() - collisionBox.getY());
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
	
	public int getLifes() {
		return lifes;
	}
	
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	
	public void damage(int lifes) {
		this.lifes -= lifes;
	}
}
