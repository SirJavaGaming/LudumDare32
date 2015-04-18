package de.sirjavagaming.worldobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import de.sirjavagaming.ResourceManager;

public class DemoCollider extends CollidableWorldObject {

	public DemoCollider(int x, int y) {
		super(new Rectangle(0, 0, 32, 32), x, y);
	}

	@Override
	public void render(SpriteBatch graphics) {
		graphics.setColor(1, 0, 0, 1);
		graphics.draw(ResourceManager.getTexture("black.png"), x + collisionBox.x, y + collisionBox.y, collisionBox.width, collisionBox.height);
		graphics.setColor(1,1,1,1);
		
	}

	@Override
	public void update() {
	}

	@Override
	public void create() {		
	}

}
