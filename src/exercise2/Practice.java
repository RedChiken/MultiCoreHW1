package exercise2;

public class Practice {
	private static int NUM_END = 10000;
	private static int NUM_THREAD = 4; 
	public static void main(String[] args) {
		if(args.length == 2) {
			NUM_THREAD = Integer.parseInt(args[0]);
			NUM_END = Integer.parseInt(args[1]);
		}
		int[] int_arr = new int[NUM_END];
		int i, s = 0;
		for(i = 0; i < NUM_END; i++) {
			int_arr[i] = i + 1;
		}
		s += sum(int_arr);
		System.out.println("sum = " + s);
	}
	static int sum(int[] arr) {
		int sum = 0;
		int len = arr.length;
//		Single Thread - Naive version
//		for(int iter : arr) {
//			sum += iter;
//		}
		
//		Multi Thread = Naive version
//		SumThread st[] = new SumThread[NUM_THREAD];
//		for(int i = 0; i < NUM_THREAD; ++i) {
//			st[i] = new SumThread(arr, (i * len) / NUM_THREAD, ((i + 1) * len) / NUM_THREAD);
//			st[i].start();
//			try {
//				st[i].join();
//				sum += st[i].ans;
//			} catch (InterruptedException e) {
//				
//			}
//		}
		
		SumThread t = new SumThread(arr, 0, arr.length);
		t.run();
		sum = t.ans;
		return sum;
	}
}
