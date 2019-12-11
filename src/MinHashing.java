import java.util.*;

public class MinHashing {
	
	private int nrHashFunc;
	private String titulo;
	
	public MinHashing(int nrHashFunc, String titulo) {
		this.nrHashFunc = nrHashFunc;
		this.titulo = titulo;
	}
	
	public int[] getMinHashValuesTitle() {
		return getMinHashValuesTitulo(titulo,nrHashFunc);
	}
	
	private int[] getMinHashValuesTitulo(String titulo,int numeroHash) { //so para um livro
		int[] minHash = new int[numeroHash];
		String title;
		int hash;
		
		for (int i = 0; i < numeroHash; i++) {
							
			title = Math.sqrt(i+1)+titulo+Math.sqrt(i+1);
				
			hash = shingle(title);
									
			minHash[i] = hash;
		}
		return minHash;
	}
	
	public static double jaccardIndex(Set<Integer> set1,Set<Integer> set2) {
		Set<Integer> intersecao = new HashSet<>(set1);
		intersecao.retainAll(set2);
		
		Set<Integer> uniao = new HashSet<>(set1);
		uniao.addAll(set2);
		
		if(uniao.isEmpty()) return 0;
		return (double) intersecao.size()/uniao.size();
	}
	
	public static double indiceSimilaridade(int[] ind1,int[] ind2) {
		int size =1;
		int w =0;
		
		if (ind1.length == ind2.length) {
			size = ind2.length;
			for (int i = 0; i < size; i++) {
				if(ind1[i]==ind2[i])
					w+=1;
			}
		}
		
		return (double)w/size;
	}
	
	public Set<Integer> shingles(String s){
    	Set<Integer> shin = new HashSet<>();
    	String[] array = s.split(" ");
    	for (int i = 0; i < array.length; i++) {
			shin.add(array[i].hashCode());
		}
    	return shin;
    }
	
	public int shingle(String s){
    	String[] array = s.split(" ");
    	int[] hashs = new int[array.length];
    	int tmp = 9999999;
    	
    	for (int i = 0; i < array.length; i++) {
			hashs[i] = array[i].hashCode();
		}
    	
    	for (int i = 0; i < hashs.length; i++) {
			if(hashs[i]<tmp)
				tmp = hashs[i];
		}
    	return tmp;
    }
	
	

}
