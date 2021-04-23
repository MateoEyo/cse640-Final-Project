package cse640.hw4;

public class Album {

	String name;
	String artist;
	String genre;
	String instrument;
	String media;
	
	public Album(String name) {
		this.name = name;
	}
	
	public void setArtist(String artistName) {
		artist = artistName;
	}
	
	public void setGenre(String albumGenre) {
		genre = albumGenre;
	}
	
	public void setInstrument(String albumInstrument) {
		instrument = albumInstrument;
	}
	
	public void setMedia(String albumMedia) {
		media = albumMedia;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getInstrument() {
		return instrument;
	}
	
	public String getMedia() {
		return media;
	}
}
