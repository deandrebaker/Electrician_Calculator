import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TotalResistance extends JPanel implements ActionListener, ChangeListener {

	MainFrame frame;
	JLabel rLabel = new JLabel("Enter Resistance:");
	ArrayList<JTextField> fields = new ArrayList<JTextField>();
	JTextField resultField = new JTextField();
	JCheckBox seriesBox = new JCheckBox("Series");
	JCheckBox parallelBox = new JCheckBox("Parallel");
	String[] units = {"Ohm"};
	JCheckBoxMenuItem resistanceOption1;
	JCheckBoxMenuItem resistanceOption2;

	public TotalResistance(MainFrame frame, int resistors) {

		this.frame = frame;
		setLayout(new GridLayout(0, 1));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0, 1));

		JLabel circuitLabel = new JLabel("Select circuit type:");

		seriesBox.addActionListener(this);
		seriesBox.setSelected(true);
		parallelBox.addActionListener(this);

		JPanel subPanel1 = new JPanel();
		subPanel1.setLayout(new GridLayout(0, 2));
		panel1.add(circuitLabel);
		subPanel1.add(seriesBox);
		subPanel1.add(parallelBox);
		panel1.add(subPanel1);

		add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0, 1));

		JLabel numResLabel = new JLabel("Select the number of resistors");

		JSlider slider = new JSlider(1, 10, resistors);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setLabelTable(slider.createStandardLabels(1));
		slider.setPaintLabels(true);

		panel2.add(numResLabel);
		panel2.add(slider);

		add(panel2);

		for (int i = 0; i < resistors; i++) {

			JTextField field = new JTextField();
			field.addActionListener(this);
			add(field);
			fields.add(field);

		}

		resultField.setEditable(false);

		JButton calcButton = new JButton("Calculate");
		calcButton.addActionListener(this);
		JMenuItem saveButton = new JMenuItem("Save");
		saveButton.addActionListener(this);

		add(calcButton);
		add(resultField);
		
		JMenu unitMenu = new JMenu("Units");	
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
		
		switch (command) {

		case "Calculate":
			
			try {				
				resultField.setText(Calculate.totalResistance(fields, seriesBox.isSelected(), units[0]));				
			}
			
			catch (Exception e) {				
				JOptionPane.showMessageDialog(this, "Please enter the resistance values");				
			}
			
			break;
			
		case "Series":
			
			parallelBox.setSelected( seriesBox.isSelected() ? false : true);
			break;
			
		case "Parallel":
			
			seriesBox.setSelected( parallelBox.isSelected() ? false : true);
			break;
			
		case "Save":
			String response = JOptionPane.showInputDialog(this, "Enter name of this value");
			Saved.add(response, resultField.getText(), units[0]);

			JOptionPane.showMessageDialog(this, (response == null || response.equals("") ? "Not Saved" : "Saved"));
			break;
			
		default:
			
			setUnits(command);
			break;

		}

		frame.setVisible(true);
	}

	public void stateChanged(ChangeEvent e) {

		JSlider source = (JSlider) e.getSource();
		var component = frame.getContentPane().getComponent(0);

		if (!source.getValueIsAdjusting()) {
			int resistors = (int) source.getValue();
			frame.remove(component);
			JMenuBar menuBar = new JMenuBar();
			JMenuItem home = new JMenuItem("Home");
			JMenuItem  saved = new JMenuItem("Saved");
			home.addActionListener(frame);
			saved.addActionListener(frame);
			
			menuBar.add(home);		
			menuBar.add(saved);
			frame.setJMenuBar(menuBar);
			TotalResistance tRPanel = new TotalResistance(frame, resistors);
			frame.add(tRPanel);
		}

		frame.setVisible(true);
	}

	public void setUnits(String command) {

		if(resistanceOption1.isSelected() && command == "Ohm") {
			units[0] = "Ohm";
			resistanceOption2.setSelected(false);
		}
		else if(resistanceOption2.isSelected()){
			units[0] = "kOhm";
			resistanceOption1.setSelected(false);
		}

	}
	
}
