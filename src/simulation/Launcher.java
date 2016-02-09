package simulation;
import graphics.DataVisualization;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Launcher extends JPanel implements ActionListener{
	
	/*
	 * Make Cool Configurations to Show 
		1. Three Body Problem 
		2. Plane of particles
		3. Cube
		4. Cube to sphere
		5. Discus 
		6. Big bang -> 1E4F for zTranslate
			N: 1000
			X: 1
			Y: 1
			Z: 1
		
	 * ******************************************************************************************************************************************
	 *	1. Adjust frame rate -> Change MainTHreadsleep, others like world should be fast and Ti84 should also be fast
		3. Input during Simulation -> Check : )
	 * 	4. Catch reading JTable errors -> Check : )
	 * 	5. JTable rendering behind image, just comment out Display::paintComponent->//g.drawImage( target, 0, 0, null ); 
	 * 	5. Varying speeds, adjust thread sleep to same number
	 * ******************************************************************************************************************************************
	 */
	
	
	
	int c = 0;
	private static Launcher input;
	JTextField N = new JTextField();
	JTextField xRange = new JTextField();
	JTextField yRange = new JTextField();
	JTextField zRange = new JTextField();
	JButton initiate = new JButton("Initiate");
	JLabel[] text = new JLabel[4];
	JCheckBox isGravity = new JCheckBox("Gravity", true);
	
	public Launcher(){
		setLayout(new GridLayout(0,2,10,10));
		setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
		
		text[0] = new JLabel("Number of Particles: ");
		text[1] = new JLabel("X Range: ");
		text[2] = new JLabel("Y Range: ");
		text[3] = new JLabel("Z Range: ");
		
		add(text[0]);
		add(N);
		add(text[1]);
		add(xRange);
		add(text[2]);
		add(yRange);
		add(text[3]);
		add(zRange);
		add(isGravity);
		
		initiate.setActionCommand("Initiate");
		initiate.addActionListener(this);
		add(initiate);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
			c++;
			if(	(c == 1) && e.getActionCommand().equals("Initiate"))
			{
				Globals.N = Integer.parseInt(N.getText());
				Globals.X_RANGE = Float.parseFloat(xRange.getText());
				Globals.Y_RANGE = Float.parseFloat(yRange.getText());
				Globals.Z_RANGE = Float.parseFloat(zRange.getText());
			}
			
		}catch(Exception e1){
			//e1.printStackTrace();
			c--;
		}
			
		if(c==1)
		{
			DataVisualization inner = new DataVisualization(isGravity.isSelected());
			inner.setOpaque(true); //content panes must be opaque
			
			JPanel container = new JPanel();
			container.add(inner);
			
			Globals.frame.remove(input);
			
			Globals.frame.add(container);
			Globals.frame.setContentPane(container);
			
			Globals.frame.pack();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Globals.frame.setLocation( (int) (dim.width/2.0 - 760.0), (int) (dim.height/2.0 - 883.0/2) );
			
			Globals.frame.setVisible(true);
		}
		
	}
	
	
	public static void main(String[] args){
		Globals.frame = new JFrame("StarLord");
		Globals.frame.setResizable(false);
		Globals.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Globals.frame.setAlwaysOnTop(true);
		input = new Launcher();
		Globals.frame.add(input);
		Globals.frame.setContentPane(input);
		
		Globals.frame.pack();
		Globals.frame.setVisible(true);
		Globals.frame.setLocationRelativeTo(null);
	}

}
