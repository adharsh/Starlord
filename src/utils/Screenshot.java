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
		
		BufferedImage picture = null;
		Rectangle areaOfDrawing = null;
		
		if(!Globals.isTable)
			areaOfDrawing = new Rectangle( corner.x + 3, corner.y + 26, Globals.DIMENSION_W, Globals.DIMENSION_H);
		else
<<<<<<< HEAD
			//areaOfDrawing = new Rectangle( corner.x + Globals.DIMENSION_W - 66, corner.y + 41, Globals.DIMENSION_W, Globals.DIMENSION_H);
			areaOfDrawing = new Rectangle( corner.x + 3, corner.y + 26, Globals.DIMENSION_W * 2 - 80, Globals.DIMENSION_H);
=======
			areaOfDrawing = new Rectangle( corner.x + Globals.DIMENSION_W - 66, corner.y + 41, Globals.DIMENSION_W, Globals.DIMENSION_H);
>>>>>>> b8070bf2a8d0137cc0fd2a4a4d2f5922866dacb3

		picture = screenShot.createScreenCapture(areaOfDrawing);
		
		try {
<<<<<<< HEAD
		    File outputfile = new File("StarLord" + Integer.toString(c) + ".jpg");
=======
		    File outputfile = new File("StarLord" + Integer.toString(c) + ".png");
>>>>>>> b8070bf2a8d0137cc0fd2a4a4d2f5922866dacb3
		    ImageIO.write(picture, "png", outputfile);
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
