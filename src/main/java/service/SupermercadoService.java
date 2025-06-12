package service;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import model.Cliente;
import model.ItemCarrinho;
import model.Produto;

import java.util.Scanner;

public class SupermercadoService {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final CarrinhoService carrinhoService = new CarrinhoService();

    public void iniciarAtendimento(Scanner scan) {
        cadastrarCliente(scan);

        boolean emExecucao = true;
        while (emExecucao) {
            mostrarMenu();
            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1 -> cadastrarProduto(scan);
                case 2 -> adicionarProdutoAoCarrinho(scan);
                case 3 -> carrinhoService.listarCarrinho();
                case 4 -> desfazerUltimaAdicao();
                case 0 -> {
                    emExecucao = false;
                    finalizar();
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Cadastrar produto.");
        System.out.println("2 - Adicionar produto ao carrinho.");
        System.out.println("3 - Ver carrinho.");
        System.out.println("4 - Desfazer última adição.");
        System.out.println("0 - Sair.");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarCliente(Scanner scan) {
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scan.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        clienteDAO.salvar(cliente);
        System.out.println("Cliente registrado com sucesso!");
    }

    private void cadastrarProduto(Scanner scan) {
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = scan.nextLine();
        System.out.print("Digite o preço do produto: ");
        double precoProduto = scan.nextDouble();
        scan.nextLine();

        Produto produto = new Produto();
        produto.setDescricao(nomeProduto);
        produto.setPreco(precoProduto);
        produtoDAO.salvar(produto);

        System.out.printf("Produto '%s' cadastrado com preço R$ %.2f%n", nomeProduto, precoProduto);
    }

    private void adicionarProdutoAoCarrinho(Scanner scan) {
        System.out.println("\nProdutos disponíveis:");
        for (Produto p : produtoDAO.listar()) {
            System.out.printf("ID: %d | Nome: %s | Preço: R$ %.2f%n", p.getId(), p.getDescricao(), p.getPreco());
        }

        System.out.print("Digite o ID do produto: ");
        long idProduto = scan.nextLong();

        System.out.print("Digite a quantidade: ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        Produto buscarProduto = produtoDAO.buscar(idProduto);
        if (buscarProduto != null) {
            carrinhoService.adicionarProduto(buscarProduto, quantidade);
            double subtotal = buscarProduto.getPreco() * quantidade;
            double totalAtual = carrinhoService.calcularTotal();

            System.out.printf("Produto '%s' adicionado (Qtd: %d, Subtotal: R$ %.2f)%n",
                    buscarProduto.getDescricao(), quantidade, subtotal);
            System.out.printf("Valor total atual do carrinho: R$ %.2f%n", totalAtual);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private void desfazerUltimaAdicao() {
        ItemCarrinho itemRemovido = carrinhoService.desfazerUltimaAdicao();
        if (itemRemovido != null) {
            System.out.printf("Produto '%s' (Qtd: %d) removido do carrinho.%n",
                    itemRemovido.getProduto().getDescricao(),
                    itemRemovido.getQuantidade());
        } else {
            System.out.println("Nenhuma adição anterior para desfazer.");
        }
    }

    private void finalizar() {
        produtoDAO.fechar();
        clienteDAO.fechar();
        System.out.println("Encerrando o sistema...");
    }
}