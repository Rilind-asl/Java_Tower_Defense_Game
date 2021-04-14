package game;

import java.awt.Point;

/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */

abstract public class Enemy implements Animatables
{

	protected double percentage;
	protected Path path;
	protected GameState game;
	
	public Enemy(String pathname, GameState game)
	{
		
		this.percentage = 0.0;
		this.path = ResourceLoader.getLoader().getPath(pathname);
		this.game = game;
	}
	/**
	 * Returns the location of this enemy on the path.
	 * @return a Point object that corresponds to the location of this enemy
	 */
	public Point getLocation() 
	{
		return path.getPathPosition(percentage);
	}
}
