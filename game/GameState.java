package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
public class GameState implements Animatables
{
	//field variables
    private double percent;
    private ResourceLoader loader;
	private boolean isGameOver;
	private boolean isPlaying;
	private List<Animatables> active;
	private List<Animatables> expired;
	private List<Animatables> pending;
	protected int lives;
	protected int credits;
	protected Point mouseLocation;
	protected boolean isMousePressed;
	
	// game state constructor.
	public GameState()
	{
		restart();
		
		// loader
        loader = ResourceLoader.getLoader();

        // Assume the circle has traveled 0% along the path.
        this.percent = 0.0;
        
        this.isGameOver = false;
        this.isPlaying = false;

       
        active.add(new Backdrop("lava.png"));
        active.add(new Backdrop("path.png"));
	}
	
	/**
	 * method used to update the game so things happen.
	 */
	public void update ()
	{
		if(isPlaying)
		{
			
			// randomly deciding when to create a new object enemy.
	    	if(Math.random() < .05)
	    	{
	    		active.add(new impEnemy("path_2.txt", this));
	    		
	    		if(Math.random() < .07)
	        	{
	        		if(Math.random() < .08)
	        			active.add(new jadEnemy("path_2.txt", this));
	        		else
	        			active.add(new KetZekEnemy("path_2.txt", this));
	        	}
	    	}
	    	// updating all animatables
	    	for(Animatables a : active)
	    	{
	    		a.update();
	    	}
	    	
	    	// adding new animatables, clearing old ones, and clearing lists.
	    	active.addAll(pending);
	    	pending.clear();
	    	active.removeAll(expired);
	    	expired.clear();
	    	clearMousePressed();
		}
		
		// checking if mouse is has been pressed to start game
		if(isMousePressed == true)
		{
			isPlaying = true;
			
		}
		
		// if they reach zero lives end game.
		if(lives == 0)
		{
			isGameOver = true;
		}
		
		// if game is over draw new back drop
		if(isGameOver)
		{
			active.add(new Backdrop("gameover.png"));
			isPlaying = false;
		}
	}
	
	// re/starting the game.
	public void restart()
	{
		
		this.lives = 100;
		this.credits = 10;
		
        active = new ArrayList<Animatables>();
        expired = new ArrayList<Animatables>();
        pending = new ArrayList<Animatables>();
        
        mouseLocation = new Point(0, 0);
        
        // drawing menu area towers
        Point menuArea1 = new Point(650, 100);
        Point menuArea2 = new Point(650, 250);
        Point menuArea3 = new Point(650, 350);
        pending.add(new ZilyanaTowerMenu(this, menuArea1));
        pending.add(new CannonTowerMenu(this, menuArea2));
        pending.add(new RantzTowerMenu(this, menuArea3));

        
	}
	/**
	 * Method used to draw images.
	 */
	@Override
	public void draw(Graphics2D g) {
        


		// drawing the menu area
    	g.setColor(Color.WHITE);
    	g.fillRect(600, 0, 300, 800);

    	
    	// drawing tower names and costs
    	g.setColor(Color.RED);
    	g.drawString("Zilyana Tower" + " Cost: 10 Credits" , 620, 100);
    	g.drawString("Cannon Tower" + " Cost: 10 Credits", 620, 220);
    	g.drawString("Rantz Tower" + " Cost: 10 Credits", 620, 350);
    	
    	// changing the font
    	g.setFont(new Font("Arial", Font.BOLD, 13));
		
		g.setColor(Color.BLACK);
		
    	// drawing menu, lives, and credits
    	g.drawString("MENU", 670, 20);
    	
    	g.drawString("Lives: " + lives, 620, 40);
    	g.drawString("Credits: " + credits, 620, 55);
    	g.drawString("Tower Menu", 660, 80);
        
    	//g.drawImage(loader.getImage("path_2.jpg"),  0, 0, null);
    	
      
        // drawing the animatables list
        
        for(Animatables a : active)
			a.draw(g);
        
        
    	if(!isPlaying && !isGameOver)
    	{
    		g.drawImage(loader.getImage("start.png"), 100, 100, null);
    	}
    	

	}
	
	public void pendingAnimatable (Animatables p)
	{
		this.pending.add(p);
	}
	public void  removeAnimatable (Animatables e)
	{
		// Adds the object to our expired list
		this.expired.add(e);
	}
	public void  addAnimatable    (Animatables n)
	{
		// Adds the object to our active list
		this.active.add(n);
	}
	public void  adjustCredits    (int amount)
	{
		// Adjusts the credits by the given amount
		credits += amount;
		
	}
	public void  adjustLives      (int amount)
	{
		// Adjusts lives by the given amount
		lives += amount;
	}
	public void  setMousePos      (Point p)
	{
		// Records the current mouse position
		p = getMousePos();
	}
	public Point getMousePos()
	{
		// Returns the current mouse position
		return mouseLocation;
	}
	public void  setMousePressed  ()
	{
		// Sets a boolean flag to true (to indicate a mouse press)
		isMousePressed = true;
	}
	public void  clearMousePressed ()
	{
		// Clears the mouse press boolean flag
		isMousePressed = false;
	}
	public boolean getMousePressed ()
	{
		// Returns the value of the mouse press flag
		return isMousePressed;
	}
	
	public Enemy findNearestEnemy (Point p)
	{
		// Finds the active enemy nearest to point p, returns it
		Enemy closest = null;
		
		for(Animatables a : active)
		{

			if(a instanceof Enemy)
			{
				Enemy e = (Enemy) a;
			
			if(closest == null)
			{
				closest = e;
			}
			else if(e.getLocation().distance(p) < closest.getLocation().distance(p))
			{
				closest = e;
			}
			
			}
		}
		
		return closest;
	}
	         
}
