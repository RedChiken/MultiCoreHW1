package exercise4;

public class Parctice {
	public static final int NUM_END = 200000;
	private static final int NUM_THREAD = 16;
	public static void main(String[] args) {
		int counter = 0;
		
		
//		int i;
//		
//		long startTime = System.currentTimeMillis();
//		for(i = 0; i < NUM_END; i++) {
//			if(isPrime(i)) {
//				counter++;
//			}
//			long endTime = System.currentTimeMillis();
//			long timeDiff = endTime - startTime;
//			System.out.println("Execution Time : " + timeDiff + "ms");
//			System.out.println("1..." + (NUM_END - 1) + " prime# counter = " + counter + "\n");
//		}
		
		
		PrimeSearch p[] = new PrimeSearch[NUM_THREAD];
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < NUM_THREAD; i++) {
			p[i] = new PrimeSearch(i * (NUM_END / NUM_THREAD), (i + 1) * (NUM_END / NUM_THREAD), i);
//			p[i].run();
			p[i].start();
		}
//		for(int i = 0; i < NUM_THREAD; i++){
//			p[i].run();
//		}
		for(int i = 0; i < NUM_THREAD; i++) {
			try {
				p[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(PrimeSearch iter : p){
			counter += iter.counter;
		}
		System.out.println("Total Excution Time : " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.println("Number of prime number : " + counter);
		
	}
	private static boolean isPrime(int x) {
		int i;
		if(x <= 1) {
			return false;
		}
		for(i = 2; i < x; i++) {
			if(((x % i) == 0) && (i != x)) {
				return false;
			}
		}
		return true;
	}

}
