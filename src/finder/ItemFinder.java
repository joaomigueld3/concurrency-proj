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
	
	static void find1Aux(int i, int j, int maxI,int maxJ, int[][] entry,int res[]){
		//System.out.println(i+", "+j);
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
			if(i+1<maxI) {
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
		
	static int[] findParallel(int[][] entry,int[] arr) {
		int meio = entry.length/2;
		int fim = entry.length;
		int p1[] = new int[2];
		Thread t1 = new Thread( () -> {
			find1Aux(0,0,meio,meio,entry,p1);
			//System.out.println("ola thread 1");
        });
		Thread t2 = new Thread( () -> {
			find1Aux(0,meio,meio,fim,entry,p1);
			//System.out.println("ola thread 2");
        });
		Thread t3 = new Thread( () -> {
			find1Aux(meio,0,fim,meio,entry,p1);
			//System.out.println("ola thread 3");
        });
		Thread t4 = new Thread( () -> {
			find1Aux(meio,meio,fim,fim,entry,p1);
			//System.out.println("ola thread 4");
		});
		t1.start();t2.start();t3.start();t4.start();
		//System.out.println("Number of threads " + Thread.activeCount());
        try { t1.join(); t2.join();t3.join();t4.join(); } 
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
		int[][] entry = new int[100][100];
		int posX = r.nextInt(100);int posY = r.nextInt(100);
		System.out.println(r.nextInt(100)+", "+r.nextInt(100));
		entry[0][8]=1;
		int meio = entry.length/2;
		int fim = entry.length;
		int p1[] = new int[2];
		long start,end,tempo,somaTempo = 0;
		start = System.currentTimeMillis();
		find1Aux(0,0,fim,fim,entry,p1);
		//findParallel(entry,p1);
		end = System.currentTimeMillis();
    	tempo=end-start;
    	somaTempo+=tempo;
    	System.out.println("Tempo de execução : " + (tempo) + "ms");
    	System.out.println(p1[0]+", "+p1[1]);
    	//System.out.println(findParallel(entry,p1)[0] + ", "+findParallel(entry,p1)[1]);
    	
	}
}
