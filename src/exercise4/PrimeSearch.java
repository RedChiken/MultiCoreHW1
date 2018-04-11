package exercise4;

public class PrimeSearch extends Thread {
	int low, high, threadNumber;
	public int counter = 0;
	public PrimeSearch(int low, int high, int tNum) {
		this.low = low;
		this.high = high;
		threadNumber = tNum;
	}
	
	public void run() {
		long startTime = System.currentTimeMillis();
		for(int i = low; i < high; i++) {
			if(isPrime(i)) {
				counter++;
//				System.out.println(threadNumber + " " + i);
			}
		}
		long endTime = System.currentTimeMillis();
		long timeDiff = endTime - startTime;
		System.out.println("Thread " + threadNumber + " Execution Time : " + timeDiff + "ms");
		System.out.println(low + "..." + (high - 1) + " prime# counter = " + counter + "\n");
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
