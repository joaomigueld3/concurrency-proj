package sudokuSolve;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		SudokuSolver s1 = new SudokuSolver();
		System.out.println(s1.toString());
		System.out.println(s1.getLinhaLista(0, s1.tabuleiro));
		System.out.println(s1.getColunaLista(0, s1.tabuleiro));
		System.out.println(s1.getGridLista(0, 0, s1.tabuleiro));
		System.out.println("Iniciando..");
		long start,end,tempo,somaTempo =0;
		int qtdLoop =1;
        for(int i=0 ;i<qtdLoop;i++) {
        	start = System.currentTimeMillis();
        	s1.getValoresPossiveis(0, 0, s1.tabuleiro);
        	end = System.currentTimeMillis();
        	tempo=end-start;
        	somaTempo+=tempo;
        	//System.out.println("Tempo de execução : " + (tempo) + "ns");
        }
        System.out.println("tempo medio: "+ (somaTempo/qtdLoop)+" ms");		
        //System.out.println("getAllValoresPossiveis: "+ s1.getAllValParallel());
	}

}
