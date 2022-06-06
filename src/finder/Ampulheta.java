package finder;

public class Ampulheta {

	static void imprimirMatriz2(String[][] matriz) {
		for(int a=0;a<matriz.length;a++) {
			for(int b=0;b<matriz.length;b++) {
				if(matriz[a][b]==null) {
					matriz[a][b]=" ";
				}
				System.out.print(matriz[a][b]+" ");
			}
			System.out.println();
		}
	}
	
	static void ampulheta(String[][] matriz) {
		for(int a=0;a<matriz.length;a++) {
			for(int b=0;b<matriz.length;b++) {
				if(a==b|| a==0 || a==matriz.length-1 ) {
					matriz[a][b]="8";
					//System.out.print(matriz[a][b]+" ");
					
				}
			}
		}	
					for(int c=0;c<matriz.length;c++) {
						int k=0;
						for(int d=matriz.length-1;d>=0;d--) {
							
							if(c==k) {
								matriz[c][d]="3";
							}k++;
							
						}
					}
					//System.out.print(matriz[a][b]+ " ");				
			//System.out.println();		
		//return matriz;
	}
	static void ampulhetaTop(int a, String[][] matriz) {
		if(a==matriz.length-1) {return;	}
		else {
			matriz[0][a]="1";
			ampulhetaTop(a+1,matriz);
		}
	}
	static void ampulhetaRight(String[][] matriz) {
		for(int a=0;a<matriz.length;a++) {
			for(int b=0;b<matriz.length;b++) {
				if (a==b) matriz[a][b]="2";
			}				
	}
	}
	static void ampulhetaLeft(String[][] matriz) {
		for(int c=0;c<matriz.length;c++) {
			int k=0;
			for(int d=matriz.length-1;d>=0;d--) {
				
				if(c==k) {
					matriz[c][d]="3";
				}k++;
				
			}
		}
	}
	static void ampulhetaBottom(int a,String[][] matriz) {
		if(a==matriz.length-1) {return;	}
		else{
			matriz[matriz.length-1][a]="4";
			ampulhetaBottom(a+1,matriz);			
	}
	}
	static void ampulhetaParalelo(String[][] matriz) {
		Thread t1 = new Thread( () -> {
			ampulhetaTop(0, matriz);
			//System.out.println("ola thread 1");
        });
		Thread t2 = new Thread( () -> {
			ampulhetaLeft(matriz);          
			//System.out.println("ola thread 2");
        });
		Thread t3 = new Thread( () -> {
			ampulhetaRight(matriz);
			//System.out.println("ola thread 3");
        });
		Thread t4 = new Thread( () -> {
			ampulhetaBottom(0, matriz);
			//System.out.println("ola thread 4");
        });
		
		
		t1.start();t2.start(); t3.start(); t4.start();
        try { t1.join(); t2.join(); t3.join(); t4.join();} catch (Exception ex) { System.err.println(ex); }
	}
	
	public static void main(String[] args) {
		String[][] matriz = new String[10000][10000];
		
		long start,end,tempo,somaTempo = 0;
		long startPar,endPar,tempoPar=0;
		start = System.currentTimeMillis();
		ampulheta(matriz);		
		end = System.currentTimeMillis();
    	tempo=end-start;
    	somaTempo+=tempo;
    	
    	startPar=System.currentTimeMillis();
    	ampulhetaParalelo(matriz);
    	endPar=System.currentTimeMillis();
    	tempoPar=endPar-startPar;
    	System.out.println("Tempo de sequencial : " + (tempo) + "ms");
    	//imprimirMatriz2(matriz);
    	System.out.println("Tempo de paralelo : " + (tempoPar) + "ms");    	
    	//imprimirMatriz2(matriz);

	}

}
