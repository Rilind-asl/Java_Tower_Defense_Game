package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Graphics2D;
import java.awt.Point;

public class CannonTower extends Towers 
{

	private int frameCounter = 0;
	public CannonTower(GameState game, Point position) 
	{
		
		super(game, position);
		this.image = ResourceLoader.getLoader().getImage("Cannon.png");
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
	        	Effects shot = new Cannonball(game, position, deltaX, deltaY);
	        	game.pendingAnimatable(shot);
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
