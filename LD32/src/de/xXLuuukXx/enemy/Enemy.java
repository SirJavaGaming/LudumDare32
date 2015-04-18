package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Enemy {
	
	protected int x;
	protected int y;
	protected int lifes;
	public Enemy(int x, int y) {
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
