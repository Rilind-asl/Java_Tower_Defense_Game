package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class dead extends Effects 
{

	private int frameCounter;
	private BufferedImage image;
	public dead(GameState game, Point position) 
	{
		super(game, position);
		this.frameCounter = 0;
		this.image = ResourceLoader.getLoader().getImage("splat.png");
		
	}

	@Override
	public void update() 
	{
		frameCounter++;
		if(frameCounter == 15)
		{
			game.removeAnimatable(this);
			frameCounter = 0;
			game.adjustCredits(1);
		}
		
	}
	
	@Override
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, position.x-image.getWidth()/2, position.y-image.getHeight()/2, null);
		
	}

}
