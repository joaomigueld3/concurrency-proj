package sudokuSolve;
import java.util.ArrayList;

	public class SudokuSolver {
		public int[][] tabuleiro = new int[9][9];
		ArrayList<Integer> linhaLista = new ArrayList<>();
		ArrayList<Integer> colunaLista = new ArrayList<>();
		ArrayList<Integer> gridLista = new ArrayList<>();
		
		public SudokuSolver() {
			this.tabuleiro[0][0] = 5; this.tabuleiro[0][1] = 3; this.tabuleiro[0][4] = 7;
			this.tabuleiro[1][0] = 6; this.tabuleiro[1][3] = 1; this.tabuleiro[1][4] = 9; this.tabuleiro[1][5] = 5;
			this.tabuleiro[2][1] = 9;this.tabuleiro[2][2] = 8;this.tabuleiro[2][7] = 6;
			this.tabuleiro[3][0] = 8;this.tabuleiro[3][5] = 6;this.tabuleiro[3][8] = 3;
			this.tabuleiro[4][0] = 4;this.tabuleiro[4][3] = 8;this.tabuleiro[4][5] = 3;this.tabuleiro[4][8] = 1;
			this.tabuleiro[5][0] = 7;this.tabuleiro[5][5] = 2;this.tabuleiro[5][8] = 6;
			this.tabuleiro[6][1] = 6;this.tabuleiro[6][6] = 2;this.tabuleiro[6][7] = 8;
			this.tabuleiro[7][3] = 4;this.tabuleiro[7][4] = 1;this.tabuleiro[7][5] = 9;this.tabuleiro[7][8] = 5;
			this.tabuleiro[8][5] = 8;this.tabuleiro[8][7] = 7;this.tabuleiro[8][8] = 9;
			}
		//pegar valores já existentes
		public ArrayList<Integer> getLinhaLista(int linha, int[][] tabuleiro){
			ArrayList<Integer> listRes = new ArrayList<>();			
				for(int j=0;j<9;j++) {
					if(tabuleiro[linha][j]!=0) {
						listRes.add(tabuleiro[linha][j]);
					}
				}
				return listRes;			
		}
		public ArrayList<Integer> getColunaLista(int coluna, int[][] tabuleiro){
			ArrayList<Integer> listRes = new ArrayList<>();			
				for(int i=0;i<9;i++) {
					if(tabuleiro[i][coluna]!=0) {
						listRes.add(tabuleiro[i][coluna]);
					}
				}
				return listRes;			
		}
		
		public ArrayList<Integer> getGridLista(int linha, int coluna, int[][] tabuleiro){
			ArrayList<Integer> listRes = new ArrayList<>();			
				for(int i=((linha/3)*3);i<((linha/3)*3 + 3);i++) {
					for (int j=((coluna/3)*3);j<((coluna/3)*3 + 3);j++) {
						if(tabuleiro[i][j]!=0) {
							listRes.add(tabuleiro[i][j]);
						}	
					}					
				}
				return listRes;			
		}
		public boolean verificaLinha() {
			return true;
		}
		
		public ArrayList<Integer> getValoresPossiveis(int linha, int coluna, int[][] tabuleiro){
			ArrayList<Integer> listRes = new ArrayList<>();			
			for(int candidato = 1; candidato < 10; candidato++) {
				boolean listaContains=true;
				Thread t1 = new Thread( () -> {
		           //  listaContains = getLinhaLista(linha, tabuleiro).contains(candidato);
		        });
				if(!listaContains && 
						!getColunaLista(coluna, tabuleiro).contains(candidato) && 
						!getGridLista(linha, coluna, tabuleiro).contains(candidato)) {
					listRes.add(candidato);
				}
			}
			
				return listRes;			
		}
		
		
		
		
		public  String toString() { // retona String do sudoku (imprime matriz)
			// getTotalTime();
			// initializaSudoku(difficulty.NORMAL);
			String s = "";
			for (int i = 0; i < 9; i++) {
				if (i % 3 == 0 && i != 0)
					s = s + '\n';
				for (int j = 0; j < 9; j++) {
					if (j % 3 == 0)
						s += "  ";
					if (tabuleiro[i][j] == 0) { // se o conteudo de gridplayer na posição i,j for nulo printa espaço 0
						s = s + ".";
					} else {
						s = s + "" + tabuleiro[i][j]; // se não printa o conteudo de gridplayer
					}
				}
				s = s + '\n';
			}
			return s;
		}
    		 
    		
	
}
