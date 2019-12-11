
public class Livro {
	
	private String titulo;
	private String categoria;
	private static int currentID = 100;
	private final int id;
	
	public Livro(String titulo, String categoria) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.id = currentID++;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "#"+ id + " '"+titulo+"',categoria: "+categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + id;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (id != other.id)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	
}
