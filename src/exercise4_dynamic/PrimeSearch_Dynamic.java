package exercise4_dynamic;

public class PrimeSearch_Dynamic extends Thread {
    public static final int NUM_END = 200000;
    public int counter, tNum;
    public static Integer iter;
    public PrimeSearch_Dynamic(int tNum){
        counter = 0;
        this.tNum = tNum;
        iter = 0;
    }

    public void run(){
        long startTime = System.currentTimeMillis();
        synchronized (iter){
            while (iter < NUM_END) {
                if (isPrime(iter++)) {
                    counter++;
//                    System.out.println(tNum + " " + iter);
                }
            }
        }
        //각 쓰레드가 서로 다르게 연산함
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("Thread " + tNum + " Execution Time : " + timeDiff + "ms");
        System.out.println("prime# counter = " + counter + "\n");
    }

    private synchronized boolean isPrime(int x) {
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
