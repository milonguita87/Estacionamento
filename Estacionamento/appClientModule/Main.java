import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import Dao.TarifaDAO;
import Dao.VeiculoDAO;
import Model.Tarifa;
import Model.Veiculo;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		TarifaDAO tarifaDAO = new TarifaDAO();
		
		int opcao = -1;
		
		while (opcao != 4) {
			System.out.println("\n======= MENU ESTACIONAMENTO =======");
			System.out.println("1- Inserir Placa");
			System.out.println("2- Listar todos os veiculos");
			System.out.println("3- Registrar saida de veiculo");
			System.out.println("4- Sair");
			System.out.println("Escolha uma opção: ");
		
			opcao = Integer.parseInt(scanner.nextLine());
			
			switch (opcao) {
				case 1:
					inserir(scanner, veiculoDAO);
					break;
				case 2: 
					listarTodos(veiculoDAO);
					break;
				case 3:
					registrarSaida(scanner, veiculoDAO, tarifaDAO);
					break;
				case 4:
					System.out.println("Saindo..");
					break;
				default:
					System.out.println("Opcão Invalida!");
				}
		}
	
		scanner.close();
	}

	private static void inserir(Scanner scanner, VeiculoDAO veiculoDAO) {
	    System.out.println("Inserir Placa: ");
	    String placa = scanner.nextLine();
	 
	    if (placa == null || placa.trim().isEmpty()) {
	        System.out.println("Placa inválida. Você precisa digitar uma placa.");
	        return;
	    }
	 
	    Integer idExistente = veiculoDAO.buscarIdPorPlaca(placa);
	 
	    if (idExistente != null) {
	        System.out.println("Esse veículo já está no estacionamento. Não é possível registrar entrada novamente.");
	        return;
	    }
	 

	    Veiculo veiculo = new Veiculo(0, placa, LocalDateTime.now(), null);
	    veiculoDAO.inserir(veiculo);
	    System.out.println("Veículo inserido com sucesso.");
	}
	 
	
	private static void listarTodos(VeiculoDAO veiculoDAO) {
		List<Veiculo> veiculos = veiculoDAO.listarTodos();
		 
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("\n--- Veículos no estacionamento ---");
            for (Veiculo v : veiculos) {
                System.out.println(
                    "ID: " + v.getIdveiculo() +
                    " | Placa: " + v.getPlaca() +
                    " | Entrada: " + v.getHora_entrada() +
                    " | Saída: " + v.getHora_saida()
                );
            }
        }
		
	}
	
	private static void registrarSaida(Scanner scanner, VeiculoDAO veiculoDAO, TarifaDAO tarifaDAO) {
		 System.out.println("Digite a placa do veículo que vai sair: ");
		 String placa = scanner.nextLine();
		 Integer idveiculo = veiculoDAO.buscarIdPorPlaca(placa);

		    if (idveiculo == null) {
		        System.out.println("Nenhum veículo com essa placa encontrado no estacionamento.");
		        return;
		    }
		    
		 Veiculo veiculo = veiculoDAO.buscarPorId(idveiculo);
		 LocalDateTime horaEntrada = veiculo.getHora_entrada();
		 LocalDateTime horaSaida = LocalDateTime.now();
		 
		 Tarifa tarifaHora = tarifaDAO.buscarPorNome("HORA");
		 Tarifa tarifaDiaria = tarifaDAO.buscarPorNome("DIARIA");
		 
		 double valor = calcularValor(horaEntrada, horaSaida, tarifaHora, tarifaDiaria);
		 
		 veiculoDAO.registrarSaida(idveiculo, horaSaida);
		 
		 System.out.println("Saída registrada com sucesso!");
		 System.out.printf("Valor a pagar: R$ %.2f%n", valor);
	}
	
	private static double calcularValor(LocalDateTime entrada, LocalDateTime saida, Tarifa tarifaHora, Tarifa tarifaDiaria) {
		 
	    long minutos = java.time.Duration.between(entrada, saida).toMinutes();
	 
	    if (minutos <= 15) {
	        return 0;
	    }
	 
	    long horas = (minutos + 59) / 60;
	 
	    if (horas < 12) {
	        return horas * tarifaHora.getValorHora();
	    } else {
	        long dias = (horas + 23) / 24; 
	        return dias * tarifaDiaria.getValorHora();
	    }
	}
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}