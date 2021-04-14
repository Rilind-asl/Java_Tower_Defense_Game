package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Cannonball extends Effects {

	private BufferedImage image;
	private int deltaX, deltaY;
	private int frameCounter;
	
	public Cannonball(GameState game, Point position, int deltaX, int deltaY) 
	{
		super(game, position);
		this.image = ResourceLoader.getLoader().getImage("ball.png");
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.frameCounter = 0;
		this.position = new Point(position);
	}

	@Override
	public void update() 
	{
		
		frameCounter++;
		if(frameCounter == 30)
		{
			game.removeAnimatable(this);
		}
		position.x += deltaX/3;
		position.y += deltaY/3;
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.drawImage(image, position.x, position.y, null);
		
	}
	
	public Point getLocation()
	{
		return new Point(position);
	}

}
