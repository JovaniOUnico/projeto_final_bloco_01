package visualization;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import controller.CartController;
import model.CartItem;
import model.Print3D;
import model.PrintText;
import model.Product;


public class Menu {
	private static Scanner read;
	private static CartController cartControl;

	public static void main(String[] args) {
		
		cartControl = new CartController();
		
		initController();

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

		Show Sh = new Show();

        List<String> menuProducts = new ArrayList<String>();

		for (Product prod : cartControl.getAllMarketProducts()) {

			String prodDesc = Show.showProductLine(prod);
	
			menuProducts.add(prodDesc);
		}

        Sh.showMenu("Lista de Produtos", menuProducts, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT, "", true, " Produtos não cadastrados");

	}
	
	public static void addProductToCart() {
		
		Integer cod = getValueInt("Digite o código do produto que deseja adicionar ao carrinho: ");
		
		
		if (cartControl.searchProductById(cod).isPresent()) {
			Integer qtd = getValueInt("Digite a quantidade de produtos que quer comprar: ");
			
			cartControl.addProductToCart(cartControl.searchProductById(cod).get(), qtd);
			System.out.println("Produto adicionado ao carrinho");
		} else {
			System.out.println("Produto não encontrado");
		}

	}
	
	public static void removeProductFromCart() {
		Integer cod = getValueInt("Digite o código do produto que deseja remover do carrinho: ");

		if (cartControl.searchProductById(cod).isPresent()) {
			cartControl.removeProductFromCart(cod);
			System.out.println("Produto removido do carrinho com sucesso");
		} else {
			System.out.println("Produto não encontrado");
		}
	}
	
	public static void listCart() {
		Show Sh = new Show();

        List<String> menuProducts = new ArrayList<String>();

		for (CartItem ct : cartControl.getAllCartProducts()){

			String prodDesc = Show.showCartLine(ct);
	
			menuProducts.add(prodDesc);
		}

        Sh.showMenu("Carrinho de compra", menuProducts, Colors.BLACK_BACKGROUND, Colors.BLUE_BRIGHT, "", true, "Carrinho Vazio");

	}
	
	public static void finishPurchase() {
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();
		listCart();
		
		float sum = 0;
		
		for(CartItem ct : cartControl.getAllCartProducts()){
			sum += ct.getProd().getPrice() * ct.getQtd();
		}
		
		System.out.println("Valor Total: " + nfMoeda.format(sum));

		payment(sum);
		
		
	}
	
	public static void initController() {
		ArrayList<Product> pList = new ArrayList<Product>();
		
		pList.add(new Print3D(cartControl.getId(), "Puxador de Gaveta",	"Puxador moderno para móveis",	9f, 40, 0.03f));
		pList.add(new Print3D(cartControl.getId(), "Ornamento de Parede", "Decoração tridimensional para parede", 35f, 40, 0.03f));
		pList.add(new Print3D(cartControl.getId(), "Conector para projetos eletrônicos", "Conector para projetos eletrônicos",	28f, 40, 0.11f));
		pList.add(new Print3D(cartControl.getId(), "Quebra-Cabeça 3D", "Quebra-cabeça tridimensional pequeno",	28f, 130, 0.11f));
		
		pList.add(new PrintText(cartControl.getId(), "Adesivo de Parede - Citação Inspiradora", "Adesivo vinílico com frase motivacional", 20.00f, 30, 0.5f));
		pList.add(new PrintText(cartControl.getId(), "Placa Decorativa - Bem-Vindo", "Placa em PVC com texto 'Bem-Vindo' para porta", 15.00f, 20, 0.3f));
		pList.add(new PrintText(cartControl.getId(), "Banner Personalizado - Feliz Aniversário", "Banner em lona com nome e tema de aniversário", 35.00f, 60, 1.2f));
		pList.add(new PrintText(cartControl.getId(), "Letreiro em MDF - Café", "Letras em MDF com a palavra 'Café' para decoração", 25.00f, 45, 0.8f));
		pList.add(new PrintText(cartControl.getId(), "Adesivo para Notebook - Logo da Empresa", "Adesivo vinílico recortado com o logo da empresa", 10.00f, 15, 0.15f));

		for (Product pd : pList) {			
			cartControl.addProductToMarket(pd);
		}
		
		//tests();

	}

	
	public static Integer getValueInt(String msg) {
    	boolean validator = true;
    	int value = 0;

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextInt();

				return value;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}
	
	public static Integer getValueInt(String msg, int min, int max) {
    	boolean validator = true;
    	int value = 0;

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextInt();

				if (value >= min && value <= max) {					
					validator = false;
				} else {
					throw new Exception("Value is invalid");
				}

			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}

	public static float getValueFloat(String msg) {
    	boolean validator = true;
    	float value = 0;

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextFloat();

				validator = false;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}
	
