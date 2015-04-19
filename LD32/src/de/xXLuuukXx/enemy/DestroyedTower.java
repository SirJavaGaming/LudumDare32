package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.sirjavagaming.ResourceManager;
import de.sirjavagaming.worldobjects.WorldObject;

public class DestroyedTower extends WorldObject {

	public DestroyedTower(int x, int y) {
		super(x, y);
	}

	@Override
	public void render(SpriteBatch graphics) {
		graphics.draw(ResourceManager.getTexture("Tower_d.png"), x, y, 100, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

}
