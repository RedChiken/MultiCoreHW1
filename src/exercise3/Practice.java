package exercise3;

public class Practice {
	static long num_steps = 100000;
	public static void main(String[] args) {
//		int i;
//		double pi, x, step, sum = 0.0;
//		step = 1.0 / (double) num_steps;
//		for(i = 0; i < num_steps; i++) {
//			x = (i + 0.5) * step;
//			sum = sum + 4.0 / (1.0 + x * x);
//		}
//		pi = step * sum;
		double pi = 0.0;
		SumThread t = new SumThread(0, num_steps, num_steps);
		t.run();
		pi = t.Sum();
		System.out.println("pi = " + pi);
	}
}
