package it.polito.tdp.music.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.music.MusicController;
import it.polito.tdp.music.db.MusicDAO;

public class Model {
	

	public List<Month> mesi;
	public MusicDAO dao;
	private List<ArtistFrequency> topArtists;
	private Month meseSelezionato;
	private List<Country> countryTop;
	private List <CoppiaCountry> coppie;
	
	private Graph grafo;
	
	
	
	public Model() {
		dao = new MusicDAO();
		
		countryTop = new ArrayList<>();
		topArtists = new ArrayList<ArtistFrequency>();
		countryTop = new ArrayList<Country>();
	}
	
	public List<Month> getMonths(){
		this.mesi = new ArrayList<Month>();
		
		for(Integer m : dao.getMesi()) {
			this.mesi.add(Month.of(m));
		}
		return mesi;
	}
	
	
	
	public List<ArtistFrequency> getTopArtist (Month m){
		this.meseSelezionato=m;
		topArtists = dao.getArtistsbyMonth(m);
		return topArtists;
		
	}
	
	
	// creo il metodo che per ogni artista presente in topArtists mi aggiunge le nazioni
	// in cui è stato ascoltato in quel mese
	
	public List<Country> getCountryTop(Month m){
		for(ArtistFrequency a : topArtists) {
			List<Country> parziale = (dao.getCountryFromArtist(m, a.getArtistId()));
			for(Country c : parziale) { // NON DEVO CREARE DUPLICATI!!
				if(!countryTop.contains(c))
					countryTop.add(c);
			}
		}
		return countryTop;
	}
	
	
	public void creaGrafo(Month m) {
		
		this.grafo= new SimpleWeightedGraph<Country, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, getCountryTop(m));
		
		/*List<CoppiaCountry> parziale = dao.getCountryPairs(m);
		
		for(CoppiaCountry cc: parziale) {
			if(countryTop.contains(cc.getCo1()) && countryTop.contains(cc.getCo2())) {
				this.coppie.add(cc);
				if(cc.getPeso()!=0)
					Graphs.addEdge(this.grafo, cc.getCo1(), cc.getCo2(), cc.getPeso());
			}
			
		}*/
		
		List<CoppiaCountry> freq = dao.getCountryPairs(m);

		for (Country c1 : countryTop) {
			for (Country c2 : countryTop) {
				if (c1.getId() < c2.getId()) {

					int peso = calcolaPeso(c1, c2, freq);

					if (peso != 0) {
						Graphs.addEdge(grafo, c1, c2, peso);
					}
				}
			}
			
		}
		
		System.out.format("Grafo creato: %d vertici, %d archi\n",grafo.vertexSet().size(), grafo.edgeSet().size());
	
	}
	
		private int calcolaPeso(Country c1, Country c2, List<CoppiaCountry> freq) {
			
			for( CoppiaCountry cpf : freq ) {
				if( c1.getId()==cpf.getCo1() &&
						c2.getId()==cpf.getCo2())
					return cpf.getPeso() ;
			}
			
			return 0 ;
		}

}
