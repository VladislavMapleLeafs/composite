package test;

import model.Box;
import model.Component;
import model.Product;

public class test {

	public static void main(String[] args) {

		Box rootBox = new Box("RootBox");
		Box box1 = new Box("Box1");
		Box box2 = new Box("Box2");
		Box box11 = new Box("Box11");
		Box box12 = new Box("Box12");
		Product product1 = new Product("Product1", 15.5);
		Product product2 = new Product("Product2", 6);
		Product product3 = new Product("Product3", 9);
		Product product4 = new Product("Product4", 18.7);
		
		rootBox.Add(box1);
		rootBox.Add(box2);
		box1.Add(box11);
		box1.Add(box12);
		rootBox.Add(product1);
		rootBox.Add(product1);
		box1.Add(product2);
		box11.Add(product3);
		box2.Add(product4);
		
		rootBox.Info();
		System.out.println(rootBox.getBoxValue());
	}

}
