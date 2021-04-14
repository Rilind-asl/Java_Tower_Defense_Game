package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class impEnemy extends Enemy implements Animatables
{
	private BufferedImage image;
	
	public impEnemy(String pathname, GameState game) 
	{
		super(pathname, game);
		
		this.image = ResourceLoader.getLoader().getImage("imp.png");

	}
	
	@Override
	public void draw(Graphics2D g) {

		// getting path position
		
        Point c = path.getPathPosition(percentage);
        
        // drawing my enemy
        
        g.drawImage(image, c.x-image.getWidth()/2, c.y-image.getHeight()/2, null);
	}
	@Override
	public void update() 
	{

    	percentage += 0.001;
    	if(percentage > 1)
    	{
    		game.lives--;
    		if(game.lives < 0)
    		{
    			game.lives = 0;
    		}
    		game.removeAnimatable(this);
    		percentage = 0;
    	}
	}
	
}
