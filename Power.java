import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Power extends JPanel implements ActionListener {

	MainFrame frame;
	JLabel label = new JLabel("Enter 2 of the values:");
	JLabel voltageLabel = new JLabel("Voltage:");
	JLabel currentLabel = new JLabel("Current:");
	JLabel resistanceLabel = new JLabel("Resistance:");
	JTextField voltageField = new JTextField();
	JTextField currentField = new JTextField();
	JTextField resistanceField = new JTextField();
	JTextField resultField = new JTextField();
	String[] units = { "V", "A", "Ohm", "W" };
	JCheckBoxMenuItem voltageOption1;
	JCheckBoxMenuItem voltageOption2;
	JCheckBoxMenuItem currentOption1;
	JCheckBoxMenuItem currentOption2;
	JCheckBoxMenuItem resistanceOption1;
	JCheckBoxMenuItem resistanceOption2;
	JCheckBoxMenuItem powerOption1;
	JCheckBoxMenuItem powerOption2;

	public Power(MainFrame frame) {

		this.frame = frame;
		setLayout(new GridLayout(0, 1));

		resultField.setEditable(false);
		voltageField.addActionListener(this);
		currentField.addActionListener(this);
		resistanceField.addActionListener(this);

		JButton calcButton = new JButton("Calculate");
		calcButton.addActionListener(this);
		JMenuItem saveButton = new JMenuItem("Save");
		saveButton.addActionListener(this);

		add(voltageLabel);
		add(voltageField);
		add(currentLabel);
		add(currentField);
		add(resistanceLabel);
		add(resistanceField);
		add(calcButton);
		add(resultField);

		JMenu unitMenu = new JMenu("Units");
		JMenu voltageMenu = new JMenu("Voltage");
		voltageMenu.addActionListener(this);
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
		JMenu powerMenu = new JMenu("Power");
		powerOption1 = new JCheckBoxMenuItem("W");
		powerOption1.setSelected(true);
		powerOption2 = new JCheckBoxMenuItem("kW");
		powerOption1.addActionListener(this);
		powerOption2.addActionListener(this);
		powerMenu.add(powerOption1);
		powerMenu.add(powerOption2);
		unitMenu.add(powerMenu);

		frame.getJMenuBar().add(saveButton);
		frame.getJMenuBar().add(unitMenu);

	}

	public void actionPerformed(ActionEvent evt) {

		String command = evt.getActionCommand();

		switch (command) {

		case "Calculate":

			String power = Calculate.power(voltageField.getText(), currentField.getText(), resistanceField.getText(), 
					units[0], units[1], units[2], units[3]);

			try {
				Double.parseDouble(power);
				resultField.setText(power);
			}

			catch (Exception e) {

				JOptionPane.showMessageDialog(this, "Please enter 2 of the following: voltage, resistance, or current");

			}
			break;

		case "Save":
			String response = JOptionPane.showInputDialog(this, "Enter name of this value");
			Saved.add(response, resultField.getText(), units[3]);

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
		
		if (powerOption1.isSelected() && command == "W") {
			units[3] = "W";
			powerOption2.setSelected(false);
		} 
		else if (powerOption2.isSelected()) {
			units[3] = "kW";
			powerOption1.setSelected(false);
		}
	}

}
