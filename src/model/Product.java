package model;

public abstract class Product {

	Integer id;
	String  name;
	String  desc;
	Float   price;
	Integer tempoImpresssao;

	public Product(int id, String name, String desc, Float price, Integer tempoImpresssao) {
		this.setId(id);
		this.setName(name);
		this.setDesc(desc);
		this.setPrice(price);
		this.setTempoImpresssao(tempoImpresssao);
	}
	
	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Integer getTempoImpresssao() {
		return tempoImpresssao;
	}

	public void setTempoImpresssao(Integer tempoImpresssao) {
		this.tempoImpresssao = tempoImpresssao;
	}

}
