package de.sirjavagaming.worldobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InvisBorder extends CollidableWorldObject{

	public InvisBorder(Rectangle border) {
		super(new Rectangle(border.x, border.y, border.width, border.height), (int)border.x, (int)border.y);
	}

	@Override
	public void render(SpriteBatch graphics) {
		// TODO Auto-generated method stub
		
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
