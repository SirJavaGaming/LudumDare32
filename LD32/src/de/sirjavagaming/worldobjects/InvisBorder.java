package de.sirjavagaming.worldobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InvisBorder extends CollidableWorldObject{

	public InvisBorder(Rectangle border) {
		super(new Rectangle(border.x, border.y, border.width, border.height), 0, 0);
	}

	@Override
	public void render(SpriteBatch graphics) {
//		graphics.draw(de.sirjavagaming.ResourceManager.getTexture("black.png"), getCollisionBox().x, getCollisionBox().y, getCollisionBox().width, getCollisionBox().height);
	
		
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
