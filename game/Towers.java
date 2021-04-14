package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Towers implements Animatables
{
	protected BufferedImage image;
	protected GameState game;
	protected Point position;
	
	public Towers(GameState game, Point position)
	{
		
		this.game = game;
		this.position = position;
	}
}
