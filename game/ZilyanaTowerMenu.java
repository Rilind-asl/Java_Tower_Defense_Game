package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ZilyanaTowerMenu extends Effects {

	private BufferedImage image;
	
	public ZilyanaTowerMenu(GameState game, Point position) {
		super(game, position);
		image = ResourceLoader.getLoader().getImage("Zilyana.png");
	}

	@Override
	public void update() 
	{
		if(game.getMousePos().distance(position) < 50 && game.getMousePressed() && game.credits >= 10)
		{
			game.pendingAnimatable(new ZilyanaTowerMoving(game, position));
			game.clearMousePressed();
		}
		
	}

	@Override
	public void draw(Graphics2D g) 
	{
		
		g.drawImage(image, position.x, position.y, null);
		
	}

}
