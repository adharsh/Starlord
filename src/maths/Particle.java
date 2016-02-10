package maths;

public class Particle {
	
	public static final float SofteningParameter = 1f;
	public static final float G = (float) 6.67E-11;
	//x, y, and z values are not scaled to fit screen, just based on calculations
	public float x, y, z;
	public float Vx, Vy, Vz;
	public float Fx, Fy, Fz;
	public float mass;
	private static int idTally = 0;
	public final int id;
	
	
	public Particle(float x, float y, float z, float mass, float xInitialVelocity, float yInitialVelocity, float zInitialVelocity){
		
		id = idTally++;
	//	System.out.println(id);

		this.x = x;
		this.y = y;
		this.z = z;

		this.Vx = xInitialVelocity;
		this.Vy = yInitialVelocity;
		this.Vz = zInitialVelocity;
		
		this.mass = mass;
		
		resetForce();
	}
	
	
	public void update(float dt){
		Vx += dt * Fx / mass;
		Vy += dt * Fy / mass;
		Vz += dt * Fz / mass;
		
		x += dt * Vx;
		y += dt * Vy;
		z += dt * Vz;
	}
	
	public float distanceTo(Particle p){
		float dx =  x - p.x;
		float dy =  y - p.y;
		float dz =  z - p.z;
		return (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public void resetForce(){
		Fx = 0.0f;
		Fy = 0.0f;
		Fz = 0.0f;
	}
	
	public void addForce(Particle p){
		float dx =  p.x - x;
		float dy =  p.y - y;
		float dz =  p.z - z;
		float dist = (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
		float F = (G * this.mass * p.mass) / (dist*dist + SofteningParameter);
		this.Fx += F * dx / dist;
		this.Fy += F * dy / dist;
		this.Fz += F * dz / dist;
	}
	
	public String toString(){
		return "( " + x + ", " + y + ", " + z + " )\t" + "< " + Vx + ", " + Vy + ", " + Vz + " >\t" + mass;
	}
	
	public static int colorGenerator(int id){
		
		float frequency = (float)0.001;
		
		int red = (int)(Math.sin(frequency * System.nanoTime()/10E6f + id) * 127.5 + 127.5);
		int green = (int)(Math.sin(frequency * System.nanoTime()/10E6f + 2.0*Math.PI/3.0 + id) * 127.5 + 127.5);
		int blue = (int)(Math.sin(frequency * System.nanoTime()/10E6f + 4.0*Math.PI/3.0 + id) * 127.5 + 127.5);
		
		return (0x80<<24 | red<<16 | green<<8 | blue);
	}


}