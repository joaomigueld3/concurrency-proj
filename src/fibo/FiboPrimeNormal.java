package fibo;

public class FiboPrimeNormal {
	
	public int fib(int n) {
		if(n == 0 ) {
			return 0;
		}
		else if(n == 1 || n == 2) {
			return 1;
		}
		return fib(n-1) + fib(n-2);
	}

	public boolean isPrime(int n) {
		int m=(int)Math.round(Math.sqrt(n));
		if(n==1 || n==2) {
			return true;
		}
		for(int i=2; i<=m;i++) {
			if(n%i==0) {
				return false;
			}
	}
		return true;
	
}
	//fazer lista de fib até fib(n) < 100000
	//verificar se cada um é primo, se for add em outra lista
	private int[] listFibAux() {
		int[] listRes = new int[32];
		for(int i=0;i<listRes.length;i++) {
			if(fib(i)>1000000) {
				break;
			}
			listRes[i]=fib(i);
		}
		return listRes;
	}
	public void listFib() {
		for(int i=0;i<listFibAux().length;i++) {
			System.out.print(listFibAux()[i]+", ");
			if(listFibAux()[i+1]==0) {
				break;
			}
		}
	}
	/*multi-threaded Java program to print all numbers below 100,000 that are both prime and Fibonacci number */
	public void fibPrimeNormal() {
		for(int i = 0; i < listFibAux().length; i++) {
			if(isPrime(listFibAux()[i])==true){
				System.out.print(listFibAux()[i]+", ");
			}
		}
		
	}
	
	
}
