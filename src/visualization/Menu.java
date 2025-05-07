package visualization;

import java.util.List;
import java.util.Scanner;

public class Menu {
	private static Scanner read;

	public static void main(String[] args) {
		read = new Scanner(System.in);

		Show Sh = new Show();

		int opt = 0;

        List<String> menuPrincipal = List.of(
            "Listar Produtos",
            "Adicionar Produto ao Carrinho",
            "Remover produto do carrinho",
            "Ver Carrinho",
            "Finalizar Compra",
            "Sair"
        );

        do {
        	Sh.showMenu("Impress 4 You", menuPrincipal, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT);

        	opt = read.nextInt();

        	control(opt);
        } while (opt != 6);

        read.close();
	}

	public static void control (int opt) {

		switch (opt) {
			case 1 -> listProducts();
			case 2 -> addProductToCart();
			case 3 -> removeProductFromCart();
			case 4 -> listCart();
			case 5 -> finishPurchase();
			case 6 -> Show.about();
			default -> System.out.println("Opção inválida tente novamente");
		}
		
		if (opt != 6) {			
			Show.keyPress();
		}

	}
	
	public static void listProducts() {
		System.out.println("Listando os produtos");
	}
	
	public static void addProductToCart() {
		System.out.println("Adicionando produtos ao carrinho");
	}
	
	public static void removeProductFromCart() {
		System.out.println("Remover produto do carrinho");
	}
	
	public static void listCart() {
		System.out.println("Ver carrinho");
	}
	
	public static void finishPurchase() {
		System.out.println("Finalizar Compra");
	}

}