	public static String getValueString(String msg) {
    	boolean validator = true;
    	String value = "";

    	while (validator) {
        	System.out.println(msg);

			try {
				value = read.nextLine();
				//read.nextLine();

				validator = false;
			} catch (Exception e) {
			    System.err.println("Erro ao digitar o valor tente novamente!");

			    read.nextLine();
				continue;
			}
		}
    	
    	return value;
	}
	
	public static void tests() {		
		listProducts();

		Optional<Product> prod = cartControl.searchProductById(1001);

		if (prod.isPresent()) {
			System.out.println("Produto encontrado");
			System.out.println(Show.showProductLine(prod.get()));
		} else {
			System.out.println("Produto não encontrado");
		}
		
		System.out.println("----");
		
		cartControl.searchProductsByName("Ad").forEach(prod2 -> System.out.println(Show.showProductLine(prod2)));

		System.out.println("----");

		System.out.println("Adicionando produto ao carrinho");
		cartControl.addProductToCart(cartControl.searchProductById(1001).get(), 20);

		System.out.println("----");

		System.out.println("Lista dos produtos no carrinho");
		cartControl.getAllCartProducts().forEach(cd -> System.out.println(Show.showProductLine(cd.getProd()) + " | quantidade " + cd.getQtd()));

		System.out.println("----");
		

		System.out.println("Adicionando novamente o produto ao carrinho");
		cartControl.addProductToCart(cartControl.searchProductById(1001).get(), 10);

		System.out.println("----");

		System.out.println("Lista dos produtos no carrinho");
		cartControl.getAllCartProducts().forEach(cd -> System.out.println(Show.showProductLine(cd.getProd()) + " | quantidade " + cd.getQtd()));

		System.out.println("----");
		
		System.out.println("Adicionando outro produto ao carrinho");
		cartControl.addProductToCart(cartControl.searchProductById(1005).get(), 33);

		System.out.println("----");

		System.out.println("Lista dos produtos no carrinho");
		cartControl.getAllCartProducts().forEach(cd -> System.out.println(Show.showProductLine(cd.getProd()) + " | quantidade " + cd.getQtd()));

		System.out.println("----");
		
		System.out.println("Removendo produto do carrinho");
		cartControl.removeProductFromCart(1001);

		System.out.println("----");

		System.out.println("Lista dos produtos no carrinho");
		cartControl.getAllCartProducts().forEach(cd -> System.out.println(Show.showProductLine(cd.getProd()) + " | quantidade " + cd.getQtd()));

		System.out.println("----");

		Show.keyPress();
	}
	
	public static void payment(Float value) {
		Random random = new Random();

		//simular pagamento com cartão de crédito

        // Solicitar informações do cartão
		read.nextLine();

		System.out.print("Número do Cartão (XXXX-XXXX-XXXX-XXXX): ");
        String numeroCartao = read.nextLine();


        System.out.print("Data de Validade (MM/AA): ");
        String dataValidade = read.nextLine();

        System.out.print("CVV: ");
        String cvv = read.nextLine();
      // Simular processamento
        System.out.println("\nProcessando pagamento...");

        // Simular comunicação com a instituição financeira (atraso aleatório)
        try {
            Thread.sleep(random.nextInt(3000) + 1000); // Espera entre 1 e 4 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simular aprovação ou rejeição com base em uma probabilidade
        boolean pagamentoAprovado = random.nextDouble() <= 0.8; // 80% de chance de aprovação

        if (pagamentoAprovado) {
            System.out.println("\nPagamento APROVADO!");
            System.out.printf("Valor pago: R$ %.2f\n", value);
            System.out.println("Obrigado pela sua compra!");

            // Simular geração de número de transação
            String numeroTransacao = String.format("%012d", random.nextInt(1000000000));
            System.out.println("Número da Transação: " + numeroTransacao);
            
            cartControl.resetCartProducts();

        } else {
            System.out.println("\nPagamento REJEITADO!");
            int codigoErro = random.nextInt(100) + 1;
            String mensagemErro;

            switch (codigoErro) {
                case 1:
                case 25:
                    mensagemErro = "Saldo insuficiente.";
                    break;
                case 5:
                case 33:
                    mensagemErro = "Cartão inválido.";
                    break;
                case 14:
                    mensagemErro = "Número do cartão incorreto.";
                    break;
                case 41:
                case 43:
                    mensagemErro = "Cartão bloqueado.";
                    break;
                case 51:
                    mensagemErro = "Data de validade expirada.";
                    break;
                case 54:
                    mensagemErro = "CVV inválido.";
                    break;
                default:
                    mensagemErro = "Erro desconhecido ao processar o pagamento.";
                    break;
            }

            System.out.println("Motivo da rejeição: " + mensagemErro);
            System.out.println("Por favor, verifique os dados do seu cartão e tente novamente.");
        }

	}

}
