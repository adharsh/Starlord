package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import simulation.Globals;
import maths.Particle;

public class World implements Runnable, KeyListener, MouseMotionListener, MouseWheelListener{

	private BufferedImage target;
	private Graphics bufferGraphics;
	private final Color alphaLayer;

	private int[] keys;
	private float xTranslate, 
	zTranslate = (Globals.X_RANGE/Globals.Z_RANGE > Globals.Y_RANGE/Globals.Z_RANGE)? Globals.X_RANGE/Globals.Z_RANGE : Globals.Y_RANGE/Globals.Z_RANGE;

	float yawY = 0.0f;
	float pitchX = 0.0f;
	float rollZ = 0.0f;

	public World(BufferedImage target){

		this.target = target;
		bufferGraphics = target.createGraphics();

		alphaLayer = new Color(0, 0, 0, Globals.alphaLayerValue);

		//bufferGraphics.setColor(new Color(0,0,0,255));
		bufferGraphics.setColor(alphaLayer);
		// bufferGraphics.setComposite();
		bufferGraphics.fillRect(0, 0, Globals.DIMENSION_W, Globals.DIMENSION_H);

		keys = new int[100];
		//	addKeyListener(this);
	}

	@Override
	public void run() {
		while(true){
			Globals.frameBufferLock.lock();
			Globals.arraylistParticleLock.lock();

			bufferGraphics.fillRect(0, 0, Globals.DIMENSION_W, Globals.DIMENSION_H);

			
			for( Particle particle : Globals.particles){
				float x = 0, y = 0, z = 0;

				//Rotation
				float tmpX = particle.x;
				float tmpY = particle.y;
				float tmpZ = particle.z;
				
				x = (float) (
						tmpX * (	Math.cos(yawY) * Math.cos(rollZ)	) + 
						tmpY * (	Math.cos(rollZ) * Math.sin(pitchX) * Math.sin(yawY) - Math.cos(pitchX) * Math.sin(rollZ) ) + 
						tmpZ * (	Math.cos(pitchX) * Math.cos(rollZ) * Math.sin(yawY) + Math.sin(pitchX) * Math.sin(rollZ)	)
						); 

				y = (float) (
						tmpX * (	Math.cos(yawY) * Math.sin(rollZ)	) + 
						tmpY * (	Math.cos(pitchX) * Math.cos(rollZ) + Math.sin(pitchX) * Math.sin(yawY) * Math.sin(rollZ)	) + 
						tmpZ * (	- Math.cos(rollZ) * Math.sin(pitchX) + Math.cos(pitchX) * Math.sin(yawY) * Math.sin(rollZ)	)
						);

				z = (float)(
						tmpX * (- Math.sin(yawY) )	+ 
						tmpY *	(	Math.cos(yawY) * Math.sin(pitchX) )	+
						tmpZ * (	Math.cos(pitchX) * Math.cos(yawY)	)
						);
		
				//Translation
				z = z + zTranslate;
				
				if(	z <= 0 )
					continue;
				
				x = (x + xTranslate)/z; //(particle.z + zTranslate);
				y = y/z; //(particle.z + zTranslate);
				
				//Perspective
				x *= Globals.DIMENSION_W;
				x += Globals.DIMENSION_W / 2.0f;

				y *= Globals.DIMENSION_H;
				y += Globals.DIMENSION_H / 2.0f;

				int pixelX = (int) x;
				int pixelY = (int) y;
				
				/*	if(particle.id==0){
					System.out.println("x value: " + pixelX);
					System.out.println("y value: " + pixelY);
					System.out.println();
				//	System.out.println("z value: " + particle.z);
				}
				 */	
				if(pixelX >= Globals.DIMENSION_W || pixelX < 0 || pixelY >= Globals.DIMENSION_H || pixelY < 0 )
					continue;
				

				//	target.setRGB(pixelX, pixelY, 0x80FF0000);
				target.setRGB(pixelX, pixelY, Particle.colorGenerator(particle.id));

				//	particle.z -= 1f;

			}

			Globals.arraylistParticleLock.unlock();
			Globals.frameBufferLock.unlock();	

			try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

		}
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();

		if(keys[37] == 1 || key == 37){
			xTranslate += 0.1;
			keys[37] = 1;
		}

		if(keys[38] == 1 || key == 38){
			zTranslate -= 0.1f;
			keys[38] = 1;
		}

		if(keys[39] == 1 || key == 39){
			xTranslate -= 0.1;
			keys[39] = 1;
		}

		if(keys[40] == 1 || key == 40){
			zTranslate += 0.1f;
			keys[40] = 1;
		}

	}

	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();

		if(key == 37){
			keys[37] = 0;
		}

		if(key == 38){
			keys[38] = 0;
		}

		if(key == 39){
			keys[39] = 0;
		}

		if(key == 40){
			keys[40] = 0;
		}

	}



	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub

	}
	public void mouseDragged(MouseEvent event) {
		mouseUpdate(event.getX(), event.getY());
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		lastX = event.getX();
		lastY = event.getY();
	}

	private static boolean firstMouse = true;
	private float lastX = Globals.DIMENSION_W/2.0f;
	private float lastY = Globals.DIMENSION_H/2.0f;
	private void mouseUpdate(float xpos, float ypos){
		if (firstMouse) {
			lastX = xpos;
			lastY = ypos;
			firstMouse = false;
		}

		float xoffset = xpos - lastX;
		float yoffset = lastY - ypos;
		lastX = xpos;
		lastY = ypos;

		float sensitivity = 0.005f;
		xoffset *= sensitivity;
		yoffset *= sensitivity;

		yawY += xoffset;
		pitchX += yoffset;

	/*	if (pitchX > 89.0f)
			pitchX = 89.0f;
		if (pitchX < -89.0f)
			pitchX = -89.0f;
	*/
		rollZ = 0;

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		zTranslate += e.getWheelRotation() * e.getScrollAmount();
	}

}

