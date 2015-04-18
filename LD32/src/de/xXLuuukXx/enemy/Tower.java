package de.xXLuuukXx.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import de.sirjavagaming.ResourceManager;
import de.team.Game;
import de.team.GameInterface;
import de.xXLuuukXx.Player;

public abstract class Tower extends Enemy {

	private Player player;

	private boolean shot;

	private int playerx, playery, kugelx, kugely;

	private int towerx = 300;
	private int towery = 200;

	private GameInterface instance;

	public Tower(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void create() {

		this.instance = Game.getInstance();

	}

	public void render() {
		SpriteBatch graphics = instance.getGraphics();
		graphics.draw(ResourceManager.getTexture("kugel.png"), x, y, 80, 100);
	}

	public void shot() {

//		if (shot == false) { // !shot tuts auch^^
//
//			shot = true;
//
//			playerx = player.getX();
//			playery = player.getY();
//
//			kugelx = towerx;
//			kugely = towery;
//
//			double dX = towerx - playerx;
//			double dY = towery - playery;
//			double dZ = 1 - 1; // 1 - 1 = 0 ?!
//
//			double yaw = Math.atan2(dZ, dX);
//
//			double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY)
//					+ Math.PI;
//
//			double X = Math.sin(pitch) * Math.cos(yaw);
//			double Y = Math.sin(pitch) * Math.sin(yaw);
//			double Z = Math.cos(pitch); // ich dachte wir machen nen 2D spiel,
//										// wofür dann die z-kompnente?!
//
//			float Vx = (float) X;
//			float Vy = (float) Y;
//			float Vz = (float) Z;
//
//			Vector3 vector = new Vector3(Vx, Vy, Vz);
//
//			// hast du das kopiert oder selber geschrieben?
//
//		}
		
		

	}

}
