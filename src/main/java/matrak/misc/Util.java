package matrak.misc;

public class Util {
	
	public static void log(Object... oo) 
	{
		String log = "";
		if(oo == null || oo.length == 0) {
			log = "null";
		}
		else {
			StringBuilder b = new StringBuilder();
			for(Object o : oo) {
				b.append( (o != null) ? o.toString() : "null" );
				b.append( ", ");
			}
			log = b.substring(0, b.length() - 2);
		}
		
		System.out.println(log);
	}
	
}
