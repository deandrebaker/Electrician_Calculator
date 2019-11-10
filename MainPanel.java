import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// The home screen for the application and displays the functions of the calculator
public class MainPanel extends JPanel implements ActionListener{
	
	MainFrame frame;
	
	public MainPanel(MainFrame frame) {
		
		this.frame = frame;
		setLayout(new GridLayout(0,1));
		
		// Creates buttons for each function of the calculator and adds it to the MainFrame window
		JButton voltageButton = createButton("Voltage");
		JButton currentButton = createButton("Current");
		JButton resistanceButton = createButton("Resistance");
		JButton totalResistanceButton= createButton("Total Resistance");
		JButton totalCapacitanceButton = createButton("Total Capacitance");
		JButton resistorButton = createButton("Dropping Resistor");
		JButton powerButton = createButton("Power");
		JButton batteryButton = createButton("Battery Life");
		
		setLayout(new GridLayout(0, 2));
		add(voltageButton);
		add(currentButton);
		add(resistanceButton);
		add(totalResistanceButton);
		add(totalCapacitanceButton);
		add(resistorButton);
		add(powerButton);
		add(batteryButton);
		
	}
	
	// Creates a JButton and adds the ActionListener to the button
	public JButton createButton(String text) {
		
		JButton button = new JButton(text);
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(150, 80));
		return button;
	}	
	
	public void actionPerformed(ActionEvent evt) {
		
		String command = evt.getActionCommand();		
		Component component = frame.getContentPane().getComponent(0);
		
		// Removes the MainPanel from the window and creates and adds a new JPanel depending on the button pressed
		switch(command) {
		
		case "Voltage":
			
			frame.remove(component);
			Voltage voltage = new Voltage(frame);
			frame.add(voltage);
			break;
			
		case "Current":
			
			frame.remove(component);
			Current current = new Current(frame);
			frame.add(current);
			break;
			
		case "Resistance":
			
			frame.remove(component);
			Resistance resistance= new Resistance(frame);
			frame.add(resistance);
			break;
			
		case "Total Resistance":
			
			frame.remove(component);
			TotalResistance totalResistance = new TotalResistance(frame, 1);
			frame.add(totalResistance);
			break;
			
		case "Total Capacitance":
			
			frame.remove(component);
			TotalCapacitance totalCapacitance = new TotalCapacitance(frame, 1);
			frame.add(totalCapacitance);
			break;
			
		case "Dropping Resistor":
			
			frame.remove(component);
			Resistor resistor = new Resistor(frame);
			frame.add(resistor);
			break;
				
		case "Power":
			
			frame.remove(component);
			Power power = new Power(frame);
			frame.add(power);
			break;
			
		case "Battery Life":
			
			frame.remove(component);
			Battery battery = new Battery(frame);
			frame.add(battery);
			break;
			
		}
		
		frame.setVisible(true);
		
	}

}
