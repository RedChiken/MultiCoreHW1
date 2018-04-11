package exercise2;

public class SumThread extends Thread {
	int lo, hi;
	int[] arr;
	int ans = 0;
	SumThread(int[] a, int l, int h){
		lo = l;
		hi = h;
		arr = a;
	}
	public synchronized void run() {
//		for(int i = lo; i < hi; ++i) {
//			ans += arr[i];
//			System.out.println(ans);
//		}
		if((hi - lo) < 10) {
			for(int i = lo; i < hi; i++) {
				ans += arr[i];
			}
		}
		else {
			SumThread left = new SumThread(arr, lo, (hi + lo) / 2);
			SumThread right = new SumThread(arr, (hi + lo) /2, hi);
			left.start();
			right.start();
			try {
				left.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				right.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ans = left.ans + right.ans;
		}
	}
}
