package exercise4_dynamic;

public class Practice {
    static final int NUM_END = 200000;
    static final int NUM_THREAD = 16;
    public static void main(String[] args){
        int counter = 0;
        PrimeSearch_Dynamic p[] = new PrimeSearch_Dynamic[NUM_THREAD];
//        int num[] = new int[NUM_END];
//        for(int i = 0; i < NUM_END; i++){
//            num[i] = i + 1;
//        }
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < NUM_THREAD; i++){
            p[i] = new PrimeSearch_Dynamic(i);
//            p[i].run();
            p[i].start();
        }
        for(int i = 0; i < NUM_THREAD; i++){
            try{
                p[i].join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        for(int i = 0; i < NUM_THREAD; i++){
            counter += p[i].counter;
        }
        System.out.println("Total Excution Time : " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("Number of prime number : " + counter);
    }
}
