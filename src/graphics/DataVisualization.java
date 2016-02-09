package graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import maths.Particle;
import maths.Ti84;
import simulation.Globals;
import simulation.Launcher;

public class DataVisualization extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private int colLength;
	private JButton enter;
	int c = 0;

	public DataVisualization(boolean isGravity) {
		//super(new GridLayout(0,1));
		String[] sColNames = {
				"ID",
				"Mass",
				"X Positon",
				"Y Position",
				"Z Position",
				"X Velocity",
				"Y Velocity",
				"Z Velocity"
		};

		colLength = sColNames.length;
		
		Ti84.intializeParticles(isGravity);
		
		Object[][] data = new Object[Globals.N][sColNames.length];

		for(int i = 0; i < Globals.particles.size(); i++){
			Particle p = Globals.particles.get(i);
			data[i][0] = String.valueOf(p.id);
			data[i][1] = String.valueOf(p.mass);
			data[i][2] = String.valueOf(p.x);
			data[i][3] = String.valueOf(p.y);
			data[i][4] = String.valueOf(p.z);
			data[i][5] = String.valueOf(p.Vx);
			data[i][6] = String.valueOf(p.Vy);
			data[i][7] = String.valueOf(p.Vz);
		}

		Globals.table = new JTable(data, sColNames);
		Globals.table.setPreferredScrollableViewportSize(new Dimension(700, 800));
		Globals.table.setFillsViewportHeight(true);

		//Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(Globals.table);

		//Add the scroll pane to this panel.
		add(scrollPane);

		enter = new JButton("Enter");
		enter.setPreferredSize(new Dimension(100,20));
		//	enter.setMaximumSize(new Dimension(10,10));
		//	enter.setMinimumSize(new Dimension(10,10));
		//	enter.setBounds(20, 30, 50, 50);
		enter.setSize(new Dimension(40,40));
		enter.setActionCommand("Enter");
		enter.addActionListener(this);

		add(enter);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String ent = e.getActionCommand();
		c++;

		try{
			if(	(c==1) && (ent.equals("Enter")) ){

				for(int row = 0; row < Globals.N; row++ ){

					Globals.particles.get(row).mass =  Float.valueOf((String) Globals.table.getValueAt(row, 1));
					Globals.particles.get(row).x = Float.valueOf((String) Globals.table.getValueAt(row, 2));
					Globals.particles.get(row).y = Float.valueOf((String) Globals.table.getValueAt(row, 3));
					Globals.particles.get(row).z = Float.valueOf((String) Globals.table.getValueAt(row, 4));
					Globals.particles.get(row).Vx = Float.valueOf((String) Globals.table.getValueAt(row, 5));
					Globals.particles.get(row).Vy = Float.valueOf((String) Globals.table.getValueAt(row, 6));
					Globals.particles.get(row).Vz = Float.valueOf((String) Globals.table.getValueAt(row, 7));

				}

			}
		}catch(Exception e1){
			//	e1.printStackTrace();
			c--;
			System.out.println(c);
		}

		if(c==1)
		{
			remove(enter);
			Launcher.launch();
		}

	}


}