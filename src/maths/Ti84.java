package maths;
import java.util.Vector;

import simulation.Globals;

public class Ti84 implements Runnable{

	public Ti84(){
		almightyForce();
	}

	private void almightyForce(){
		for(int a = 0; a < Globals.N; a++){
			Particle p = Globals.particles.get(a);

			for(int b = 0; b < Globals.N; b++){
				if(b != a)
					p.addForce(Globals.particles.get(b));
			}
		}
	}

	public void resetParticleForces(){
		for(Particle particle : Globals.particles){
			particle.resetForce();
		}	
	}
	
	public static void intializeParticles(boolean isGravity)
	{
		if(isGravity)
		{
			for(int i = 0; i < Globals.N; i++){

				Globals.particles.add(	new Particle(
						(float)( (Math.random()-0.5) * Globals.X_RANGE ), //x
						(float)( (Math.random()-0.5) * Globals.Y_RANGE ), //y
						(float)( Math.random() * Globals.Z_RANGE ),		  //z
						1E10f, 0.0f, 0.0f, 0.0f));	//mass, Vx, Vy, Vz
				//		(int)( Math.random() * 2)
			}
		}
		else
		{
			for(int i = 0; i < Globals.N; i++){

				Globals.particles.add(	new Particle(
						(float)( (Math.random()-0.5) * Globals.X_RANGE ), //x
						(float)( (Math.random()-0.5) * Globals.Y_RANGE ), //y
						(float)( Math.random() * Globals.Z_RANGE ),		  //z
						0.0001f, 0.0f, 0.0f, 0.0f));	//mass, Vx, Vy, Vz
				//		(int)( Math.random() * 2)
			}
		}
	}
	
	@Override
	public void run() {
		while(true){

			Globals.arraylistParticleLock.lock();
			//			Globals.timeLock.lock();

			resetParticleForces();
			
			almightyForce();
			
			float dtNOW = Globals.dt;
			
			for(Particle particle : Globals.particles){
				particle.update(dtNOW);
			//	if(particle.id == 0)
			//		System.out.println(particle);
			}	

			//System.out.println("Ti84 dt: " + Globals.dt);
			//System.out.println(Globals.particles.get(0));
			Globals.arraylistParticleLock.unlock();		
			//			Globals.timeLock.unlock();

			try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

		}
	}

}