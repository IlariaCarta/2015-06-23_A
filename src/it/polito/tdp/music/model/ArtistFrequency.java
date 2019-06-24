package it.polito.tdp.music.model;

public class ArtistFrequency {
	private int artistId;
	private String artist;
	private int ascolti;
	
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getAscolti() {
		return ascolti;
	}
	public void setAscolti(int ascolti) {
		this.ascolti = ascolti;
	}
	public ArtistFrequency(int artistId, String artist, int ascolti) {
		super();
		this.artistId = artistId;
		this.artist = artist;
		this.ascolti = ascolti;
	}
	
	
	
	
}
