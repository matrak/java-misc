package matrak.misc.design_patterns.structural;

/**
 * Facade provides a simple interface to a complex system:
 * makes it easier to use.
 */
public class Facade {
	
	public static interface InkPrinter 
	{
		void turnOn();
		boolean feedPpaper(int pages);
		boolean checkInk();
		void inputData(String formatedText);
		void printPage();
		void turnOff();
	}	
	
	public static class PrinterFacade {
		
		InkPrinter printer;
		
		public void printSomeFunnyStuff(String joke) {
			printer.turnOn();
			
			if(printer.checkInk() && printer.feedPpaper(1)) {
				printer.inputData(joke);
				printer.printPage();
			}
			
			printer.turnOff();
		}
		
	}
		
}
