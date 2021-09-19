package model;

public class Product implements Component {

	public String name;
	public double value;
	
	public Product(String name, double value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String Info() {
		return "Назва продукту: " + name + ", " + "ціна продукту: " + value;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
