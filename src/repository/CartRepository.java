package repository;

import java.util.ArrayList;
import java.util.Optional;

import model.CartItem;
import model.Product;

public interface CartRepository {
	// CRUD
	
	public Optional<Product> searchProductById(int num);
	public ArrayList<Product> searchProductsByName(String name);
	public ArrayList<CartItem> getAllCartProducts();
	public ArrayList<Product> getAllMarketProducts();

	public boolean addProductToCart(Product prod, int qtd);
	public boolean addProductToMarket(Product prod);

	public boolean updateCartProduct(Product acc, int qtd);
	public boolean removeProductFromCart(Product prod);
	public boolean removeProductFromCart(int id);

}
