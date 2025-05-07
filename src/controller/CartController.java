package controller;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import model.CartItem;
import model.Product;
import repository.CartRepository;

public class CartController implements CartRepository {
	private ArrayList<Product> marketProducts = new ArrayList<Product>();
	private ArrayList<CartItem> cartProducts  = new ArrayList<CartItem>();
	Integer Id = 1000;

	@Override
	public Optional<Product> searchProductById(int id) {
		return marketProducts.stream()
	            .filter(prod -> prod.getId().equals(id))
	            .findFirst();
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		return marketProducts.stream()
	            .filter(prod -> prod.getName().contains(name))
	            .collect(Collectors.toCollection(ArrayList::new));
	}
	
	public OptionalInt getPosCartProductById(Integer id) {
		OptionalInt aux = OptionalInt.empty();

		for(int i = 0; i < cartProducts.size(); i++) {
			if (cartProducts.get(i).getProd().getId().equals(id)) {
				aux = OptionalInt.of(i);
			}

		}
		
		return aux;
	}

	@Override
	public ArrayList<CartItem> getAllCartProducts() {
		return cartProducts;
	}
	
	@Override
	public ArrayList<Product> getAllMarketProducts() {
		return marketProducts;
	}

	@Override
	public boolean addProductToCart(Product prod, int qtd) {
		OptionalInt pos = this.getPosCartProductById(prod.getId());
		
		if(pos.isPresent()) {
			Integer posAux = pos.getAsInt();
			
			return cartProducts.set(posAux, new CartItem(prod, qtd)) != null;
		} else {			
			return cartProducts.add(new CartItem(prod, qtd));
		}

	}
	
	@Override
	public boolean addProductToMarket(Product prod) {
		return marketProducts.add(prod);
	}

	@Override
	public boolean updateCartProduct(Product prod, int qtd) {
		OptionalInt pos = this.getPosCartProductById(prod.getId());
		
		if(pos.isPresent()) {
			Integer posAux = pos.getAsInt();
			
			return cartProducts.set(posAux, new CartItem(prod, qtd)) != null;
		} else {			
			return cartProducts.add(new CartItem(prod, qtd));
		}
	}

	@Override
	public boolean removeProductFromCart(Product prod) {
		OptionalInt pos = this.getPosCartProductById(prod.getId());

		if(pos.isPresent()) {

			return cartProducts.remove(pos.getAsInt()) != null;
		}

		return false;
	}

	@Override
	public boolean removeProductFromCart(int id) {
		OptionalInt pos = this.getPosCartProductById(id);
		if(pos.isPresent()) {
			return cartProducts.remove(pos.getAsInt()) != null;
		}

		return false;
	}

	public void resetCartProducts() {
		cartProducts  = new ArrayList<CartItem>();
	}

	public Integer getId() {
		return ++Id;
	}

}
