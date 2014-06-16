package matrak.misc.design_patterns.structural;

import static matrak.misc.Util.*;

/**
 * <li> Structural Pattern
 * <li> Also known as "Wrapper"
 */
public class Decorator {

	static class ClassWhichDoesSomeStuff 
	{
		public String doIt() {
			return "FooBar";
		}
	}
	
	/**
	 * Decorator for {@link ClassWhichDoesSomeStuff}
	 */
	static class EvenMoreStuffDecorator extends ClassWhichDoesSomeStuff 
	{
		@Override
		public String doIt() {
			return super.doIt() + "Baz";
		}
	}
	
	public static void main(String[] args) {
		log("Basic Class\t", new ClassWhichDoesSomeStuff().doIt());
		log("Decorator\t",   new EvenMoreStuffDecorator().doIt());
	}
}
