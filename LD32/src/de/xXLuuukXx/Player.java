package de.xXLuuukXx;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.Direction;
import de.sirjavagaming.ResourceManager;
import de.team.Game;
import de.team.GameInterface;
import de.team.GameState;
import de.xXLuuukXx.enemy.Projectile;

public class Player {

	private GameInterface instance;
	private Rectangle collisionBox;
	private int x;
	private int y;
	private int movementspeed = 8;
	private int lifes;
	private Direction current;
	private boolean moving = false;
	private boolean sharpShooting = false;
	private int fireDelay = 200;

	public void create() {
		this.collisionBox = new Rectangle(25, 10, 25, 70);
		this.instance = Game.getInstance();
		this.lifes = 10;
		current = Direction.DOWN;
		x = Game.WIDTH / 2 - 13;
		y = 720 / 2 - 30;

	}

	public void render() {
		SpriteBatch graphics = instance.getGraphics();

		// graphics.draw(ResourceManager.getTexture("black.png"), getCollisionBox().x, getCollisionBox().y, getCollisionBox().width, getCollisionBox().height);

		boolean b = (System.currentTimeMillis() % 200) < 100;
		if (moving) {
			switch (current) {
			case DOWN:
				if (b)
					graphics.draw(
							ResourceManager.getTexture("player/p_f_1.png"), x,
							y, 100, 100);
				else
					graphics.draw(
							ResourceManager.getTexture("player/p_f_2.png"), x,
							y, 100, 100);
				break;
			case LEFT:
				if (b)
					graphics.draw(
							ResourceManager.getTexture("player/p_l_1.png"), x,
							y, 100, 100);
				else
					graphics.draw(
							ResourceManager.getTexture("player/p_l_2.png"), x,
							y, 100, 100);
				break;
			case RIGHT:
				if (b)
					graphics.draw(
							ResourceManager.getTexture("player/p_r_1.png"), x,
							y, 100, 100);
				else
					graphics.draw(
							ResourceManager.getTexture("player/p_r_2.png"), x,
							y, 100, 100);
				break;
			case UP:
				if (b)
					graphics.draw(
							ResourceManager.getTexture("player/p_b_1.png"), x,
							y, 100, 100);
				else
					graphics.draw(
							ResourceManager.getTexture("player/p_b_2.png"), x,
							y, 100, 100);
				break;
			}
		} else {
			switch (current) {
			case DOWN:
				graphics.draw(ResourceManager.getTexture("player/p_f.png"), x,
						y, 100, 100);
				break;
			case LEFT:
				graphics.draw(ResourceManager.getTexture("player/p_l.png"), x,
						y, 100, 100);
				break;
			case RIGHT:
				graphics.draw(ResourceManager.getTexture("player/p_r.png"), x,
						y, 100, 100);
				break;
			case UP:
				graphics.draw(ResourceManager.getTexture("player/p_b.png"), x,
						y, 100, 100);
				break;
			}
		}

	}

	private long lastShot = 0;

	public void update() {
		if(lifes <= 0) {
			Game.getInstance().setGameState(GameState.DEAD);
		}
		moving = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			y += instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.UP);
			current = Direction.UP;
			moving = true;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			x -= instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.LEFT);
			current = Direction.LEFT;
			moving = true;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			y -= instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.DOWN);
			current = Direction.DOWN;
			moving = true;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			x += instance.getWorld().getCurrentRoom()
					.getMaxMovement(this, Direction.RIGHT);
			current = Direction.RIGHT;
			moving = true;
		}

		if (lastShot + fireDelay < System.currentTimeMillis() || sharpShooting) {
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				instance.getWorld()
						.getCurrentRoom()
						.addProjectile(
								new Projectile(this, Math.toRadians(270), x
										+ (int) collisionBox.getX()
										+ (int) collisionBox.getWidth() / 2, y
										+ (int) collisionBox.getY()
										+ (int) collisionBox.getHeight() / 2));
				lastShot = System.currentTimeMillis();
			} else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				instance.getWorld()
						.getCurrentRoom()
						.addProjectile(
								new Projectile(this, Math.toRadians(90), x
										+ (int) collisionBox.getX()
										+ (int) collisionBox.getWidth() / 2, y
										+ (int) collisionBox.getY()
										+ (int) collisionBox.getHeight() / 2));
				lastShot = System.currentTimeMillis();
			} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				instance.getWorld()
						.getCurrentRoom()
						.addProjectile(
								new Projectile(this, Math.toRadians(180), x
										+ (int) collisionBox.getX()
										+ (int) collisionBox.getWidth() / 2, y
										+ (int) collisionBox.getY()
										+ (int) collisionBox.getHeight() / 2));
				lastShot = System.currentTimeMillis();
			} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				instance.getWorld()
						.getCurrentRoom()
						.addProjectile(
								new Projectile(this, Math.toRadians(0), x
										+ (int) collisionBox.getX()
										+ (int) collisionBox.getWidth() / 2, y
										+ (int) collisionBox.getY()
										+ (int) collisionBox.getHeight() / 2));
				lastShot = System.currentTimeMillis();
			}
		}

	}

	public int getMovementspeed() {
		return movementspeed;
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x + collisionBox.getX(), y + collisionBox.getY(),
				collisionBox.getWidth() + collisionBox.getX(),
				collisionBox.getHeight() + collisionBox.getY());
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
