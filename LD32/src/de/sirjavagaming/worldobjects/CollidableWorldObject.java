package de.sirjavagaming.worldobjects;

import com.badlogic.gdx.math.Rectangle;

public abstract class CollidableWorldObject extends WorldObject {
	
	protected Rectangle collisionBox;
	
	public CollidableWorldObject(Rectangle collisionBox, int x, int y) {
		super(x, y);
		this.collisionBox = collisionBox;
	}
	
	
	public Rectangle getCollisionBox() {
		return new Rectangle(x + collisionBox.getX(), y + collisionBox.getY(), collisionBox.getWidth(), collisionBox.getHeight());
	}
	
	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}
	
}
