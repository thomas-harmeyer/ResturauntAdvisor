package application;


public class Restaraunt {
	private String name;
	private String price;
	private int rating;
	private String cusine;
	private String type;
	final private String SPACE = "           ";
	public Restaraunt(){
	}
	public Restaraunt(String name, String price, int rating, String cusine, String type) {
		super();
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.cusine = cusine;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getCusine() {
		return cusine;
	}
	public void setCusine(String cusine) {
		this.cusine = cusine;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSpacing(){
		return SPACE;
	}
	@Override
	public String toString() {
		return "Restaraunt [name=" + name + ", price=" + price + ", rating=" + rating + ", cusine=" + cusine + ", type="
				+ type + ", SPACE=" + SPACE + "]";
	}
	public String[] toStringArray(){
		String[] str = {name,price,Integer.toString(rating),cusine,type};
		return str;
	}
	
	

}
