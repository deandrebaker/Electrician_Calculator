import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Battery extends JPanel implements ActionListener {
	
	MainFrame frame;
	JLabel ratingLabel = new JLabel("Enter capacity rating:");
	JLabel deviceLabel = new JLabel("Enter charge consumed by device:");
	JTextField ratingField = new JTextField();
	JTextField deviceField = new JTextField();
	JTextField resultField = new JTextField();
	String[] units = {"mAh", "h"};
	JCheckBoxMenuItem batteryOption1;
	JCheckBoxMenuItem batteryOption2;
	JCheckBoxMenuItem timeOption1;
	JCheckBoxMenuItem timeOption2;
	
	public Battery(MainFrame frame) {
		
		this.frame = frame;
		setLayout(new GridLayout(0,1));
		
		resultField.setEditable(false);
		ratingField.addActionListener(this);
		deviceField.addActionListener(this);
		
		JButton calcButton = new JButton("Calculate");
		calcButton.addActionListener(this);
		JMenuItem saveButton = new JMenuItem("Save");
		saveButton.addActionListener(this);
		
		add(ratingLabel);
		add(ratingField);
		add(deviceLabel);
		add(deviceField);
		add(calcButton);
		add(resultField);
		
		JMenu unitMenu = new JMenu("Units");
		JMenu batteryMenu = new JMenu("Battery capacity");
		batteryOption1 = new JCheckBoxMenuItem("mAh");
		batteryOption1.setSelected(true);
		batteryOption2 = new JCheckBoxMenuItem("Ah");
		batteryOption1.addActionListener(this);
		batteryOption2.addActionListener(this);
		batteryMenu.add(batteryOption1);
		batteryMenu.add(batteryOption2);
		unitMenu.add(batteryMenu);		
		JMenu timeMenu = new JMenu("Time");
		timeOption1 = new JCheckBoxMenuItem("h");
		timeOption1.setSelected(true);
		timeOption2 = new JCheckBoxMenuItem("days");
		timeOption1.addActionListener(this);
		timeOption2.addActionListener(this);
		timeMenu.add(timeOption1);
		timeMenu.add(timeOption2);
		unitMenu.add(timeMenu);	
		
		frame.getJMenuBar().add(saveButton);
		frame.getJMenuBar().add(unitMenu);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		
		String command = evt.getActionCommand();
		
		switch(command) {
				
		case "Calculate":
			
			try {
				resultField.setText(Calculate.battery(ratingField.getText(), deviceField.getText(), units[0], units[0], units[1]));
			}
			
			catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Please enter the rating charge and device consumption");
			}
			
			frame.setVisible(true);
			break;
			
		case "Save":
			String response = JOptionPane.showInputDialog(this, "Enter name of this value");
			Saved.add(response, resultField.getText(), units[1]);

			JOptionPane.showMessageDialog(this, (response == null || response.equals("") ? "Not Saved" : "Saved"));
			break;

		default:
				
			setUnits(command);
			break;
			
		}
		
	}


	public void setUnits(String command) {
		
		if(batteryOption1.isSelected() && command == "mAh") {
			units[0] = "mAh";
			batteryOption2.setSelected(false);
		}
		else if(batteryOption2.isSelected()){
			units[0] = "Ah";
			batteryOption1.setSelected(false);
		}
		
		if(timeOption1.isSelected() && command == "h") {
			units[1] = "h";
			timeOption2.setSelected(false);
		}
		else if(timeOption2.isSelected()){
			units[1] = "days";
			timeOption1.setSelected(false);
		}
		
	}	
	
}
