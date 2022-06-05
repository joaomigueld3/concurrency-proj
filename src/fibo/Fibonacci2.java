package fibo;



public class Fibonacci2 {
	public static class ParFibo extends Thread {
	    private int x;
	    public long answer;

	    public ParFibo(int x) {
	        this.x = x;
	    }

	    public void run() {
	        if (x <= 2)
	            answer = 1;
	        else {
	            try {
	            	ParFibo t = new ParFibo(x - 1);		//t decrementa x
	                t.start();						//inicia thread

	                long y = RFibo(x - 2);			//

	                t.join();
	                answer = t.answer + y;

	            } catch (InterruptedException ex) {
	            }
	        }
	    }
	}

	public static long RFibo(int no) {
	    if (no == 1 || no == 2) {
	        return 1;
	    }
	    return RFibo(no - 1) + RFibo(no - 2);
	}

	public static void main(String[] args) throws Exception {
	    try {
	        long start = System.currentTimeMillis();
	        ParFibo f = new ParFibo(40);
	        f.start();
	        f.join();
	        long end = System.currentTimeMillis();
	        System.out.println("Parallel-Fibonacci:" + f.answer + "\tTime:" + (end - start));

	        start = System.currentTimeMillis();
	        long result = RFibo(40);
	        end = System.currentTimeMillis();
	        System.out.println("Normal-Fibonacci: " + result + "\tTime:" + (end - start));


	    } catch (Exception e) {
	    }
	}

}
