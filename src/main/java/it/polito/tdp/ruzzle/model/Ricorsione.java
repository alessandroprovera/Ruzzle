package it.polito.tdp.ruzzle.model;

import java.util.List;
import java.util.ArrayList;

public class Ricorsione {
	
	public List<Pos> trovaParola(String parola, Board board) {
		// cercare la prima lettera
		for(Pos p: board.getPositions()) {
			// la lettera alla posizione p e la prima lettera della parola?
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				// chiamare il metodo ricorsivo
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if(cerca(parola,board,parziale,1)){
					return parziale;
				}
						
			}
		}
		return null;
		
		
	}
	
	public boolean cerca(String parola, Board board, List<Pos> parziale, int livello) {
		
		//caso di terminazione
		if(livello == parola.length())
			return true;
		
		//altrimenti si va avanti a costruire la soluzione
		// prendiamo ultima lettera in parziale e cerchiamo le sue adiacenti
		Pos ultima = parziale.get(parziale.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		//andiamo a verificare, una per una, se le adiacenti sono uguali alla 
		//lettera che stiamo cercando
		for(Pos a: adiacenti) {
			if(!parziale.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {
				parziale.add(a);
				if(cerca(parola,board,parziale,livello+1)) {
					return true;
				}
				//backtracking
				parziale.remove(parziale.size()-1);
			}
				
		}
		return false;
		
	}

}
