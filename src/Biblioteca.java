import java.util.*;

public class Biblioteca {
	private ListLivros lista = new ListLivros();
	private CountingFilter filter;
	
	public Biblioteca(int numLiv) {
		filter = new CountingFilter(numLiv);
		
		for (int i = 0; i < numLiv; i++) { //gerar livros;
			int quant = (int)Math.floor(1+Math.random()*(4));
			addLivro(Gerador.livro(),quant);
		}
	}
	
	
	public ListLivros getLista() {
		return lista;
	}

	public CountingFilter getFilter() {
		return filter;
	}


	public boolean addLivro(Livro liv, int quant) {
		if (lista.addLivro(liv, quant)) {
			for (int i = 0; i < quant; i++) {
				filter.add(liv.getTitulo());
			}
			return true;
		}
		return false;
	}
	
	public boolean removeLivro(Livro liv) {
		if (lista.removeLivro(liv)) {
			filter.remove(liv.getTitulo());
			return true;
		}
		return false;
	}
	
	public int findLivro(String l) {
		if(filter.contains(l)) {
			for (int i = 0; i < lista.size(); i++) {
				if(lista.getLivro(i).getTitulo().equals(l))
					return i+1;
			}
		}
		return -1;
	}
	
	public ArrayList<String> sugerirLivro(String s){
		ArrayList<String> recomendados = new ArrayList<>();
		List<String> titulos = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			titulos.add(lista.getLivros().get(i).getTitulo()); //lista dos titulos
		}
		
		MinHashing mhs = new MinHashing(3,s); 
		int[] assinatura = mhs.getMinHashValuesTitle();  //assinatura do titulo dado
		
		
		for (int i = 0; i < titulos.size(); i++) {   //ve na lista dos titulos quais sao aparecidos
			
			MinHashing mh = new MinHashing(3,titulos.get(i));	
			int[] assinaturaS = mh.getMinHashValuesTitle();	//assinatura do titulo da lista na posiçao i
			
			double similaridade = MinHashing.indiceSimilaridade(assinatura, assinaturaS);
			
			if(similaridade>0.4)
				recomendados.add(titulos.get(i));
		}
		
		return recomendados;
	}
	
	public String printLivros() {
		return lista.toString();
	}


	@Override
	public String toString() {
		return "Biblioteca\nLista de Livros:\n"+lista.toString();
	}
	
	

}
