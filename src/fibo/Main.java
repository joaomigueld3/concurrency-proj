package fibo;

import fibo.Fibonacci2.ParFibo;

public class Main {

	public static void main(String[] args) {
		FiboPrimeNormal f1 = new FiboPrimeNormal();
		System.out.println("fib de 30: "+f1.fib(30));
		System.out.println("is prime: "+ f1.isPrime(8));
		System.out.print("fibs até 1000000: ");f1.listFib();
		System.out.println();
		long start = System.currentTimeMillis(); 
		System.out.print("fib e prime: ");f1.fibPrimeNormal();
		long end = System.currentTimeMillis();
		System.out.println("tempo de execucao: "+(end-start)+"ms");
	}

}
