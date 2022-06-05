package finder;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemFinder {

	 static int[][] matrix(){
		 int[][] matrix = new int[1001][1001];
		 matrix[500][500]=1;
		 return matrix;
	 }
	
	static void find1Aux(int i, int j, int maxI,int maxJ, int arr[]){
		
		if(i==maxI && j==maxJ) {
			return;
		}
		if(matrix()[i][j]==1) {
			arr[0]=i;arr[1]=j;
			return;
		}
			 find1Aux(i+1,j+1,maxI,maxJ,arr);		
	}
		
	static void findParallel(int[] arr) {
		int p1[] = new int[2];
		int p3[] = new int[2];
		int p2[] = new int[2];
		int p4[] = new int[2];
		Thread t1 = new Thread( () -> {
			find1Aux(0,0,500,500,p1);
        });
		Thread t2 = new Thread( () -> {
			find1Aux(0,500,500,1000,p2);
        });
		Thread t3 = new Thread( () -> {
			find1Aux(500,0,1000,500,p3);
        });
		Thread t4 = new Thread( () -> {
			find1Aux(500,500,1000,1000,p4);
        });
		t1.start();t2.start();t3.start();t4.start();
        try { t1.join(); t2.join();t3.join();t4.join(); } 
        catch (Exception ex) { System.err.println(ex); } 
       
        for(int i=0;i<2;i++) {
        	if(p1[0]!=0) {
        		arr = Arrays.copyOfRange(p1, 0, p1.length);
        		break;
        	}else if(p2[0]!=0) {
        		arr = Arrays.copyOfRange(p2, 0, p2.length);
        		break;
        	}else if(p3[0]!=0) {
        		arr = Arrays.copyOfRange(p3, 0, p3.length);
        		break;
        	}
        	else if(p4[0]!=0) {
        		arr = Arrays.copyOfRange(p4, 0, p4.length);
        		break;
        	}
        }
	}
	public static void main(String args[]) {
		
		int p1[] = new int[2];
		long start,end,tempo,somaTempo = 0;
		start = System.currentTimeMillis();
		//find1Aux(0,0,1001,1001,p1);
		findParallel(p1);
		System.out.println(p1[0] + ", "+p1[1]);
		end = System.currentTimeMillis();
    	tempo=end-start;
    	somaTempo+=tempo;
    	System.out.println("Tempo de execução : " + (tempo) + "ms");
	}
}
