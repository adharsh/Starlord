package simulation;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import maths.Particle;


public class Globals {
	
	public static Vector<Particle> particles = new Vector<Particle>();
	
	public static ReentrantLock frameBufferLock = new ReentrantLock();
	public static ReentrantLock arraylistParticleLock = new ReentrantLock();
	//public static ReentrantLock timeLock = new ReentrantLock();
	
	//change dimensions
	public static final int DIMENSION_W = 800;//2000;
	public static final int DIMENSION_H = 800;//1000;
	
	public static int N = 1000;
	
	public static float dt;
	
	public static float X_RANGE = 100.0f;
	public static float Y_RANGE = 100.0f;
	public static float Z_RANGE = 10.0f;
	
	public static int alphaLayerValue = 63;
	
	public static JTable table;
	public static JFrame frame;
	
}
