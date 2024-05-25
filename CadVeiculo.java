
import java.util.List;
import java.util.Scanner;

import entities.Carro;
import entities.Moto;
import entities.Veiculo;
import services.VeiculoService;

public class CadVeiculo {
    private static Scanner scan;
    private static VeiculoService veiculoService;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        veiculoService = new VeiculoService();

        int escolha;
        do {
            clear();
            System.out.print(
                    "\nSistema de Gerenciamento de Frotas: \nMENU DE OPÇÕES: \n1 - Cadastrar Novo Veículo \n2 - Listar todos os Veículos \n3 - Pesquisar Veículo por Placa \n4 - Remover Veículo \n0 - Sair \nDigite a opção desejada: ");
            do {
                if (scan.hasNextInt()) {
                    escolha = scan.nextInt();
                    if (escolha >= 0 && escolha <= 4) {
                        break;
                    }
                }
                scan.nextLine();
                System.out.print("\nOpção inválida! \nDigite um número dentro das opções acima: ");
            } while (true);

            scan.nextLine();

            switch (escolha) {
                case 1:
                    save();
                    break;
                case 2:
                    imprimirVeiculos();
                    break;
                case 3:
                    pesquisarVeiculo();
                    break;
                case 4:
                    deletarVeiculo();
                    break;
                case 0:
                    clear();
                    System.out.println("\nO sistema encerrou. Volte logo!");
                    break;
            }

        } while (escolha != 0);
    }

    public static void save() {
        clear();

        Veiculo veiculoAdd;
        System.out.print(
                "\nCADASTRO DE VEÍCULO \nQual o tipo de veículo: \n1 - Carro \n2 - Moto \nDigite a opção desejada: ");
        int tipoVeiculo;

        do {
            if (scan.hasNextInt()) {
                tipoVeiculo = scan.nextInt();
                if (tipoVeiculo >= 1 && tipoVeiculo <= 2) {
                    break;
                }
            }
            scan.nextLine();
            System.out.print("\nOpção inválida! \nDigite um número dentro das opções acima: ");
        } while (true);

        scan.nextLine();
        String descricao = tipoVeiculo == 1 ? "do carro: " : "da moto: ";

        clear();

        System.out.print("\nDigite a marca " + descricao);
        String marca = scan.nextLine();
        System.out.print("Digite o modelo " + descricao);
        String modelo = scan.nextLine();
        System.out.print("Digite o ano " + descricao);
        int ano;

        do {
            if (scan.hasNextInt()) {
                ano = scan.nextInt();
                if (ano > 0)
                    break;
            }
            scan.nextLine();
            System.out.print("\nOpção inválida! \nDigite um ano válido: ");
        } while (true);

        scan.nextLine();
        System.out.print("Digite a placa " + descricao);
        String placa = scan.nextLine();

        if (tipoVeiculo == 1) {
            System.out.print("Digite o número de portas: ");
            int numeroPortas;

            do {
                if (scan.hasNextInt()) {
                    numeroPortas = scan.nextInt();
                    if (numeroPortas >= 2 && numeroPortas <= 4)
                        break;
                }
                scan.nextLine();
                System.out.print("\nOpção inválida! \nDigite um número de portas válido: ");
            } while (true);

            scan.nextLine();
            veiculoAdd = new Carro(marca, modelo, ano, placa, numeroPortas);
        } else {
            System.out.print("A moto possue partida elétrica? \n1-Sim \n2-Não \nDigite a opção desejada: ");
            int partidaEletrica;

            do {
                if (scan.hasNextInt()) {
                    partidaEletrica = scan.nextInt();
                    if (partidaEletrica == 1 || partidaEletrica == 2)
                        break;
                }
                scan.nextLine();
                System.out.print("\nOpção inválida! \nDigite um número das opções acima: ");
            } while (true);

            scan.nextLine();
            boolean partida = partidaEletrica == 1 ? true : false;
            veiculoAdd = new Moto(marca, modelo, ano, partida, placa);
        }
        try {
            veiculoService.save(veiculoAdd);
            System.out.println("\nVeículo cadastrado com sucesso! \nPressione enter para voltar ao Menu Inicial");
            scan.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scan.nextLine();
        }
    }

    private static void imprimirVeiculos() {
        int indice = 1;
        clear();
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        for (Veiculo veiculo : veiculos) {
            String tipo = veiculo instanceof Carro ? "Carro" : "Moto";
            System.out.print("\nVeículo " + indice++ + " - Tipo: " + tipo + " - " + veiculo);
        }
        System.out.print("\nPressione enter para voltar ao Menu Inicial");
        scan.nextLine();
    }

    private static void pesquisarVeiculo() {
        clear();
        System.out.print("\nInforme a placa que você quer pesquisar: ");
        String placa = scan.nextLine();
        veiculoService.pesquisarVeiculo(placa);
        try {
            Veiculo veiculo = veiculoService.pesquisarVeiculo(placa);
            if (veiculo != null) {
                String tipo = veiculo instanceof Carro ? "Carro" : "Moto";
                System.out.print("\nVeículo" + " Tipo: " + tipo + " - " + veiculo);
            } else {
                System.out.println("\nVeículo não encontrado com a placa informada");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print("\nPressione enter para voltar ao Menu Inicial");
        scan.nextLine();
    }

    private static void deletarVeiculo() {
        clear();
        System.out.print("\nREMOÇÃO DE VEÍCULOS");
        System.out.print("\nLISTA DE VEÍCULOS CADASTRADOS");
        int indice = 1;
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        for (Veiculo veiculo : veiculos) {
            String tipo = veiculo instanceof Carro ? "Carro" : "Moto";
            System.out.print("\nVeículo " + indice++ + " - Tipo: " + tipo + " - " + veiculo);
        }

        System.out.print("\nInforme a placa do veículo que deseja REMOVER: ");
        String placa = scan.nextLine();
        try {
            veiculoService.remove(placa); 
            System.out.print("\nVeículo removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print("\nPressione enter para voltar ao Menu Inicial");
        scan.nextLine();
    }

    private static void clear() {
        System.out.print("033[H\033[2J");
    }
}