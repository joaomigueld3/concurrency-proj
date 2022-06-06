package finder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ItemFinder {

	 static int[][] matrix(){
		 int[][] matrix = new int[4][4];
		 matrix[2][2]=1;
		 return matrix;
	 }
	 public static int[][] copy(int[][] src) {
	        if (src == null) {
	            return null;
	        }
	 
	        int[][] copy = new int[src.length][];
	        for (int i = 0; i < src.length; i++) {
	            copy[i] = src[i].clone();
	        }
	 
	        return copy;
	    }
	
	static void find1Aux2(int i, int j, int maxI,int maxJ, int[][] entry,int res[]){
		System.out.println(i+", "+j);
		if(i==maxI && j==maxJ) {
			return;
		}
		if(entry[i][j]==1) {
			res[0]=i;res[1]=j;
			return;
		} else {
			if(j+1<maxJ) {
				find1Aux(i, j + 1, maxJ, maxJ, entry, res);				
			}
			if(j+1==maxJ) {
				find1Aux(i + 1, j, maxJ, maxJ, entry, res);
			}
			
			/*if(j>0) {
				find1Aux(i, j - 1, maxJ, maxJ, entry, res);
			}
			if(i>0) {
				find1Aux(i - 1, j, maxJ, maxJ, entry, res);
			}*/
						
		}	
			 
	}
	static void find1Aux(int i, int j, int maxI,int maxJ, int[][] entry,int res[]) {
		
		for(int a=i;a<=maxI;a++) {
			for(int b=j;b<=maxJ;b++) {
				//System.out.println(a+", "+b);
				if(entry[a][b]==1) {
					res[0]=a;res[1]=b;
					return;
				}
			}
		}
	}
	static void findBackwards(int i, int j, int minI,int minJ, int[][] entry,int res[]) {
		for(int a=i;a>=minI;a--) {
			for(int b=j;b>=minJ;b--) {
				//System.out.println(a+", "+b);
				if(entry[a][b]==1) {
					res[0]=a;res[1]=b;
					return;
				}
			}
		}
	}
		
	static int[] findParallel(int[][] entry,int[] arr) {
		int meio = entry.length/2;
		int fim = entry.length-1;
		int p1[] = new int[2];
		Thread t1 = new Thread( () -> {
			find1Aux(0,0,meio,fim,entry,p1);
			System.out.println("ola thread 1");
        });
		Thread t2 = new Thread( () -> {
			findBackwards(0,fim,meio,meio,entry,p1);
			System.out.println("ola thread 2");
        });
		Thread t3 = new Thread( () -> {
			find1Aux(meio,0,fim,meio,entry,p1);
			System.out.println("ola thread 3");
        });
		Thread t4 = new Thread( () -> {
			findBackwards(fim,fim,meio,0,entry,p1);
			System.out.println("ola thread 4");
		});
		t1.start();t2.start(); t3.start();t4.start();
		System.out.println("Number of threads " + Thread.activeCount());
        try { t1.join(); t2.join(); t3.join();t4.join(); } 
        catch (Exception ex) { System.err.println(ex); } 
        
        //System.out.println(p1[0]+", "+p1[1]);
        //System.out.println("Number of threads " + Thread.activeCount());
        return p1;
       /* for(int i=0;i<2;i++) {
        	if(p1[0]!=0) {
        		arr = Arrays.copyOfRange(p1, 0, p1.length);
        		break;
        	}
        }*/
	}
	public static void main(String args[]) {
		Random r = new Random(123456789L);
		int[][] entry = new int[20000][20000];
		int posX = r.nextInt(100);int posY = r.nextInt(100);
		//System.out.println(r.nextInt(100)+", "+r.nextInt(100));
		entry[19000][1000]=1;
		int meio = entry.length/2;
		int fim = entry.length-1;
		int p1[] = new int[2];
		int p2[] = new int[2];
		long start,end,tempo,somaTempo = 0;
		long startPar,endPar,tempoPar=0;
		start = System.currentTimeMillis();
		find1Aux(0,0,fim,fim,entry,p1);		
		end = System.currentTimeMillis();
    	tempo=end-start;
    	somaTempo+=tempo;
    	
    	startPar=System.currentTimeMillis();
    	p2 =findParallel(entry,p1);
    	endPar=System.currentTimeMillis();
    	tempoPar=endPar-startPar;
    	System.out.println("Tempo de sequencial : " + (tempo) + "ms");
    	System.out.println("normal: "+p1[0]+", "+p1[1]);
    	System.out.println("Tempo de paralelo : " + (tempoPar) + "ms");    	
    	System.out.println("paralelo: "+p2[0] + ", "+p2[1]);
    	
	}
}
