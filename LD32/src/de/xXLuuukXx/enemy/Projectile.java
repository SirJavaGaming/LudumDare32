package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import de.sirjavagaming.ResourceManager;
import de.sirjavagaming.worldobjects.WorldObject;

public class Projectile extends WorldObject {

	private double direction;
	private int speed;
	
	private Vector2 position;
	
	private int size = 32;
	
	private Object owner;
	
	
	public Projectile(Object owner, double direction, int x, int y) {
		super(x, y);
		this.direction = direction;
		this.speed = 10;
		
		this.owner = owner;
		
		position = new Vector2(x, y);
		
		ResourceManager.playSoundEffect("schuss.wav", 0.2f);
		
	}

	@Override
	public void render(SpriteBatch graphics) {
		graphics.draw(ResourceManager.getTexture("Kugel.png"), x - size/2, y - size/2, size, size);
		
	}

	@Override
	public void update() {
		position.set(position.x + (float)Math.cos(direction) * speed, position.y + (float)Math.sin(direction) * speed);
		this.x = (int) position.x;
		this.y = (int) position.y;
	}
	
	@Override
	public int getX() {
		return (int) position.x;
	}
	
	@Override
	public int getY() {
		return (int) position.y;
	}
	
	@Override
	public void setX(int x) {
		position.x = x;
		super.setX(x);
	}
	
	@Override
	public void setY(int y) {
		position.y = y;
		super.setY(y);
	}

	public boolean hit(Rectangle r2) {
		Rectangle r = new Rectangle(x, y, size, size);
		boolean b = r2.overlaps(r);
		if(b) {
			return true;
		}
		return false;
	}
	
	public Object getOwner() {
		return owner;
	}
	
	public void setOwner(ProjectileOwner owner) {
		this.owner = owner;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
