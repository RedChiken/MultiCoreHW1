package exercise3;

public class SumThread extends Thread{
	double step;
	long low, high;
	long num_steps;
	public double sum = 0.0;
	
	public SumThread(long l, long h, long ns) {
		num_steps = ns;
		step = 1.0 / (double)num_steps;
		low = l;
		high = h;
	}
	
	public synchronized void run() {
		double x = 0;
		if((high - low) < 10) {
			for(long i = low; i < high; i++) {
				x = (i + 0.5) * step;
				sum = sum + (4.0 / (1.0 + x * x));
			}
		}
		else {
			SumThread left = new SumThread(low, (low + high) / 2, num_steps);
			SumThread right = new SumThread((low + high) / 2, high, num_steps);
			left.run();
			right.run();
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
			sum = left.sum + right.sum;
		}
	}
	
	public double Sum() {
		return sum * step;
	}
}
