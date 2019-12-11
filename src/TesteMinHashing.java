import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TesteMinHashing {
	private MinHashing titulo;
	private MinHashing listaTitulo;
	private List<String> lista = new ArrayList<>();
	private ArrayList<String> recomenMinHash = new ArrayList<>();
	private ArrayList<String> recomJaccard = new ArrayList<>();

    public TesteMinHashing(String file) throws IOException{
        BufferedReader read = new BufferedReader(new FileReader(file));
        String ln = read.readLine();
        String[] s = ln.split(";");
        String title = s[0];
        titulo = new MinHashing(400,title);
        
        while(ln != null) {
        	String[] a = ln.split(";"); 
        	lista.add(a[0]);
        	ln = read.readLine();
        }
        read.close();
        
        int[] assinaturaTitulo = titulo.getMinHashValuesTitle();
        
        System.out.println("JaccardIndex\tMinHashing");
        
        for (int i = 0; i < lista.size(); i++) {
			listaTitulo = new MinHashing(550,lista.get(i));
			int[] assinaturaTitleList = listaTitulo.getMinHashValuesTitle();
			double jaccardIndex = MinHashing.jaccardIndex(shingles(title), shingles(lista.get(i)));
			
			System.out.print(jaccardIndex+"\t\t");
			
			double similaridade = MinHashing.indiceSimilaridade(assinaturaTitulo, assinaturaTitleList);
			System.out.println(similaridade);
			if(similaridade >0.3)
				recomenMinHash.add(lista.get(i));
			if(jaccardIndex >0.3)
				recomJaccard.add(lista.get(i));
		}
        
        System.out.println("--------Lista para MinHashing--------");
        System.out.println(recomenMinHash.toString());
        System.out.println("--------Lista para JaccardIndex--------");
        System.out.println(recomJaccard.toString());
    }
    
    public Set<Integer> shingles(String s){
    	Set<Integer> shin = new HashSet<>();
    	String[] array = s.split(" ");
    	for (int i = 0; i < array.length; i++) {
			shin.add(array[i].hashCode());
		}
    	return shin;
    }
    
    public static void main(String[] args) throws IOException {
		new TesteMinHashing("livros.txt");
	}

}
