package simulation;


public class UpdateTable implements Runnable{
	
	@Override
	public void run() {
		while(true){
			
			
	/*		if(Globals.table.isEditing())
			{

		//		Globals.table.getCellEditor().stopCellEditing();
				int row = Globals.table.getEditingRow();
				int col = Globals.table.getEditingColumn();


				if(col==1)
					Globals.particles.get(row).mass =  Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));
				else if(col==2)
					Globals.particles.get(row).x = Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));
				else if(col == 3)
					Globals.particles.get(row).y =  Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));
				else if(col == 4)
					Globals.particles.get(row).z =  Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));
				else if(col == 5)
					Globals.particles.get(row).Vx =  Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));
				else if(col == 6)
					Globals.particles.get(row).Vy = Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));
				else if(col == 7)
					Globals.particles.get(row).Vz =  Float.valueOf((String) String.valueOf(Globals.table.getValueAt(row, col)));

				//	try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
				Globals.table.getCellEditor().stopCellEditing();
			}
		*/	
		//	Globals.arraylistParticleLock.lock();
			
			for(int row = 0; row < Globals.N; row++ ){
					Globals.table.setValueAt(Globals.particles.get(row).mass, row, 1);
					Globals.table.setValueAt(Globals.particles.get(row).x, row, 2);
					Globals.table.setValueAt(Globals.particles.get(row).y, row, 3);
					Globals.table.setValueAt(Globals.particles.get(row).z, row, 4);
					Globals.table.setValueAt(Globals.particles.get(row).Vx, row, 5);
					Globals.table.setValueAt(Globals.particles.get(row).Vy, row, 6);
					Globals.table.setValueAt(Globals.particles.get(row).Vz, row, 7);
				}
		
		//	Globals.arraylistParticleLock.unlock();
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
		
		}
	}

	
	
}
