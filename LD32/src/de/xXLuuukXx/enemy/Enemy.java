package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.worldobjects.CollidableWorldObject;
import de.sirjavagaming.worldobjects.WorldObject;

public abstract class Enemy extends CollidableWorldObject {
	
	public Enemy(Rectangle collisionBox, int x, int y) {
		super(collisionBox, x, y);
	}
	
	protected int lifes;
	
	public int getLifes() {
		return lifes;
	}
	
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	
	public void damage(int damage) {
		lifes -= damage;
	}
	
	public abstract WorldObject getReplacementForDead();
	public abstract void render(SpriteBatch graphics);
	public abstract void update();
	public abstract void create();


}
