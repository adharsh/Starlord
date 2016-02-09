package utils;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import simulation.Globals;

public class Screenshot implements KeyListener{
	
	private static int c = -1;

	public Screenshot()	
	{
		
	}
	
	public void saveImage()
	{	
		c++;
		
		Point corner = Globals.frame.getLocation();
		Robot screenShot = null;
		
		try {
			screenShot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Rectangle areaOfDrawing = new Rectangle( corner.x, corner.y, 200, 200);
		BufferedImage dataImage = screenShot.createScreenCapture(areaOfDrawing);
		
		try {
		    File outputfile = new File("StarLord" + Integer.toString(c) + ".png");
		    ImageIO.write(dataImage, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Picture");
			e.printStackTrace();
		}
		
		System.out.println("Saved Image as: StarLord" + Integer.toString(c) + ".png");
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if(key == KeyEvent.VK_ENTER)
		{
			saveImage();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
