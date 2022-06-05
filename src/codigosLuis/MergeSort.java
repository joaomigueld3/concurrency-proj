package codigosLuis;

import java.util.*;

// Exemplo de Merge Sort

class MergeSort {

    static boolean compare(int a, int b) {
        return a<=b;
    }

    static void merge(int a[], int b[], int dest[]) {
        int posA = 0;
        int posB = 0;
        int posDest = 0;
        while (posA<a.length && posB<b.length) {
            if (compare(a[posA],b[posB])) 
                dest[posDest++] = a[posA++];
            else
                dest[posDest++] = b[posB++];
        }
        while (posA<a.length) dest[posDest++] = a[posA++];
        while (posB<b.length) dest[posDest++] = b[posB++];
           
    }
    
    //Versão sequencial
    public static void mergesort(int arr[]) {
        //System.out.println("Ordenando:");
        //for (int c=0;c<arr.length;c++) { System.out.print(arr[c]+" ");}
        //System.out.println();

        if (arr.length<2) return;
        if (arr.length==2) {
            if (arr[0]<=arr[1]) {
            	return;
            }
            int aux = arr[0];
            arr[0] = arr[1];
            arr[1] = aux;
            return;
        }

        int meio = arr.length/2;
        int p1[] = Arrays.copyOfRange(arr, 0, meio);	//(array origem, indice inicial, indice final)
        int p2[] = Arrays.copyOfRange(arr, meio, arr.length);
        mergesort(p1);
        mergesort(p2);
        merge(p1,p2,arr);

    }
    
    //Versão Paralela simples (paraleliza apenas o primeiro nível de recurssão)
    public static void pMergeSort1(int arr[]) {
        int meio = arr.length/2;
        int p1[] = Arrays.copyOfRange(arr, 0, meio);
        int p2[] = Arrays.copyOfRange(arr, meio, arr.length);
        Thread t1 = new Thread( () -> {
            mergesort(p1);            
        });
        Thread t2 = new Thread( () -> {
            mergesort(p2);            
        });
        t1.start();t2.start();
        try { t1.join(); t2.join(); } catch (Exception ex) { System.err.println(ex); } 
        merge(p1,p2,arr);
    }
    
    //Versão Paralela Avançada (paraleliza vários níveis de recurssão)
    public static void pMergeSort2(int arr[], int nivel) {	//vai dividindo o array, de acordo com o nivel=qtd threads
        if (nivel==0) {
           mergesort(arr);
           return;
        }
        int meio = arr.length/2;
        int p1[] = Arrays.copyOfRange(arr, 0, meio);
        int p2[] = Arrays.copyOfRange(arr, meio, arr.length);
        Thread t1 = new Thread( () -> {
            pMergeSort2(p1,nivel-1);
            System.out.println("ola thread 1");
        });
        Thread t2 = new Thread( () -> {
            pMergeSort2(p2,nivel -1 );
            System.out.println("ola thread 2");
        });
        t1.start();t2.start();
        try { t1.join(); t2.join(); } catch (Exception ex) { System.err.println(ex); } 
        merge(p1,p2,arr);
    }
    
   
    public static void main(String args[]) {
        Random r = new Random(123456789L);
        int arr[] = new int[1000000];
        for (int c=0;c<arr.length;c++) {
            arr[c] = r.nextInt(100000);
        }
        //for (int c=0;c<arr.length;c++) { System.out.print(arr[c]+" ");}
        System.out.println();
        System.out.println("Iniciando ordenacao");
        
        
        //Testem os diferentes algoritmos
        //mergesort(arr);
        //pMergeSort1(arr);
        long start,end,tempo,somaTempo =0;
        for(int i=0 ;i<1;i++) {
        	start = System.currentTimeMillis();
        	pMergeSort2(arr,3);
        	end = System.currentTimeMillis();
        	tempo=end-start;
        	somaTempo+=tempo;
        	System.out.println("Tempo de execu��o : " + (tempo) + "ms");
        }
        System.out.println("tempo medio: "+ (somaTempo/200)+" ms");
        
        System.out.println("Testando ordenacao");
       // for (int c=0;c<arr.length;c++) { System.out.print(arr[c]+" ");}
        System.out.println();
        for (int c=1;c<arr.length;c++) {
            if (!compare(arr[c-1],arr[c])) 
               System.out.println("erro na posicao "+c);
        }
        System.out.println("Fim do teste");
    }

}
