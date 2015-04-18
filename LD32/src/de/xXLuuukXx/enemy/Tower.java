package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.sirjavagaming.ResourceManager;
import de.sirjavagaming.worldobjects.WorldObject;
import de.team.Game;
import de.xXLuuukXx.Player;

public class Tower extends WorldObject {

	private int fireDelay;

	public Tower(int x, int y) {
		super(x, y);
	}

	@Override
	public void create() {
		fireDelay = 3000;

	}

	@Override
	public void render(SpriteBatch graphics) {
		graphics.draw(ResourceManager.getTexture("Tower.png"), x, y, 80, 100);

	}

	long lastShot = 0;

	@Override
	public void update() {
		if (lastShot != 0) {
			if (lastShot + fireDelay < System.currentTimeMillis()) {
				lastShot = System.currentTimeMillis();
				shoot();
			}
		} else {
			lastShot = System.currentTimeMillis();
		}
	}

	public void shoot() {
		Player p = Game.getInstance().getPlayer();
		double angle = Math.atan2(p.getCollisionBox().y - y, p.getCollisionBox().x - x);
		
		if (angle < 0) {
			angle += 2*Math.PI;
		}

		Game.getInstance().getWorld().getCurrentRoom().addProjectile(new Projectile(angle, x, y));

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

}
