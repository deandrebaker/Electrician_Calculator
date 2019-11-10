

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// 
public class Saved extends JPanel{
	
	MainFrame frame;
	
	static HashMap<String, String> map = new HashMap<String, String>();
	static ArrayList<String> keys = new ArrayList<String>();
	static ArrayList<String> units = new ArrayList<String>();
	
	public Saved(MainFrame frame) {
		
		this.frame = frame;
		setLayout(new GridLayout(0,1));
		
		for(int i = 0; i < map.size(); i++) {
			
			String key = keys.get(i);			
			JLabel label = new JLabel(key + " : " + map.get(key) + " " + units.get(i));			
			add(label);
			
		}
		
	}
	
	public static void add(String key, String value, String unit) {
		
		if(key != "" && value != "" && key != null && value != null) {
			
			keys.add(key);
			map.put(key, value);
			units.add(unit);
			
		}
		
	}
	
	
}
