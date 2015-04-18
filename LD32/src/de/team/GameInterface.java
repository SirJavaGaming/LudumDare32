package de.team;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.sirjavagaming.World;

public class GameInterface implements ApplicationListener {
	
	private GameState gameState;
	
	private SpriteBatch graphics;
	
	private World world;
	
	@Override
	public void create() {
		setGameState(GameState.PLAYING);
		
		
		OrthographicCamera cam = new OrthographicCamera(Game.WIDTH, Game.HEIGHT);
		cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
		
		graphics = new SpriteBatch();
		graphics.setProjectionMatrix(cam.combined);
		
		this.world = new World();
		world.create();
	}
	
	@Override
	public void render() {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		graphics.begin();
		graphics.enableBlending();
		switch (gameState) {
		case PLAYING:
			world.update();
			world.render();
			break;
		case ROOM_SWITCH:
			world.update();
			world.render();
			break;
		}
		graphics.end();
	}
	
	public SpriteBatch getGraphics() {
		return graphics;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void resize(int width, int height) {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void dispose() {}
}
