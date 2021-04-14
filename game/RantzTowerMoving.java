package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class RantzTowerMoving extends Effects 
{
	// Private image variable
	private BufferedImage image;
	
	//Constructor
	public RantzTowerMoving(GameState game, Point position) 
	{
		// calling super class
		super(game,position);
		
		// Setting image
		this.image = ResourceLoader.getLoader().getImage("rantz.png");
		
	}

	@Override
	public void update() 
	{
		// getting mouse position
		position = game.getMousePos();
		
		// if mouse is pressed and credits are 
		// more than/equal to 5 and mouse is in menu area
		if(game.getMousePressed() && game.credits >= 10 && game.getMousePos().x < 600)
		{
			game.clearMousePressed();
			game.pendingAnimatable(new RantzTower(game, position));
			game.adjustCredits(-10);
			game.removeAnimatable(this);
			
		}
		// else if the mouse is pressed and not enough credits
		else if(game.getMousePressed() && game.credits < 10)
		{
			System.out.println("Not enough Credits");
			game.clearMousePressed();
			game.removeAnimatable(this);
			
		}
		// else if the mouse is pressed in menu area again
		// wont allow placing towers
		else if(game.getMousePressed() && game.getMousePos().x >= 600)
		{
			game.removeAnimatable(this);
			game.clearMousePressed();
		}
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, position.x, position.y, null);
		
	}

}
