package riso.builder.conceptNet5.URI.out;

public class Topico {
	
	private String uri;
	private String conceito;
	
	public Topico(String uri, String conceito){
		setUri(uri);
		setConceito(conceito);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((conceito == null) ? 0 : conceito.hashCode());
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
		Topico other = (Topico) obj;
		if (conceito == null) {
			if (other.conceito != null)
				return false;
		} else if (!conceito.equals(other.conceito))
			return false;
		return true;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getConceito() {
		return conceito;
	}
	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	
	
	

}
