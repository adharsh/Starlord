package simulation;
<<<<<<< HEAD
import graphics.AlphaValue;
import graphics.DataVisualization;

import java.awt.Dimension;
import java.awt.Font;
=======
import java.awt.Dimension;
>>>>>>> b8070bf2a8d0137cc0fd2a4a4d2f5922866dacb3
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
<<<<<<< HEAD
import javax.swing.SwingConstants;
=======

import graphics.AlphaValue;
import graphics.DataVisualization;
>>>>>>> b8070bf2a8d0137cc0fd2a4a4d2f5922866dacb3

/* ZoomGravityStarLordname - Bagley
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

public class Launcher extends JPanel implements ActionListener{

	int c = 0;
	private static Launcher input;
	private JTextField N = new JTextField();
	private JTextField xRange = new JTextField();
	private JTextField yRange = new JTextField();
	private JTextField zRange = new JTextField();
	private JTextField zoom = new JTextField();
	private JButton initiate = new JButton("Initiate");
	private JCheckBox isGravity = new JCheckBox("Gravity", true);
	private JCheckBox isTable = new JCheckBox("Table", true);

	public Launcher(){
		setLayout(new GridLayout(0,2,10,10));
		setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
		
		zoom.setText("0");
<<<<<<< HEAD
		JPanel rtfm = new JPanel();
		JLabel manual = new JLabel("By Adharsh Babu, AOOD - Art Project");
		
		rtfm.add(new JLabel(""));
		rtfm.add(manual);
		add(rtfm);
		
		JLabel owner = new JLabel("Press Enter Key to Capture Image of Frame");
		Font font = owner.getFont();
		Font italicFont = new Font(font.getFontName(), Font.ITALIC, font.getSize());
		owner.setFont(italicFont);
		add(owner);
=======
>>>>>>> b8070bf2a8d0137cc0fd2a4a4d2f5922866dacb3
		
		add(new JLabel("Number of Particles: "));
		add(N);
		add(new JLabel("X Range: "));
		add(xRange);
		add( new JLabel("Y Range: "));
		add(yRange);
		add(new JLabel("Z Range: "));
		add(zRange);
		add(new JLabel("Zoom Out Translation: "));
		add(zoom);
		add(isGravity);
		add(isTable);
		add(new JLabel(""));
		
		initiate.setActionCommand("Initiate");
		initiate.addActionListener(this);
		add(initiate);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try{
			c++;
			if((c == 1) && e.getActionCommand().equals("Initiate"))
			{
				Globals.N = Integer.parseInt(N.getText());
				Globals.X_RANGE = Float.parseFloat(xRange.getText());
				Globals.Y_RANGE = Float.parseFloat(yRange.getText());
				Globals.Z_RANGE = Float.parseFloat(zRange.getText());
				Globals.ZOOM_FACTOR = Float.parseFloat(zoom.getText());
			}

		}catch(Exception e1){
			//e1.printStackTrace();
			c--;
		}

		if(c==1)
		{
			Globals.isTable = isTable.isSelected();
			
			if(!isTable.isSelected())
			{
				Globals.frame.remove(input);
				
				AlphaValue alpha = new AlphaValue(isGravity.isSelected());
				Globals.frame.add(alpha);
				Globals.frame.setContentPane(alpha);
				
				Globals.frame.setLocationRelativeTo(null);
				Globals.frame.pack();
				Globals.frame.setVisible(true);
			}else{
				
				Globals.frame.remove(input);
				
				DataVisualization inner = new DataVisualization(isGravity.isSelected());
				
				Globals.frame.add(inner);
				Globals.frame.setContentPane(inner);
				
				Globals.frame.pack();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				Globals.frame.setLocation( (int) (dim.width/2.0 - 760.0), (int) (dim.height/2.0 - 883.0/2) );
				
				Globals.frame.setVisible(true);
			}
			
			
		}
	}
	
	
	public static void launch()
	{
		MainThread mainThread = new MainThread();
		Thread launcher = new Thread(mainThread);
		launcher.start();
	}
	
	public static void main(String[] args){
		Globals.frame = new JFrame("StarLord");
		Globals.frame.setResizable(false);
		Globals.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	Globals.frame.setAlwaysOnTop(true);
<<<<<<< HEAD
		
		input = new Launcher();
		Globals.frame.add(input);
		//Globals.frame.setContentPane(input);
		
		
		
=======
		input = new Launcher();
		Globals.frame.add(input);
		Globals.frame.setContentPane(input);

>>>>>>> b8070bf2a8d0137cc0fd2a4a4d2f5922866dacb3
		Globals.frame.pack();
		Globals.frame.setVisible(true);
		Globals.frame.setLocationRelativeTo(null);
	}

}
