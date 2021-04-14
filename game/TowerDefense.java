package game;

/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TowerDefense extends JPanel implements ActionListener, Runnable, MouseListener, MouseMotionListener
{

	private GameState game;
    // This constant avoids an obnoxious warning, but it is totally unused by us.
    //   It would only be relevant if we were using object serialization.
    private static final long serialVersionUID = 42L;
    
    // Fields (object variables) 
    /**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Main runs in the 'main' execution thread, and the GUI
        //   needs to be built by the GUI execution thread.
        //   Ask the GUI thread to run our 'run' method (at some
        //   later time).
        
        SwingUtilities.invokeLater(new TowerDefense());

        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
    
    /**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
    public void run ()
    {
        JFrame f = new JFrame("RuneScape Tower Defense");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setContentPane(this);
        f.pack();
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);
        f.setResizable(true);
    }


    // Methods

    public TowerDefense ()
    {

    		game = new GameState();
            Dimension panelSize = new Dimension(850, 600);
            
            this.setMinimumSize(panelSize);
            this.setPreferredSize(panelSize);
            this.setMaximumSize(panelSize);

            Timer timer = new Timer(20, this);
            timer.start();   
            
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
    }
    
    /**
     * Draws the image, path, and the animating ball.
     * 
     * (The background is not cleared, it is assumed the backdrop
     * fills the panel.)
     * 
     * @param g the graphics object for drawing on this panel
     */
    public void paint (Graphics g)
    {
    	
    	game.draw((Graphics2D)g);
    	
    }
    
    /**
     * The actionPerformed method is called (from the GUI event loop)
     * whenever an action event occurs that this object is lisening to.
     * 
     * For our test panel, we assume that the Timer has expired, so
     * we advance our small sphere along the path.
     * 
     * @param e the event object 
     */
    public void actionPerformed (ActionEvent e)
    {
    	game.update();
    	repaint();
    }

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		game.mouseLocation = new Point (e.getX(), e.getY());
	
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		game.mouseLocation = new Point (e.getX(), e.getY());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
			game.isMousePressed = true;
			game.mouseLocation = new Point(e.getX(), e.getY());
//			System.out.println(game.mouseLocation);
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


