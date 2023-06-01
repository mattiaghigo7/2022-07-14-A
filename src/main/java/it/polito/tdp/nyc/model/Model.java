package it.polito.tdp.nyc.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	
	private Graph<NTA, DefaultWeightedEdge> grafo;
	private List<String> boroughs;
	private List<NTA> NTAs;
	private double pesoMedio;
	
	public Model() {
		this.dao = new NYCDao();
		this.boroughs = dao.getAllBoroughs();
	}
	
	public void creaGrafo(String ntaCode) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.NTAs = dao.getVertici(ntaCode);
		Graphs.addAllVertices(grafo, NTAs);
//		System.out.println("Ci sono "+this.grafo.vertexSet().size()+" vertici.");
		
		for(NTA n1 : NTAs) {
			for(NTA n2 : NTAs) {
				if(!n1.equals(n2)) {
					Set<String> unione = new HashSet<>(n1.getSSIDs());
					unione.addAll(n2.getSSIDs());
					Graphs.addEdge(grafo, n1, n2, unione.size());
				}
			}
		}
//		System.out.println("Ci sono "+this.grafo.edgeSet().size()+" archi.");
	}
	
	public List<String> getBorough(){
		return this.boroughs;
	}

	public int getVertici() {
		return grafo.vertexSet().size();
	}
	
	public int getArchi() {
		return grafo.edgeSet().size();
	}
	
	public List<Arco> analisiArchi() {
		this.pesoMedio = 0.0;
		for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
			this.pesoMedio+=this.grafo.getEdgeWeight(d);
		}
		this.pesoMedio=this.pesoMedio/grafo.edgeSet().size();
		List<Arco> archi = new ArrayList<>();
		for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
			if(grafo.getEdgeWeight(d)>this.pesoMedio) {
				archi.add(new Arco(grafo.getEdgeSource(d).getNTACode(),grafo.getEdgeTarget(d).getNTACode(),grafo.getEdgeWeight(d)));
			}
		}
		archi.sort(null);
		return archi;
	}

	public double getPesoMedio() {
		return pesoMedio;
	}
	
	
}
