import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("Menu");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Abrir Conta");
            System.out.println("3. Escolher Plano");
            System.out.println("4. Exibir Clientes e Contas");
            System.out.println("5. Depositar em Conta");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    abrirConta();
                    break;
                case 3:
                    escolherPlano();
                    break;
                case 4:
                    listarClientesContas();
                    break;
                case 5:
                    depositarEmConta();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }

    private static void cadastrarCliente() {
        System.out.print("Digite o tipo de cliente (1. Pessoa Física, 2. Pessoa Jurídica): ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente;
        if (tipoCliente == 1) {
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();
            cliente = new Pessoa_Fisica(nome, cpf);
        } else {
            System.out.print("Digite o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();
            cliente = new Pessoa_Juridica(nome, cnpj);
        }

        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void abrirConta() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }

        System.out.print("Digite o nome do titular da conta: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarClientePorNome(nome);

        if (cliente != null) {
            Conta conta = new Conta(cliente);
            contas.add(conta);
            System.out.println("Conta aberta com sucesso para " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private static void escolherPlano() {
        System.out.print("Digite o nome do cliente para selecionar o plano: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarClientePorNome(nome);

        if (cliente instanceof Pessoa_Fisica) {
            System.out.print("Escolha o plano individual (Básico, Completo): ");
            String plano = scanner.nextLine();
            ((Pessoa_Fisica) cliente).escolherPlanoIndividual(plano);
            System.out.println("Plano individual '" + plano + "' selecionado para " + cliente.getNome());
        } else if (cliente instanceof Pessoa_Juridica) {
            System.out.print("Escolha o plano coletivo (Empresarial, Corporativo): ");
            String plano = scanner.nextLine();
            ((Pessoa_Juridica) cliente).escolherPlanoColetivo(plano);
            System.out.println("Plano coletivo '" + plano + "' selecionado para " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado ou tipo inválido.");
        }
    }

    private static void depositarEmConta() {
        System.out.print("Digite o nome do titular da conta para depósito: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarClientePorNome(nome);

        if (cliente != null) {
            System.out.print("Digite o valor do depósito: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            Conta conta = buscarContaPorCliente(cliente);
            if (conta != null) {
                conta.depositar(valor);
                System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
            } else {
                System.out.println("Conta não encontrada para o cliente " + cliente.getNome());
            }
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    private static void listarClientesContas() {
        for (Conta conta : contas) {
            conta.getTitular().mostrarDados();
            System.out.println("Saldo da conta: R$" + conta.getSaldo());
        }
    }

    private static Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    private static Conta buscarContaPorCliente(Cliente cliente) {
        for (Conta conta : contas) {
            if (conta.getTitular().equals(cliente)) {
                return conta;
            }
        }
        return null;
    }
}
