package game;
import java.awt.Graphics2D;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Effects implements Animatables
{
	protected GameState game;
	protected Point position;
	protected BufferedImage image;
	
	public Effects(GameState game, Point position)
	{
		this.game = game;
		this.position = position;
	}
	public void draw (Graphics2D g) 
	{
        Point c = getLocation();                 
        g.drawImage(image,  c.x-image.getWidth()/2, c.y-image.getHeight()/2, null);
	}
	
	public Point getLocation () {
		return position;
		}
}
