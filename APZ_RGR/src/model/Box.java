package model;

import java.util.ArrayList;
import java.util.List;

public class Box implements Component {

	public String name;
	private List<Component> components = new ArrayList<>();

	public Box(String name) {
		this.name = name;
	}

	public void Add(Component component) {
		components.add(component);
	}

	public void Remove(Component component) {
		components.remove(component);
	}

	@Override
	public String Info() {
		String s;
		s = "Назва ящика: " + name + "\n";
		s += "Інформація про компоненти всередині ящика:\n[";
		if (components.size() != 0) {
			for (int i = 0; i < components.size(); i++) {
				if(i != components.size() - 1) {
					s += components.get(i).Info(); 
					s += "\n";
				} else {
					s += components.get(i).Info();
				}
			}
		} else {
			s += "Ящик порожній";
		}
		s += "]";
		return s;
	};
	
	public double getBoxValue() {
		double boxValue = 0;
		if (components.size() != 0) {
			for (int i = 0; i < components.size(); i++) {
				if(components.get(i) instanceof Box) {
					boxValue += ((Box) components.get(i)).getBoxValue();
				} else {
					boxValue += ((Product) components.get(i)).value;
				}
			}
		}
		return boxValue;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
