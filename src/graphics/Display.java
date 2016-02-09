package graphics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import simulation.Globals;


public class Display extends JPanel{
	
	private BufferedImage target;
	
	public Display(){
		target = new BufferedImage(Globals.DIMENSION_W, Globals.DIMENSION_H, BufferedImage.TYPE_INT_ARGB);
		
	}
	
	public BufferedImage getTarget(){
		return target;
	}
	
	
	public void paintComponent(Graphics g){
		//super.paintComponent(g);

		Globals.frameBufferLock.lock();


		
		g.drawImage( target, 0, 0, null );

		

		
		Globals.frameBufferLock.unlock();
		
	}


}
