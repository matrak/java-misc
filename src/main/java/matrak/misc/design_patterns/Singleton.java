package matrak.misc.design_patterns;

import static matrak.misc.Util.log;

import java.util.concurrent.CyclicBarrier;

public class Singleton {
	
	/**
	 * Non Thread safe version
	 */
	static class SimpleSingleton {
		
		private static SimpleSingleton INSTANCE;
		
		private SimpleSingleton() {
		}
		
		/**
		 * "lazy initialization"
		 */
		public static SimpleSingleton get() 
		{
			if(INSTANCE == null) 
			{
				INSTANCE = new SimpleSingleton();
				log("new SimpleSingleton()");
			}
			return INSTANCE;
		}
		
		/**
		 * can be performance problem for older jvm-s (factor 100)
		 */
		public synchronized static SimpleSingleton getSync() {
			return get();
		}
		
		/**
		 * still not thread-safe: lock in synchronized block,
		 * but still more than one thread can pass the if(INSTANCE == null)
		 * barrier
		 */
		public static SimpleSingleton getSyncFast() {
			if(INSTANCE == null) {
				synchronized (SimpleSingleton.class) {
					INSTANCE = new SimpleSingleton();
				}
			}
			return INSTANCE;
		}
		
		/**
		 * "Double-checked locking"
		 * Still not the best option: In some "cases" if(INSTANCE == null)
		 * can evaluate to true even if the new instance has not yet been created 
		 * (http://www.theserverside.de/singleton-pattern-in-java/)
		 */
		public static SimpleSingleton getSyncFastDoubleCheck() {
			if(INSTANCE == null) {
				synchronized (SimpleSingleton.class) {
					INSTANCE = (INSTANCE == null) ? new SimpleSingleton() : INSTANCE;
				}
			}
			return INSTANCE;			
		} 
	}
	
	static class CreateSimpleSingletonThread implements Runnable {
		
		final CyclicBarrier gate;
		final int tnum;
		
		CreateSimpleSingletonThread(CyclicBarrier gate, int tnum) {
			this.gate = gate;
			this.tnum = tnum;
		}
		
		@Override
		public void run() 
		{
			try {
				gate.await();
			}
			catch(Exception e) {}
			
//			SimpleSingleton.get();
			SimpleSingleton.getSync();
		}
	}
	
	// ------ main ------ //
	
	public static void main(String[] args) 
	{
		int tnum = 10;
		CyclicBarrier gate = new CyclicBarrier(tnum);
		for(int t = 0; t < tnum; t++) {
			new Thread(new CreateSimpleSingletonThread(gate, t)).start();
		}
	}
	
	
}
