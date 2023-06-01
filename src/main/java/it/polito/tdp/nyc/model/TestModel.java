package it.polito.tdp.nyc.model;

public class TestModel {

	public static void main(String[] args) {

		Model m = new Model();
		m.creaGrafo("BK");
		m.analisiArchi();
		System.out.println(m.getPesoMedio());
	}

}
