package exercise1;


public class C extends Thread{
	int i;
	C(int i){
		this.i = i;
	}
	public synchronized void run() {
		System.out.println("Thread " + i + " says hi");
		try {
			sleep(500);
		} catch(InterruptedException e) {
			
		}
		System.out.println("Thread " + i + " says bye");
	}
}
