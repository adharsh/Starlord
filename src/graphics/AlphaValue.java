package graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import maths.Ti84;
import simulation.Globals;
import simulation.Launcher;

public class AlphaValue extends JPanel implements ActionListener{

	int c = 0;
	private JTextField alpha = new JTextField();
	private JButton enter = new JButton("Enter");
	private boolean isGravity;
	public AlphaValue(boolean isGravity)
	{
		
		setLayout(new GridLayout(0,2,10,10));
		setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
		
		add(new JLabel("Alpha Value [0 - 63]:"));
		add(alpha);
		add(new JLabel(""));
		
		enter = new JButton("Enter");
		enter.setPreferredSize(new Dimension(100,20));
		//	enter.setMaximumSize(new Dimension(10,10));
		//	enter.setMinimumSize(new Dimension(10,10));
		//	enter.setBounds(20, 30, 50, 50);
		enter.setSize(new Dimension(40,40));
		enter.setActionCommand("Enter");
		enter.addActionListener(this);
		add(enter);
		
		this.isGravity = isGravity;
	}

	@Override
	public void actionPerformed(ActionEvent e){

		c++;

		try{
			if(e.getActionCommand().equals("Enter"))
			{
				Globals.alphaLayerValue = Integer.parseInt(alpha.getText());
				
				if(Globals.alphaLayerValue < 0 || Globals.alphaLayerValue > 255)
					c--;
				
			}
		}catch(Exception e1){
			c--;
		}
		
		if(c == 1){
			
			Globals.frame.getContentPane().remove(this);
			Globals.frame.getContentPane().invalidate();
			Globals.frame.getContentPane().validate();
			
			Ti84.intializeParticles(isGravity);
			Launcher.launch();
		}
	}

}
