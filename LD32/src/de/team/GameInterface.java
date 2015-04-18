package de.team;

import com.badlogic.gdx.ApplicationListener;

public class GameInterface implements ApplicationListener {
	
	private GameState gameState;
	
	@Override
	public void create() {
		setGameState(GameState.PLAYING);
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
