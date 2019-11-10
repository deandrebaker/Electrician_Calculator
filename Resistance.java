import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Resistance extends JPanel implements ActionListener {
	
	MainFrame frame;
	JLabel cLabel = new JLabel("Enter current:");
	JLabel vLabel = new JLabel("Enter voltage:");
	JTextField cField = new JTextField();
	JTextField vField = new JTextField();
	JTextField resultField = new JTextField();
	String[] units = {"V", "A", "Ohm"};;
	JCheckBoxMenuItem voltageOption1;
	JCheckBoxMenuItem voltageOption2;
	JCheckBoxMenuItem currentOption1;
	JCheckBoxMenuItem currentOption2;
	JCheckBoxMenuItem resistanceOption1;
	JCheckBoxMenuItem resistanceOption2;
	
	public Resistance(MainFrame frame) {
		
		this.frame = frame;
		setLayout(new GridLayout(0,1));
		
		resultField.setEditable(false);
		cField.addActionListener(this);
		vField.addActionListener(this);
		
		JButton calcButton = new JButton("Calculate");
		calcButton.addActionListener(this);
		calcButton.setPreferredSize(new Dimension(150, 80));
		JMenuItem saveButton = new JMenuItem("Save");
		saveButton.addActionListener(this);
		
		add(cLabel);
		add(cField);
		add(vLabel);
		add(vField);
		add(calcButton);
		add(resultField);

		JMenu unitMenu = new JMenu("Units");
		JMenu voltageMenu = new JMenu("Voltage");
		voltageOption1 = new JCheckBoxMenuItem("V");
		voltageOption1.setSelected(true);
		voltageOption2 = new JCheckBoxMenuItem("mV");
		voltageOption1.addActionListener(this);
		voltageOption2.addActionListener(this);
		voltageMenu.add(voltageOption1);
		voltageMenu.add(voltageOption2);
		unitMenu.add(voltageMenu);
		JMenu currentMenu = new JMenu("Current");
		currentOption1 = new JCheckBoxMenuItem("A");
		currentOption1.setSelected(true);
		currentOption2 = new JCheckBoxMenuItem("mA");
		currentOption1.addActionListener(this);
		currentOption2.addActionListener(this);
		currentMenu.add(currentOption1);
		currentMenu.add(currentOption2);
		unitMenu.add(currentMenu);
		JMenu resistanceMenu = new JMenu("Resistance");
		resistanceOption1 = new JCheckBoxMenuItem("Ohm");
		resistanceOption1.setSelected(true);
		resistanceOption2 = new JCheckBoxMenuItem("kOhm");
		resistanceOption1.addActionListener(this);
		resistanceOption2.addActionListener(this);
		resistanceMenu.add(resistanceOption1);
		resistanceMenu.add(resistanceOption2);
		unitMenu.add(resistanceMenu);
		
		frame.getJMenuBar().add(saveButton);
		frame.getJMenuBar().add(unitMenu);
	}
	
	public void actionPerformed(ActionEvent evt) {
		
		String command = evt.getActionCommand();
		
		switch(command) {
				
		case "Calculate":
			
			try {				
				resultField.setText(Calculate.resistance(vField.getText(), cField.getText(), units[0], units[1], units[2]));				
			}
			
			catch (Exception e) {				
				JOptionPane.showMessageDialog(this, "Please enter the voltage and current");				
			}
			
			break;
			
		case "Save":
			String response = JOptionPane.showInputDialog(this, "Enter name of this value");
			Saved.add(response, resultField.getText(), units[2]);

			JOptionPane.showMessageDialog(this, (response == null || response.equals("") ? "Not Saved" : "Saved"));
			break;
			
		default:
			setUnits(command);
			break;
			
		}
		
		frame.setVisible(true);			
	}
	
	public void setUnits(String command) {
		
		
		if(voltageOption1.isSelected() && command == "V") {
			units[0] = "V";
			voltageOption2.setSelected(false);
		}
		else if(voltageOption2.isSelected()){
			units[0] = "mV";
			voltageOption1.setSelected(false);
		}
		
		if(currentOption1.isSelected() && command == "A") {
			units[1] = "A";
			currentOption2.setSelected(false);
		}
		else if(currentOption2.isSelected()){
			units[1] = "mA";
			currentOption1.setSelected(false);
		}
		
		if(resistanceOption1.isSelected() && command == "Ohm") {
			units[2] = "Ohm";
			resistanceOption2.setSelected(false);
		}
		else if(resistanceOption2.isSelected()){
			units[2] = "kOhm";
			resistanceOption1.setSelected(false);
		}
	}

}
