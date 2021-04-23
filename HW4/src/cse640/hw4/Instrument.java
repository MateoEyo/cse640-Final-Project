package cse640.hw4;

public class Instrument {

	String name;
	String category;
	
	public Instrument(String name) {
		this.name = name;
	}
	
	public void setCategory(String _category) {
		category = _category;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
}
