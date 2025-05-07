package model;

public class Print3D extends Product {

	Float peso;

	public Print3D(Integer id, String name, String desc, Float price, Integer tempoImpressao, Float peso) {
		super(id, name, desc, price, tempoImpressao);
		this.setPeso(peso);
	}
	
	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	public Float getPrice() {
		return peso * price;
	}
}
