package maths;
import java.util.Vector;

import simulation.Globals;

public class Ti84 implements Runnable{

	public Ti84(){
		
	//	Globals.particles.get(0).mass = 10E12f;
	//	Globals.particles.get(0).x = -1f;
	//	Globals.particles.get(0).y = 0f;
/*		
		Globals.particles.get(1).mass = -10E12f;
		Globals.particles.get(1).x = 1f;
		Globals.particles.get(1).y = 0f;
		
		Globals.particles.get(2).mass = -10E12f;
		Globals.particles.get(2).x = 0f;
		Globals.particles.get(2).y = 1f;
		
		Globals.particles.get(3).mass = -10E12f;
		Globals.particles.get(3).x = 0f;
		Globals.particles.get(3).y = -1f;
		
		Globals.particles.get(4).mass = -10E14f;
		Globals.particles.get(4).x = -100f;
		Globals.particles.get(4).y = 0f;
		
		Globals.particles.get(5).mass = -10E14f;
		Globals.particles.get(5).x = 100f;
		Globals.particles.get(5).y = 0f;
		
		Globals.particles.get(6).mass = 10E14f;
		Globals.particles.get(6).x = 0f;
		Globals.particles.get(6).y = 0f;
		
	//	Globals.particles.get(1).mass = -10E11f;
	//	Globals.particles.get(1).x = 1f;
	//	Globals.particles.get(1).y = 0f;
		
		
	//	Globals.particles.get(1).mass = 10E12f;
	//	Globals.particles.get(2).mass = 10E12f;
	//	Globals.particles.get(3).mass = 10E12f;
	//	Globals.particles.get(4).mass = 10E13f;
	*/	
	//	System.out.println(Globals.particles.get(0));
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