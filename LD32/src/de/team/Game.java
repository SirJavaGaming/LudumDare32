package de.team;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Game {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 900;
	public static final String TITLE = "MatitaShoot";
	
	private static GameInterface instance;
	
	public static void main(String[] args) {
		if(TITLE.equalsIgnoreCase("Name fehlt noch")) System.err.println("NAME FEHLT NOCH");
		if(args.length > 0 && args[0].equalsIgnoreCase("debug")) {
			System.setProperty("org.lwjgl.debug", "true");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.resizable = false;
		config.title = TITLE;
		config.vSyncEnabled = true;
		config.foregroundFPS = 60;
		instance = new GameInterface();
		new LwjglApplication(instance, config);
	}
	
	public static GameInterface getInstance() {
		return instance;
	}
}
