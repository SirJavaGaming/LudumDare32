package de.team;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameInterface implements ApplicationListener {
	
	private GameState gameState;
	
	private SpriteBatch graphics;
	
	@Override
	public void create() {
		setGameState(GameState.PLAYING);
		
		
		OrthographicCamera cam = new OrthographicCamera(Game.WIDTH, Game.HEIGHT);
		cam.setToOrtho(false, Game.WIDTH, Game.HEIGHT);
		
		graphics = new SpriteBatch();
		graphics.setProjectionMatrix(cam.combined);
		
	}
	
	@Override
	public void render() {
		switch (gameState) {
		case PLAYING:
			
			break;
		}
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
