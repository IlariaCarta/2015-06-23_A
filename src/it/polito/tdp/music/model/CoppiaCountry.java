package it.polito.tdp.music.model;

public class CoppiaCountry {
	
	private int co1;
	private int co2;
	private int peso;
	
	
	public CoppiaCountry(int co1, int co2, int peso) {
		super();
		this.co1 = co1;
		this.co2 = co2;
		this.peso = peso;
	}


	public int getCo1() {
		return co1;
	}


	public void setCo1(int co1) {
		this.co1 = co1;
	}


	public int getCo2() {
		return co2;
	}


	public void setCo2(int co2) {
		this.co2 = co2;
	}


	public int getPeso() {
		return peso;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
