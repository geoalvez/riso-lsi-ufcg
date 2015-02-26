package riso.builder.conceptNet5.URI.out;

import java.util.HashSet;
import java.util.Set;


public class Aresta {

	private int numFound;
	private Set<ArestaConceptNet> edges = new HashSet<ArestaConceptNet>();
	private double maxScore;

	public void addEdge(ArestaConceptNet aresta){

		getEdges().add(aresta);
	}

	public void addSetEdge(Set<ArestaConceptNet> edges){

		getEdges().addAll(edges);
	}

	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public int getNumFound() {
		return numFound;
	}
	
	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}
	public Set<ArestaConceptNet> getEdges() {
		return edges;
	}
	public void setEdges(Set<ArestaConceptNet> edges) {
		this.edges = edges;
	}
	@Override
	public String toString() {
		return "Aresta [numFound=" + numFound + ", edges=" + edges + "]";
	}

}
