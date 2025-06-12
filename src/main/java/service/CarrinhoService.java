package service;

import model.ItemCarrinho;
import model.Produto;

import java.util.*;

public class CarrinhoService {

    private final List<ItemCarrinho> itens = new ArrayList<>();
    private final Map<Long, ItemCarrinho> mapaItens = new HashMap<>();
    private final Stack<ItemCarrinho> historico = new Stack<>();

    public void adicionarProduto(Produto produto, int quantidade) {
        ItemCarrinho item = mapaItens.get(produto.getId());
        if (item == null) {
            item = new ItemCarrinho(produto, quantidade);
            itens.add(item);
            mapaItens.put(produto.getId(), item);
        } else {
            item.adicionarQuantidade(quantidade);
        }
        historico.push(new ItemCarrinho(produto, quantidade));
    }

    public void listarCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }

        System.out.println("Itens no Carrinho:");
        double total = 0.0;

        for (ItemCarrinho item : itens) {
            String nome = item.getProduto().getDescricao();
            double preco = item.getProduto().getPreco();
            int quantidade = item.getQuantidade();
            double subtotal = preco * quantidade;
            total += subtotal;

            System.out.printf("- %s | Qtd: %d | Preço: R$ %.2f | Subtotal: R$ %.2f%n",
                    nome, quantidade, preco, subtotal);
        }

        System.out.printf("Total da compra: R$ %.2f%n", total);
    }

    public ItemCarrinho desfazerUltimaAdicao() {
        if (!historico.isEmpty()) {
            ItemCarrinho item = historico.pop();
            ItemCarrinho noCarrinho = mapaItens.get(item.getProduto().getId());

            if (noCarrinho != null) {
                noCarrinho.removerQuantidade(item.getQuantidade());
                if (noCarrinho.getQuantidade() <= 0) {
                    itens.remove(noCarrinho);
                    mapaItens.remove(noCarrinho.getProduto().getId());
                }
            }
            return item;
        }
        return null;
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }
}