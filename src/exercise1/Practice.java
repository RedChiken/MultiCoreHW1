package exercise1;

public class Practice {
	private static final int NUM_THREAD = 10;
	
	public static void main(String[] args) {
		System.out.println("Main Thread start!");
		C[] c = new C[NUM_THREAD];
		for(int i = 0; i < NUM_THREAD; ++i) {
			c[i] = new C(i);
			c[i].start();
		}
		System.out.println("main thread calls join()!");
		for(int i = 0; i < NUM_THREAD; ++i) {
			try {
				c[i].join();
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println("main thread ends!");
	}

}