import java.util.*;

import javax.swing.JTextField;

public class Calculate {
	
	static HashMap<String, Double> map = new HashMap<String, Double>();

	public Calculate(){
		
		map.put("V", 1.0);
		map.put("mV", 0.001);
		map.put("A", 1.0);
		map.put("mA", 0.001);
		map.put("Ohm", 1.0);
		map.put("kOhm", 1000.0);
		map.put("mF", 0.001);
		map.put("microF", 0.000001);
		map.put("nF", 0.000000001);
		map.put("W", 1.0);
		map.put("kW", 1000.0);
		map.put("mAh", 1.0);
		map.put("Ah", 1000.0);
		map.put("h", 1.0);
		map.put("days", 24.0);
		
	}
	
	public static String voltage(String cVal, String rVal, String cKey, String rKey, String vKey) {
		
		double current = Double.parseDouble(cVal) * map.get(cKey);
		double resistance = Double.parseDouble(rVal) * map.get(rKey);		
		return Double.toString(current * resistance / map.get(vKey));
		
	}
	
	public static String current(String vVal, String rVal, String vKey, String rKey, String cKey) {
		
		double voltage = Double.parseDouble(vVal) * map.get(vKey);
		double resistance = Double.parseDouble(rVal) * map.get(rKey);		
		return Double.toString(voltage / resistance / map.get(cKey));
		
	}
	
	public static String resistance(String vVal, String cVal, String vKey, String cKey, String rKey) {

		double voltage = Double.parseDouble(vVal) * map.get(vKey);		
		double current = Double.parseDouble(cVal) * map.get(cKey);
		return Double.toString(voltage / current / map.get(rKey));
		
	}
	
	public static String totalResistance(ArrayList<JTextField> fields, boolean isSeries, String rKey) {

		if(isSeries) {
				
			double resistance = 0.0;

			for (int i = 0; i < fields.size(); i++) {

				resistance += Double.parseDouble(fields.get(i).getText()) * map.get(rKey);

			}
			
			return Double.toString(resistance / map.get(rKey));
			
		}
		
		else {
			
			double resistance = 0.0;

			for (int i = 0; i < fields.size(); i++) {

				resistance += 1.0 / (Double.parseDouble(fields.get(i).getText()) * map.get(rKey));

			}
			
			return Double.toString(1.0 / resistance / map.get(rKey));
			
		}
		
	}
	
	public static String totalCapacitance(ArrayList<JTextField> fields, boolean isParallel, String capKey) {

		if(isParallel) {
				
			double capacitance = 0.0;

			for (int i = 0; i < fields.size(); i++) {

				capacitance += Double.parseDouble(fields.get(i).getText()) * map.get(capKey);

			}
			
			return Double.toString(capacitance / map.get(capKey));
			
		}
		
		else {
			
			double capacitance = 0.0;

			for (int i = 0; i < fields.size(); i++) {

				capacitance += 1.0 / (Double.parseDouble(fields.get(i).getText()) * map.get(capKey));

			}
			
			return Double.toString(1.0 / capacitance / map.get(capKey));
			
		}
		
	}
	
	public static String resistor(String sVal, String rVal, String cVal, String sKey, String rKey,String cKey, String resKey){

		double voltageDrop = Double.parseDouble(sVal) * map.get(sKey) - Double.parseDouble(rVal) * map.get(rKey);		
		double current = Double.parseDouble(cVal) * map.get(cKey);
		return Double.toString(voltageDrop / current / map.get(resKey));
		
	}
	
	public static String power(String vVal, String cVal, String rVal, String vKey, String cKey,String rKey, String pKey) {
		
		double current = 0;
		double resistance = 0;
		double voltage = 0;
		int knownValues = 3;
		
		try {voltage = Double.parseDouble(vVal) * map.get(vKey);}
		catch(Exception e){knownValues--;}
		try {current = Double.parseDouble(cVal) * map.get(cKey);}
		catch(Exception e){knownValues--;}
		try {resistance = Double.parseDouble(rVal) * map.get(rKey);}
		catch(Exception e){knownValues--;}
		
		if(knownValues != 2) {
			
			return "error";
			
		}
		
		if(voltage == 0) {
			
			return Double.toString(Math.pow(current, 2) * resistance / map.get(pKey));
			
		}
		
		else if(current == 0) {
			
			return Double.toString(Math.pow(voltage, 2) / resistance / map.get(pKey));
			
		}
		
		else {
			
			return Double.toString(voltage * current / map.get(pKey));
			
		}		
		
	}
	
	public static String battery(String rVal, String dVal, String rKey, String dKey, String tKey) {
		
		double battery = Double.parseDouble(rVal) * map.get(rKey);
		double consumption = Double.parseDouble(dVal) * map.get(dKey);		
		return Double.toString(battery / consumption / map.get(tKey));
		
	}

}
