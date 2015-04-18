package de.sirjavagaming.worldobjects;

import com.badlogic.gdx.math.Rectangle;

public abstract class CollidableWorldObject extends WorldObject {
	
	protected Rectangle collisionBox;
	
	public CollidableWorldObject(Rectangle collisionBox, int x, int y) {
		super(x, y);
		this.collisionBox = collisionBox;
	}
	
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	
	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}
	
}
