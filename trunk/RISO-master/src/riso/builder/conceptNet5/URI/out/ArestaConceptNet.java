package riso.builder.conceptNet5.URI.out;

import java.util.List;

public class ArestaConceptNet {

	private String endLemmas;
	private String startLemmas;
	
	public String getEndLemmas() {
		return endLemmas;
	}

	public void setEndLemma(String endLemmas) {
		this.endLemmas = endLemmas;
	}

	public String getStartLemmas() {
		return startLemmas;
	}

	public void setStartLemma(String startLemmas) {
		this.startLemmas = startLemmas;
	}

	/**
	 * Identificador unico para uma aresta a qual contem um hash SHA-1 da informacao que o torna unico
	 */
	private String id; 

	/**
	 * URI da afirmacao sendo expressada. 
	 * A URI nao necessariamente eh unica, porque muitas arestas podem agrupar-se juntas
	 */
	private String uri; 

	/**
	 * URI da relacao desta afirmacao
	 */
	private String rel; 

	/**
	 * URI do primeiro argumento da afirmacao
	 */
	private String start;

	/**
	 * URI do segundo argumento da afirmacao
	 */
	private String end;

	/**
	 * A forca com que esta aresta expessa esta afirmacao, 
	 * tipicamente 1, mas pode ser maior, menor ou ate mesmo negativo
	 */
	private double weight;

	/**
	 * Fontes que quando combinadas dizem que a afirmacao deveria ser verdade ou falsa
	 */
	private List<String> sources;

	/**
	 * URI prepresentando a licenca Creative Commons que governa estes dados
	 */
	private String license;

	/**
	 * URI representando a base de dados ou a carga de dados de uma particular fonte que criou esta aresta
	 */
	private String dataset;

	/**
	 * URI do contexto no qual esta afirmacao eh verdadeira
	 */
	private String context;
	/**
	 * Lista com tres identificadores de caracteristicas. Pode ser utilizado pra aprendizagem de maquina
	 */
	private List<String> features;

	/**
	 * Texto original em linguagem natural que expressa essa afirmacao. 
	 * Pode ser null porque nem toda afirmacao foi derivada de entradas de linguagem natural
	 */
	private String surfaceText;

	private double score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}


	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public String getSurfaceText() {
		return surfaceText;
	}

	public void setSurfaceText(String surfaceText) {
		this.surfaceText = surfaceText;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	private String timestamp;

	@Override
	public String toString() {
		return "ArestaConceptNet [id=" + id + ", uri=" + uri + ", rel=" + rel
				+ ", start=" + start + ", end=" + end + ", weight=" + weight
				+ ", sources=" + sources + ", license=" + license
				+ ", dataset=" + dataset + ", context=" + context
				+ ", features=" + features + ", surfaceText=" + surfaceText
				+ ", score=" + score + ", timestamp=" + timestamp + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		ArestaConceptNet other = (ArestaConceptNet) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
