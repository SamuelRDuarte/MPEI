import java.util.*;

public class CountingFilter {
	
    private int[] array;
    private int kHashes;						//num hashes
    private int nElem = 0;						//elemntos inseridos
    private double falsePos = 0.001;
    private int size;

    public CountingFilter(int n){
    	size = OptimizedFilterSize(n);
    	array = new int[size];
        this.kHashes = kHashes();
    }
    
    public int getNumElements(){ return nElem;}
    
    
    public void add(String l){
    	l=l.toLowerCase();
    	for(int i = 0;i<kHashes;i++) {
    		l = l+Math.pow(i, i+1);
    		array[Math.abs(l.hashCode()%size)]+=1;  //adiciona 1 à posiçao gerada atraves do resto do hashcode do titulo e o tamanho do filter
    			
    	}
    	nElem++;
    }
    
    public boolean contains(String l){
    	if(array[Math.abs(l.hashCode()%size)]<0)
    		throw new NoSuchElementException("O elememto nao existe");
    	
    	l=l.toLowerCase();
    	int x = 0;
    	
		for(int i=0; i<kHashes; i++){
			l = l+Math.pow(i, i+1);
			if((array[Math.abs(l.hashCode()%size)]) != 0)
				x++;
		}
		if(x==kHashes)
			return true;
		return false;
    }
    
    public void remove(String l){
		l = l.toLowerCase();
				
		for(int i=0; i<kHashes; i++){
			l = l + Math.pow(i,i+1);
			if(array[Math.abs(l.hashCode()%size)]>0)
				array[Math.abs(l.hashCode()%size)]-=1; // subtrai 1 à posiçao gerada atraves do resto do hashcode do titulo e o tamanho do filter 
		}
		nElem--;
	}
    
    public int count(String l) {
    	l = l.toLowerCase();
    	
    	if(!contains(l))
    		return -1;
    	
    	int tmp = 999999999;
    	
    	for(int i=0; i<kHashes; i++){
			l = l+Math.pow(i, i+1);
			if((array[Math.abs(l.hashCode()%size)]) < tmp)
				tmp = array[Math.abs(l.hashCode()%size)];
		}
    	return tmp;
    }
    
    @Override
	public String toString() { return "CountingBloomFilter\n"
			+ "Size  =  " + array.length + ";\n"
			+ "Number of Elements  =  " + nElem + ";\n"
			+ "Number of Hash Functions  =  " + kHashes + ".\n";
	}
    
	public int OptimizedHashFunctionsAmount() {
		return (int)Math.round(Math.abs(Math.log(falsePos)/Math.log(2)));
	}
	public int kHashes(){
		return (int)Math.round(Math.abs(Math.log(falsePos)/Math.log(2)));
	}
	public int OptimizedFilterSize (int elemAmount){
		return (int)Math.round(elemAmount*OptimizedHashFunctionsAmount()/Math.log(2));
	}

}
