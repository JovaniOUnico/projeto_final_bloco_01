package model;

public class PrintText extends Product {
	Float area;

	public PrintText(int id, String name, String desc, Float price, Integer tempoImpressao, Float area) {
		super(id, name, desc, price, tempoImpressao);
		this.setArea(area);
	}
	
	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}
	
	public Float getPrice() {
		return area * price;
	}
}
