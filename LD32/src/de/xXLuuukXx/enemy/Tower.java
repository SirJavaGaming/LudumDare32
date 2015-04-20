package de.xXLuuukXx.enemy;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.ResourceManager;
import de.sirjavagaming.worldobjects.WorldObject;
import de.team.Game;
import de.team.GameState;
import de.xXLuuukXx.Player;

public class Tower extends Enemy {

	private int fireDelay;

	public Tower(int x, int y) {
		super(new Rectangle(0, 0, 100, 100), x, y);
	}

	@Override
	public void create() {
		double localDiff = Game.getInstance().getWorld().getCurrentRoom().getDifficulty();
		lifes = (int) (localDiff + 5);
		fireDelay = (int) (4000 - (2500 * (localDiff / 10)));
		System.out.println(fireDelay);
	}

	@Override
	public void render(SpriteBatch graphics) {
		graphics.draw(ResourceManager.getTexture("Tower.png"), x, y, 100, 100);
	}

	long lastShot = 0;

	@Override
	public void update() {
		if(Game.getInstance().getGameState() == GameState.DEAD) return;
		if (lastShot != 0) {
			if (lastShot + fireDelay < System.currentTimeMillis()) {
				lastShot = System.currentTimeMillis();
				shoot();
			}
		} else {
			Random r = new Random();
			lastShot = System.currentTimeMillis() - r.nextInt(fireDelay) + 1500;
		}
	}

	public void shoot() {
		Player p = Game.getInstance().getPlayer();
		double angle = Math.atan2(p.getCollisionBox().y - y, p.getCollisionBox().x - x);
		
		if (angle < 0) {
			angle += 2*Math.PI;
		}

		Game.getInstance().getWorld().getCurrentRoom().addProjectile(new Projectile(this ,angle, x + (int) collisionBox.getWidth()/2, y + (int)collisionBox.getHeight()/2));

		// if (shot == false) {
		//
		// shot = true;
		//
		// playerx = player.getX();
		// playery = player.getY();
		//
		// kugelx = towerx;
		// kugely = towery;
		//
		// double dX = towerx - playerx;
		// double dY = towery - playery;
		// double dZ = 1 - 1; // 1 - 1 = 0 ?!
		//
		// double yaw = Math.atan2(dZ, dX);
		//
		// double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY)
		// + Math.PI;
		//
		// double X = Math.sin(pitch) * Math.cos(yaw);
		// double Y = Math.sin(pitch) * Math.sin(yaw);
		// double Z = Math.cos(pitch);
		//
		//
		// float Vx = (float) X;
		// float Vy = (float) Y;
		// float Vz = (float) Z;
		//
		// Vector3 vector = new Vector3(Vx, Vy, Vz);
		//
		//
		//
		// }

	}

	public int getFireDelay() {
		return fireDelay;
	}

	public void setFireDelay(int fireDelay) {
		this.fireDelay = fireDelay;
	}

	@Override
	public WorldObject getReplacementForDead() {
		return new DestroyedTower(x, y);
	}

}
