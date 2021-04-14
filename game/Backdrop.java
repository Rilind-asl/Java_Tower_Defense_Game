package game;

import java.awt.Graphics2D;

public class Backdrop implements Animatables
{

	private String imageName;
	
	public Backdrop(String imageName) 
	{
		this.imageName = imageName;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) 
	{
		 g.drawImage(ResourceLoader.getLoader().getImage(imageName),  0, 0, null);
		
	}

}
