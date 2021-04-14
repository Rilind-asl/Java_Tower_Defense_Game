package game;
/**
 * 
 * @author Rilind Asllani
 * @version 4/16/2017 
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * 
 * @author Rilind Asllani
 * @version 4/5/2017
 */
public class ResourceLoader 
{
	private Map<String, BufferedImage> listMap;

	private Map<String, Path> pathList;
	private ClassLoader myLoader = this.getClass().getClassLoader();
	
	static private ResourceLoader instance;
	
	
	private ResourceLoader()
	{
		// setting up the loader.
		listMap = new HashMap<>();
		pathList = new HashMap<>();
		
		
	}
	
	static public ResourceLoader getLoader()
      {
          if (instance == null)
            instance = new ResourceLoader ();
 
          return instance;
      }
 
	  
	public BufferedImage getImage(String ImageName)
	{
		if(listMap.containsKey(ImageName))
			return listMap.get(ImageName);
		
		try
		{
            
            // Load the background image
            
            InputStream imageStream = myLoader.getResourceAsStream("resources/" + ImageName);
            BufferedImage result = javax.imageio.ImageIO.read(imageStream);  // A handy helper method
            
            listMap.put(ImageName, result);
            
            return result;
		}
		catch(IOException e)
		{
			// On error, just print a message and exit.  
            //   (You should make sure the files are in the correct place.)
            
            System.err.println ("Could not load one of the files: " + ImageName);
            System.exit(0);  // Bail out.
            
            return null; //never gets here.
		}
	}
	
	public Path getPath(String pathName)
	{
		if(pathList.containsKey(pathName))
			return pathList.get(pathName);
		
		try
		{
            InputStream pointStream = myLoader.getResourceAsStream("resources/" + pathName);
            Scanner in = new Scanner (pointStream);  // Scan from the text file.            
            
            // Build the path object (using the scanner).
            Path path = new Path(in);
            
            pathList.put(pathName, path);
            
			return path;
		}
		catch(Exception e)
		{
			System.err.println ("Could not load path: " + pathName);
			System.exit(0);
			return null;
		}
		
		
	}
}
