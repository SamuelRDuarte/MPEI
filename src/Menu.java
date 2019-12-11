import java.util.*;
import static java.lang.System.*;
import java.io.IOException;

public class Menu {
	static Scanner sc = new Scanner(in);
	static Biblioteca biblio;
	public static void main(String[] args) throws IOException {
		int op,op2 = 0;
		
		do{
			
			out.println("--------------MENU---------------");
			out.println("1 - Teste do CountingFilter");
			out.println("2 - Teste do MinHash");
			out.println("3 - Teste do Contador Estocastico");
			out.println("4 - Biblioteca");
			out.println("0 - Terminar Programa");
			out.print("Opcao:");
			op = sc.nextInt();
			
			switch (op) {
			case 1:
				out.println();
				TesteCountingFilter.main(null);
				out.println();
				break;
			case 2:
				out.println();
				TesteMinHashing.main(null);
				out.println();
				break;
			case 3:
				out.println();
				TesteContadorEstocastico.main(null);
				out.println();
				break;
			case 4:
				out.println();
				out.print("Diga o número de livros que a biblioteca tem: ");
				int num = sc.nextInt();
				biblio = new Biblioteca(num);
				
				do {
					out.println();
					out.println("-------------Biblioteca------------");
					out.println("1 - Ver lista dos livros");
					out.println("2 - CountingFilter");
					out.println("3 - Surgerir Livro");
					out.println("4 - Numero de livros numa categoria");
					out.println("0 - Voltar");
					out.print("Opcao:");
					op2 = sc.nextInt();
					
					switch (op2) {
					case 1:
						out.println();
						out.print(biblio.getLista().toString());
						break;
					case 2:
						out.println();
						filter();
						break;
					case 3:
						out.println();
						recomendar();
						break;
					case 4:
						out.println();
						numCategoria();
						break;
					}
				}while(op2!=0||op>4);
				break;				
			}
		}while(op!=0 ||  op>4);
		
		out.println("Programa terminado com sucesso");
	}
	
	public static void filter() {
		ListLivros lista = biblio.getLista();
		
		for (int i = 0; i < lista.size(); i++) {
			out.printf("titulo: %s; copias: %s\n", lista.getLivro(i).getTitulo(),biblio.getFilter().count(lista.getLivro(i).getTitulo()));
		}
	}
	
	public static void recomendar() {
		List<Livro> recomendados = new ArrayList<>();
		out.print("Escreva o titulo para sugerir: ");
		sc.nextLine();
		String title = sc.nextLine();
		out.println(title);
		MinHashing titulo = new MinHashing(500,title);
		int[] assinaturaTitle = titulo.getMinHashValuesTitle();  //assinatura do titulo dado
		
		ListLivros list = biblio.getLista(); //lista dos livros
		for (int i = 0; i < list.size(); i++) {
			String listTitle = list.getLivro(i).getTitulo();
			MinHashing listTi = new MinHashing(500,listTitle);
			int[] assinatura = listTi.getMinHashValuesTitle();
			double sim = MinHashing.indiceSimilaridade(assinaturaTitle, assinatura); //indicie de similaridade
			if(sim > 0.2)
				recomendados.add(list.getLivro(i));
		}
		System.out.println(recomendados.size());
		
		for (int i = 0; i < recomendados.size(); i++) {
			out.println(recomendados.get(i).toString());
		}
	}
	
	public static void numCategoria() {
		ListLivros lista = biblio.getLista();
		out.print("Categoria: ");
		sc.nextLine();
		String cat = sc.nextLine();
		
		out.printf("Existem aproximadamente " + ContadorEstocastico.contador(cat, lista.getLivros()) + " livros desta categoria\n");
	}

}
