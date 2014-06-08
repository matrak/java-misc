package matrak.misc.design_patterns;

import static matrak.misc.Util.*;

/**
 * Structural Pattern
 * Also "Wrapper"
 */
public class Decorator {

	static class ClassWithDoesSomeStuff {
		public String doIt() {
			return "FooBar";
		}
	}
	
	static class EvenMoreStuffDecorator extends ClassWithDoesSomeStuff {
		
		@Override
		public String doIt() {
			return super.doIt() + "Baz";
		}
	}
	
	public static void main(String[] args) {
		log("Basic Class\t", new ClassWithDoesSomeStuff().doIt());
		log("Decorator\t",   new EvenMoreStuffDecorator().doIt());
	}
}
