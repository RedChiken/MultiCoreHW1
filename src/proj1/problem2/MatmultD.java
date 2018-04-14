package proj1.problem2;

import java.util.*;
import java.lang.*;

// command-line execution example) java MatmultD 6 < mat500.txt
// 6 means the number of threads to use
// < mat500.txt means the file that contains two matrices is given as standard input
//
// In eclipse, set the argument value and file input by using the menu [Run]->[Run Configurations]->{[Arguments], [Common->Input File]}.

// Original JAVA source code: http://stackoverflow.com/questions/21547462/how-to-multiply-2-dimensional-arrays-matrix-multiplication
public class MatmultD
{
    public final static int THREAD_NUM = 16;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String [] args)
    {
        int a[][]=readMatrix();
        int b[][]=readMatrix();

        long startTime = System.currentTimeMillis();
        MultiplyMatrix p[] = new MultiplyMatrix[THREAD_NUM];
        for(int i = 0; i < THREAD_NUM; i++){
            p[i] = new MultiplyMatrix(a, b, i);
            p[i].start();
        }
        for(int i = 0; i < THREAD_NUM; i++){
            try{
                p[i].join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        int[][] c = MultiplyMatrix.ret;

        long endTime = System.currentTimeMillis();

        printMatrix(c);

        System.out.printf("Total Excute TIme : %4d ms\n", (endTime - startTime));
    }

    public static int[][] readMatrix() {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = sc.nextInt();
            }
        }
        return result;
    }

    public static void printMatrix(int[][] mat) {
        System.out.println("Matrix["+mat.length+"]["+mat[0].length+"]");
        int rows = mat.length;
        int columns = mat[0].length;
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%4d " , mat[i][j]);
                sum+=mat[i][j];
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Matrix Sum = " + sum + "\n");
    }
}

class MultiplyMatrix extends Thread{
    public int a[][], b[][];
    public static int ret[][] = null;
    int m, n, o, thread_num;
    static Integer t = 0;

    public MultiplyMatrix(int a[][], int b[][], int tnum){
        this.a = a;
        this.b = b;
        m = a.length;
        n = a[0].length;
        o = b[0].length;
        if(ret == null){
            ret = new int[m][o];
        }
        thread_num = tnum;
    }

    public synchronized void run(){
        long startTime = System.currentTimeMillis();
        synchronized (t){
            while(t < m * n * o){
                int z = (t % (n * o)) / o;
                int y = (t % (n * o)) % o;
                int x = t / (n * o);
                synchronized (ret){
//                    System.out.println(thread_num + " running");
                    ret[x][z] += a[x][y] * b[y][z];
                }
                t++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Thread Number : " + thread_num + ", Excute time : " + (endTime - startTime) + "ms");
    }
}