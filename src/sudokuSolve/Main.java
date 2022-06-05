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
		ArrayList<Integer> getValores = new ArrayList<>();
		getValores = s1.getValoresPossiveis(0, 0, s1.tabuleiro);
        for(int i=0 ;i<200;i++) {
        	start = System.nanoTime();
        	s1.getValoresPossiveis(0, 0, s1.tabuleiro);
        	end = System.nanoTime();
        	tempo=end-start;
        	somaTempo+=tempo;
        	System.out.println("Tempo de execução : " + (tempo) + "ns");
        }
        System.out.println("tempo medio: "+ (somaTempo/200)+" ns");
		
        System.out.println();

	}

}
