import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TotalCapacitance extends JPanel implements ActionListener, ChangeListener {

	MainFrame frame;
	JLabel rLabel = new JLabel("Enter Capacitance:");
	ArrayList<JTextField> fields = new ArrayList<JTextField>();
	JTextField resultField = new JTextField();
	JCheckBox seriesBox = new JCheckBox("Series");
	JCheckBox parallelBox = new JCheckBox("Parallel");
	String[] units = {"mF"};
	JCheckBoxMenuItem capacitanceOption1;
	JCheckBoxMenuItem capacitanceOption2;
	JCheckBoxMenuItem capacitanceOption3;

	public TotalCapacitance(MainFrame frame, int capacitors) {

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

		JLabel numResLabel = new JLabel("Select the number of capacitors");

		JSlider slider = new JSlider(1, 10, capacitors);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setLabelTable(slider.createStandardLabels(1));
		slider.setPaintLabels(true);

		panel2.add(numResLabel);
		panel2.add(slider);

		add(panel2);

		for (int i = 0; i < capacitors; i++) {

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
		JMenu capacitanceMenu = new JMenu("Capacitance");
		capacitanceOption1 = new JCheckBoxMenuItem("mF");
		capacitanceOption1.setSelected(true);
		capacitanceOption2 = new JCheckBoxMenuItem("microF");
		capacitanceOption3 = new JCheckBoxMenuItem("nF");
		capacitanceOption1.addActionListener(this);
		capacitanceOption2.addActionListener(this);
		capacitanceOption3.addActionListener(this);
		capacitanceMenu.add(capacitanceOption1);
		capacitanceMenu.add(capacitanceOption2);
		capacitanceMenu.add(capacitanceOption3);
		unitMenu.add(capacitanceMenu);
		
		frame.getJMenuBar().add(saveButton);
		frame.getJMenuBar().add(unitMenu);

	}

	public void actionPerformed(ActionEvent evt) {

		var command = evt.getActionCommand();
		
		switch (command) {

		case "Calculate":
			
			try {				
				resultField.setText(Calculate.totalCapacitance(fields, parallelBox.isSelected(), units[0]));				
			}
			
			catch (Exception e) {				
				JOptionPane.showMessageDialog(this, "Please enter the capacitance values");				
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
		Component component = frame.getContentPane().getComponent(0);

		if (!source.getValueIsAdjusting()) {
			int capacitors = (int) source.getValue();
			frame.remove(component);
			JMenuBar menuBar = new JMenuBar();
			JMenuItem home = new JMenuItem("Home");
			JMenuItem saved = new JMenuItem("Saved");
			home.addActionListener(frame);
			saved.addActionListener(frame);
			
			menuBar.add(home);		
			menuBar.add(saved);
			frame.setJMenuBar(menuBar);
			TotalCapacitance tCapPanel = new TotalCapacitance(frame, capacitors);
			frame.add(tCapPanel);
		}

		frame.setVisible(true);
	}

	public void setUnits(String command) {

		if( capacitanceOption1.isSelected() && command == "mF") {
			units[0] = "mF";
			capacitanceOption2.setSelected(false);
			capacitanceOption3.setSelected(false);
		}
		
		else if(capacitanceOption2.isSelected() && command == "microF"){
			units[0] = "microF";
			capacitanceOption1.setSelected(false);
			capacitanceOption3.setSelected(false);
		}
		
		else if(capacitanceOption3.isSelected()){
			units[0] = "nF";
			capacitanceOption1.setSelected(false);
			capacitanceOption2.setSelected(false);
		}

	}
	
}
