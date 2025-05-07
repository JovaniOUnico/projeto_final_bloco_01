package model;

public class CartItem {
	Product prod;
	Integer qtd;
	
	public CartItem(Product prod, Integer qtd) {
		this.setProd(prod);
		this.setQtd(qtd);
	}
	
	public Product getProd() {
		return this.prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public Integer getQtd() {
		return this.qtd;
	}

	public void setQtd(Integer qtd) {
		
		if (qtd < 0) {
			qtd = 0;
		}

		this.qtd = qtd;
	}

}
