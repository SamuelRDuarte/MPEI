import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TesteCountingFilter {
	private CountingFilter filter;

    public TesteCountingFilter(String file) throws IOException{
        filter = new CountingFilter(100);
        BufferedReader read = new BufferedReader(new FileReader(file));
        String ln = read.readLine();
        while(ln != null) {
            String[] s = ln.split(";");
            for(int i=0; i<Integer.valueOf(s[2]); i++) {
                filter.add(s[0]);
            }
            System.out.printf("titulo: %s; copias: %s\n", s[0],filter.count(s[0]));
            ln = read.readLine();
        }
        read.close();
        System.out.println();
        BufferedReader reads = new BufferedReader(new FileReader("testelivros.txt"));
        String lns = reads.readLine();
        while(lns != null) {
        	String[] s = lns.split(";");
        	System.out.printf("titulo: %s; pertence: %s\n", s[0],filter.contains(s[0]));
        	lns = reads.readLine();
        }
        reads.close();
    }
    
    public static void main(String[] args) throws IOException {
		new TesteCountingFilter("livros.txt");
	}
}