package simulation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import graphics.Display;
import graphics.World;
import maths.Ti84;
import utils.Screenshot;


public class MainThread implements Runnable{

	/*

	 One Thread for animnating on bufferedimage
	 One thread for paintcompnent by using repaint()
	 One thread that's main method 
	 Reentrant lock to lock and unlock stuff

	 Draw alpha over entire image over each frame
	 Then draw stuff
	 Keep track of each particle though
	 Same as x and y, but now divide by z 


	 */

	@Override
	public void run(){
		
		Ti84 particles = new Ti84();
		Thread mathThread = new Thread(particles);
		mathThread.start();

		Display display = new Display();
		display.setPreferredSize(new Dimension(Globals.DIMENSION_W, Globals.DIMENSION_H));
		Globals.frame.add(display);
		BufferedImage target = display.getTarget();

		World drawingToImage = new World(target);
		Thread worldThread = new Thread(drawingToImage);
		worldThread.start();
		
		Screenshot screenshot = new Screenshot();
		Globals.frame.addKeyListener(screenshot);
		
		if(Globals.isTable)
		{
			UpdateTable updateTable = new UpdateTable();
			Thread updateTableThread = new Thread(updateTable);
			updateTableThread.start();
			Globals.table.getTableHeader().setReorderingAllowed(false);
		
			Globals.frame.requestFocus();
			Globals.frame.addKeyListener(drawingToImage);
			Globals.frame.addMouseMotionListener(drawingToImage);
			Globals.frame.addMouseWheelListener(drawingToImage);
			
		}else{
			Globals.frame.requestFocus();
			Globals.frame.setContentPane(display);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Globals.frame.setLocation( (int) (dim.width/2.0 - 760.0/2.0), (int) (dim.height/2.0 - 883.0/2) );
		
			if(Globals.alphaLayerValue >= 63)
			{
				Globals.frame.addKeyListener(drawingToImage);
				Globals.frame.addMouseMotionListener(drawingToImage);
				Globals.frame.addMouseWheelListener(drawingToImage);
			}
				
		}	
		
		Globals.frame.pack();
		//frame.setVisible(true);
		//frame.setLocationRelativeTo(null);

		long fps = System.nanoTime();

		while(true){

			//	Globals.timeLock.lock();

			//	System.out.println("1: " + System.nanoTime());

			display.repaint();

			try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }

			Globals.dt = (float)(System.nanoTime() - fps)/(4E7f); //+ 9E6f);
			fps = System.nanoTime();
			//	System.out.println("Globals dt:" + Globals.dt);
			//	System.out.println("2: " + System.nanoTime());
			//	System.out.println();
			//	Globals.timeLock.unlock();

		}

	}


}
