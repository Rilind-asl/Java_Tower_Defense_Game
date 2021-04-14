package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class RantzTower extends Towers {
	
	private int frameCounter = 0;
	
	public RantzTower(GameState game, Point position) 
	{
		super(game, position);
		
		this.image = ResourceLoader.getLoader().getImage("rantz.png");
	}

	@Override
	public void update() 
	{
		
		frameCounter++;
		if(frameCounter % 30 != 0)
		{
			return;
		}
        Enemy enemy = game.findNearestEnemy(position);
        
        if (enemy == null)
        {
        	return;                 
        }
        
        if (enemy.getLocation().distance(position) < 50)         
        {
        	int deltaX = enemy.getLocation().x - position.x;
        	int deltaY = enemy.getLocation().y - position.y;
        	Effects arrow = new Arrow(game, position, deltaX, deltaY);
        	game.pendingAnimatable(arrow);
        	game.removeAnimatable(enemy);
        	Effects dead = new dead(game, enemy.getLocation());
        	game.pendingAnimatable(dead);
        }
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(image, position.x, position.y, null);
		
	}


}
