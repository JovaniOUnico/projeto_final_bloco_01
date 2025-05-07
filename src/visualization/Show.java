package visualization;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import model.CartItem;
import model.Product;

public class Show {
	private static final int LARG = 120;
    public void showMenu(String title, List<String> options, String bgColor, String Color) {
        String Aux = bgColor + Color;
        
        System.out.println(Aux + "*".repeat(LARG));

        int espacosTitulo = (LARG - title.length()) / 2;
        System.out.println(Aux + " ".repeat(espacosTitulo) + title + " ".repeat(espacosTitulo + (title.length() % 2)));

        System.out.println(Aux + "*".repeat(LARG));

        if (options != null && !options.isEmpty()) {
            for (int i = 0; i < options.size(); i++) {
                String opcao = (i + 1) + " - " + options.get(i);
                int espacosOpcao = LARG - opcao.length() - 3;// - 3 para as bordas
                System.out.println(Aux + "| " + opcao + " ".repeat(Math.max(0, espacosOpcao)) + "|");
            }
        } else {
            String mensagem = " ";
            int espacosMensagem = (LARG - mensagem.length()) / 2;
            System.out.println(Aux + "|" + " ".repeat(espacosMensagem) + mensagem + " ".repeat(espacosMensagem + (mensagem.length() % 2)) + "|");
        }

        System.out.println(Aux + "*".repeat(LARG));
        System.out.println(Aux + "Entre com a opção desejada: ");
    }
    
    public void showMenu(String title, List<String> options, String bgColor, String Color, String lastText, boolean disableCount, String msgEmpty) {
        String Aux = bgColor + Color;
        
        System.out.println(Aux + "*".repeat(LARG));

        int espacosTitulo = (LARG - title.length()) / 2;
        System.out.println(Aux + " ".repeat(espacosTitulo) + title + " ".repeat(espacosTitulo + (title.length() % 2)));

        System.out.println(Aux + "*".repeat(LARG));

        if (options != null && !options.isEmpty()) {
            for (int i = 0; i < options.size(); i++) {
            	
            	String opcao;
            	if (disableCount) {
            		opcao = options.get(i);
            	} else {
            		opcao = (i + 1) + " - " + options.get(i);            		
            	}

                int espacosOpcao = LARG - opcao.length() - 3;// - 3 para as bordas
                System.out.println(Aux + "| " + opcao + " ".repeat(Math.max(0, espacosOpcao)) + "|");
            }
        } else {
            String mensagem = msgEmpty;
            int espacosMensagem = (LARG - mensagem.length()) / 2;
            System.out.println(Aux + "|" + " ".repeat(espacosMensagem) + mensagem + " ".repeat(espacosMensagem + (mensagem.length() % 2)) + "|");
        }

        System.out.println(Aux + "*".repeat(LARG));
        System.out.println(Aux + lastText);
    }
    
	public static void about() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Jovani Almeida de Souza");
		System.out.println("https://github.com/JovaniOUnico/projeto_final_bloco1");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {

		try {

			System.out.println("\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
	
	public static String showProductLine(Product prod) {
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();

		String type = prod.getClass().getName();

		String prodDesc = prod.getId()  + " | " + prod.getName();

		try {

			if (type.equalsIgnoreCase("model.Print3D") == true) {
				prodDesc += " | 3D | Preço por grama impressa: " + nfMoeda.format(prod.getPrice());
			} else {
				prodDesc += " | Texto | Preço por cm2 impresso " + nfMoeda.format(prod.getPrice());
			}

		} catch (Exception ex) {
		    System.err.println("Erro ao acessar dados");
		}
		
		return prodDesc;

	}

	public static String showCartLine(CartItem ct) {
		NumberFormat nfMoeda = NumberFormat.getCurrencyInstance();

		Product prod = ct.getProd();

		String prodDesc = prod.getId()  + " | " + prod.getName();

		try {

			prodDesc += " | qtd: " + ct.getQtd() + " | Preço Total: " + nfMoeda.format(prod.getPrice() * ct.getQtd());

		} catch (Exception ex) {
		    System.err.println("Erro ao acessar dados");
		}
		
		return prodDesc;

	}
}
