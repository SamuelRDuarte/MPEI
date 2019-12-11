import java.util.*;

public class TesteContadorEstocastico {
	public static void main(String[] args) {
		ListLivros lista = new ListLivros();
		for(int i=0; i<10000; i++) {
			lista.addLivro(Gerador.livro(), (int)(1+Math.random()*5));
		}
		String teste = "Direito";
		List<Livro> l = lista.getLivros();
		int count = 0;
		for (int i = 0; i < l.size(); i++) {
			if(l.get(i).getCategoria().equals(teste))
				count++;
		}
		System.out.printf("valor esperado %d\n", count);
		System.out.printf("Existem aproximadamente " + ContadorEstocastico.contador(teste, l) + " livros desta categoria");
	}
}