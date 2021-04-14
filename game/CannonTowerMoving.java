package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class CannonTowerMoving extends Effects 
{
	//private image variable
	private BufferedImage image;
	
	public CannonTowerMoving(GameState game, Point position) 
	{
		// calling super class
		super(game,position);
		
		// loading the image of tower
		this.image = ResourceLoader.getLoader().getImage("Cannon.png");
		
	}

	@Override
	public void update() 
	{
		// getting mouse position
		position = game.getMousePos();
		
		// if statement requirements are met it will add a tower to mouse
		// if pressed again on game area it will add tower.
		if(game.getMousePressed() && game.credits >= 10 && game.getMousePos().x < 600)
		{
			game.clearMousePressed();
			game.pendingAnimatable(new CannonTower(game, position));
			game.adjustCredits(-10);
			game.removeAnimatable(this);
		}
		
		// if pressed but can't purchase cause of zero credits it will
		// not allow any placements of tower.
		else if(game.getMousePressed() && game.credits < 10)
		{
			System.out.println("Not enough Credits");
			game.clearMousePressed();
			game.removeAnimatable(this);
			
		}
		
		//disallow placing towers in menu area
		else if(game.getMousePressed() && game.getMousePos().x >= 600)
		{
			game.clearMousePressed();
			game.removeAnimatable(this);
		}
		
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, position.x, position.y, null);
		
	}

}
