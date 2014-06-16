package matrak.misc.design_patterns.behavioral;

import java.util.Observable;

/**
 * Notify objects about changes to some other object.
 */
public class Observer {

	static class KelvinTemperature extends Observable {
		
		private double value = 0.0;
		
		public void setValue(double value) {
			this.value = value;
			setChanged();
			notifyObservers();
		}
		
		public double getValue() {
			return value;
		}
	}
	
	static enum ThermometerType {
		KELVIN,
		CELCIUS,
		FAHRENHEIT;
	}
	
	static class KelvinThermometer implements java.util.Observer 
	{
		protected final int thermometerId;
		
		public KelvinThermometer(int thermometerId, KelvinTemperature temp) {
			this.thermometerId = thermometerId;
			temp.addObserver(this);
			System.out.println("new Thermometer");
		}
		
		@Override
		public void update(Observable o, Object payload) {
			KelvinTemperature t = (KelvinTemperature) o;
			printTemperature(t.getValue());
		}
		
		protected void printTemperature(double value) {
			System.out.println("Thermometer " + thermometerId + ": " + value + " K");
		}
	}
	
	static class CelciusThermometer extends KelvinThermometer {
		
		public CelciusThermometer(int thermometerId, KelvinTemperature temp) {
			super(thermometerId, temp);
		}
		
		@Override
		protected void printTemperature(double value) {
			double celciusValue = Math.round( value - 273.15 );
			System.out.println("Thermometer " + thermometerId + ": " + celciusValue + " C");
		}
	}
	
	public static void main(String[] args) {
		KelvinTemperature temp = new KelvinTemperature();
		
		new KelvinThermometer(1, temp);
		new CelciusThermometer(2, temp);
		
		for(int i = 273; i < 283; i++) {
			temp.setValue(i);
		}
		
	}
	
}
