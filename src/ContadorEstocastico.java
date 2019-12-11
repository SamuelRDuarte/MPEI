import java.util.List;

public class ContadorEstocastico {
	public static int contador(String s, List<Livro> lista) {
		int count = 0;
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getCategoria().equals(s))
				if(Math.random()<0.5) 
					count++;
		}
		return count*2;
	}
}