package fibo;

import fibo.Fibonacci2.ParFibo;

public class Main {

	public static void main(String[] args) {
		FiboPrimeNormal f1 = new FiboPrimeNormal();
		System.out.println(f1.fib(30));
		System.out.println("is prime: "+ f1.isPrime(8));
		f1.listFib();
		System.out.println();
		f1.fibPrimeNormal();
	}

}
