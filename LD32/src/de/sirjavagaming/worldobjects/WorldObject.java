package de.sirjavagaming.worldobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class WorldObject {
	protected int x;
	protected int y;
	public WorldObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void render(SpriteBatch graphics);
	public abstract void update();
	public abstract void create();
}
