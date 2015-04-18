package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.sirjavagaming.worldobjects.WorldObject;

public abstract class Enemy extends WorldObject {
	
	public Enemy(int x, int y) {
		super(x, y);
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
	

	public abstract void render(SpriteBatch graphics);
	public abstract void update();
	public abstract void create();


}
